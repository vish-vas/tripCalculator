package com.example.lp.tripcalculator.service;

import com.example.lp.tripcalculator.calculator.TripCostCalculatorFactory;
import com.example.lp.tripcalculator.config.TripCalculatorConfig;
import com.example.lp.tripcalculator.fileIO.CsvReader;
import com.example.lp.tripcalculator.model.Tap;
import com.example.lp.tripcalculator.model.TapType;
import com.example.lp.tripcalculator.model.Trip;
import com.example.lp.tripcalculator.model.TripStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class TripCostingService {

    private final CsvReader csvReader;
    private final TripCostCalculatorFactory tripCostCalculatorFactory;
    private final String inputFileName;
    private static final String CURRENCY = "$";

    public TripCostingService(CsvReader csvReader,
                              TripCostCalculatorFactory tripCostCalculatorFactory,
                              TripCalculatorConfig config) {
        this.csvReader = csvReader;
        this.tripCostCalculatorFactory = tripCostCalculatorFactory;
        this.inputFileName = config.getInputFileName();
    }


    public List<Trip> calculateCosts() throws IOException {
        Iterator<Tap> taps = csvReader.parse(inputFileName, Tap.class);

        Map<String, Tap> panTapOns = new HashMap<>();
        List<Trip> resultTrips = new ArrayList<>();

        taps.forEachRemaining(tap -> {
            if(TapType.ON.equals(tap.getTapType())){
                if(panTapOns.containsKey(tap.getPan())) {
                    //handle the case where there were two tapOns
                }
                panTapOns.put(tap.getPan(), tap);
            }
            if(TapType.OFF.equals(tap.getTapType())){
                if(!panTapOns.containsKey(tap.getPan())){
                    //handle the case where there was no tapOn present
                }
                Tap tapOn = panTapOns.get(tap.getPan());
                resultTrips.add(createTrip(tapOn, tap, calculateTripStatus(tapOn, tap)));
                panTapOns.remove(tap.getPan());
            }
        });

        //handle the incomplete trips
        panTapOns.values().forEach(tapOn -> resultTrips.add(createTrip(tapOn, null, TripStatus.INCOMPLETE)));

        resultTrips.sort(Comparator.comparing(Trip::getStarted));
        return resultTrips;
    }

    private TripStatus calculateTripStatus(Tap tapOn, Tap tapOff){
        if(isTripCancelled(tapOn, tapOff))
            return TripStatus.CANCELED;
        return TripStatus.COMPLETE;
    }

    private boolean isTripCancelled(Tap tapOn, Tap tapOff) {
        return tapOn.getStop().equals(tapOff.getStop());
    }

    private Trip createTrip(Tap tapOn, Tap tapOff, TripStatus tripStatus) {
        String tripCost = CURRENCY + tripCostCalculatorFactory.getCalculatorForTripStatus(tripStatus).calculateCost(tapOn, tapOff)
                .setScale(2, RoundingMode.HALF_EVEN);
        Trip trip = new Trip(tapOn.getDateTime(), tapOn.getStop(), tripCost, tapOn.getCompanyId(), tapOn.getBusId(), tapOn.getPan(), tripStatus);
        if(tapOff != null) {
            trip.setFinished(tapOff.getDateTime());
            trip.setDurationSecs(getTripDurationSecs(tapOn.getDateTime(), tapOff.getDateTime()));
            trip.setToStop(tapOff.getStop());
        }
        return trip;
    }

;    private Long getTripDurationSecs(Date tapOnTime, Date tapOffTime) {
        return TimeUnit.MILLISECONDS
                .toSeconds(tapOffTime.getTime() - tapOnTime.getTime());
    }
}
