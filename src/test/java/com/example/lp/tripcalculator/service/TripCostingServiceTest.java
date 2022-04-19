package com.example.lp.tripcalculator.service;

import com.example.lp.tripcalculator.calculator.TripCostCalculator;
import com.example.lp.tripcalculator.calculator.TripCostCalculatorFactory;
import com.example.lp.tripcalculator.config.TripCalculatorConfig;
import com.example.lp.tripcalculator.fileIO.CsvHelper;
import com.example.lp.tripcalculator.model.Fare;
import com.example.lp.tripcalculator.model.Stop;
import com.example.lp.tripcalculator.model.Tap;
import com.example.lp.tripcalculator.model.TapType;
import com.example.lp.tripcalculator.model.Trip;
import com.example.lp.tripcalculator.model.TripStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@MockitoSettings
class TripCostingServiceTest {

    private TripCostingService tripCostingService;
    @Mock
    private TripCostCalculatorFactory tripCostCalculatorFactory;
    @Mock
    private TripCostCalculator tripCostCalculator;
    @Mock
    private CsvHelper csvHelper;
    @Mock
    private TripCalculatorConfig config;

    private static final String PAN = "500030002000";
    private static final String BUS_ID = "Bus22";
    private static final String COMPANY_ID = "CompanyX";
    private static final String FILENAME = "fileName";

    @BeforeEach
    void setup() {
        when(config.getInputFileName()).thenReturn(FILENAME);
        when(config.getOutputFileName()).thenReturn(FILENAME);
        tripCostingService = new TripCostingService(csvHelper, tripCostCalculatorFactory, config);
    }

    @Test
    void calculateCostsForCompleteTrip() {
        Date from = new Date();
        Tap tapOn = new Tap(1, from, TapType.ON, Stop.Stop1, COMPANY_ID, BUS_ID, PAN);

        Date to = Date.from(from.toInstant().plusSeconds(550));
        Tap tapOff = new Tap(2, to, TapType.OFF, Stop.Stop3, COMPANY_ID, BUS_ID, PAN);

        when(tripCostCalculatorFactory.getCalculatorForTripStatus(TripStatus.COMPLETE)).thenReturn(tripCostCalculator);
        when(tripCostCalculator.calculateCost(tapOn, tapOff)).thenReturn(Fare._3.getCost());

        List<Trip> trips = tripCostingService.calculateCosts(List.of(tapOn, tapOff).iterator());

        assertEquals(1, trips.size());

        Trip trip = trips.get(0);
        assertEquals(from, trip.getStarted());
        assertEquals(to, trip.getFinished());
        assertEquals(BUS_ID, trip.getBusId());
        assertEquals(Stop.Stop1, trip.getFromStop());
        assertEquals(Stop.Stop3, trip.getToStop());
        assertEquals(COMPANY_ID, trip.getCompanyId());
        assertEquals("$" + Fare._3.getCost().setScale(2), trip.getChargeAmount());
        assertEquals(PAN, trip.getPan());
        assertEquals(TripStatus.COMPLETE, trip.getStatus());
        assertEquals(550, trip.getDurationSecs());
    }

    @Test
    void calculateCostsForInCompleteTrip() {
        Date from = new Date();
        Tap tapOn = new Tap(1, from, TapType.ON, Stop.Stop1, COMPANY_ID, BUS_ID, PAN);

        when(tripCostCalculatorFactory.getCalculatorForTripStatus(TripStatus.INCOMPLETE)).thenReturn(tripCostCalculator);
        when(tripCostCalculator.calculateCost(tapOn, null)).thenReturn(Fare._3.getCost());

        List<Trip> trips = tripCostingService.calculateCosts(List.of(tapOn).iterator());

        assertEquals(1, trips.size());

        Trip trip = trips.get(0);
        assertEquals(from, trip.getStarted());
        assertNull(trip.getFinished());
        assertEquals(BUS_ID, trip.getBusId());
        assertEquals(Stop.Stop1, trip.getFromStop());
        assertNull(trip.getToStop());
        assertEquals(COMPANY_ID, trip.getCompanyId());
        assertEquals("$" + Fare._3.getCost().setScale(2), trip.getChargeAmount());
        assertEquals(PAN, trip.getPan());
        assertEquals(TripStatus.INCOMPLETE, trip.getStatus());
        assertNull(trip.getDurationSecs());
    }

    @Test
    void calculateCostsForCanceledTrip() {
        Date from = new Date();
        Tap tapOn = new Tap(1, from, TapType.ON, Stop.Stop1, COMPANY_ID, BUS_ID, PAN);

        Date to = Date.from(from.toInstant().plusSeconds(550));
        Tap tapOff = new Tap(2, to, TapType.OFF, Stop.Stop1, COMPANY_ID, BUS_ID, PAN);

        when(tripCostCalculatorFactory.getCalculatorForTripStatus(TripStatus.CANCELED)).thenReturn(tripCostCalculator);
        when(tripCostCalculator.calculateCost(tapOn, tapOff)).thenReturn(BigDecimal.ZERO);

        List<Trip> trips = tripCostingService.calculateCosts(List.of(tapOn, tapOff).iterator());

        assertEquals(1, trips.size());

        Trip trip = trips.get(0);
        assertEquals(from, trip.getStarted());
        assertEquals(to, trip.getFinished());
        assertEquals(BUS_ID, trip.getBusId());
        assertEquals(Stop.Stop1, trip.getFromStop());
        assertEquals(Stop.Stop1, trip.getToStop());
        assertEquals(COMPANY_ID, trip.getCompanyId());
        assertEquals("$" + BigDecimal.ZERO.setScale(2), trip.getChargeAmount());
        assertEquals(PAN, trip.getPan());
        assertEquals(TripStatus.CANCELED, trip.getStatus());
        assertEquals(550, trip.getDurationSecs());
    }
}