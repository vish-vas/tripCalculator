package com.example.lp.tripcalculator.calculator;

import com.example.lp.tripcalculator.model.Fare;
import com.example.lp.tripcalculator.model.Stop;
import com.example.lp.tripcalculator.model.Tap;
import com.example.lp.tripcalculator.model.TripStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

@Component
public class IncompleteTrip implements TripCostCalculator {

    private final Map<Stop, BigDecimal> stopCostMap;

    IncompleteTrip() {
        stopCostMap = generateStopCostMap();
    }

    private Map<Stop, BigDecimal> generateStopCostMap(){
        Map<Stop, BigDecimal> temp = new HashMap<>();
        Arrays.stream(Stop.values()).forEach(stop -> {
            BigDecimal highestFare = Arrays.stream(Fare.values())
                    .filter(fare -> fare.getBetweenStops().contains(stop))
                    .map(Fare::getCost)
                    .max(Comparator.naturalOrder())
                    .orElse(BigDecimal.ZERO);
            temp.put(stop, highestFare);
        });
        return temp;
    }

    @Override
    public BigDecimal calculateCost(Tap tapOn, Tap tapOff) {
        return stopCostMap.get(tapOn.getStop());
    }

    @Override
    public TripStatus forTripStatus() {
        return TripStatus.INCOMPLETE;
    }
}
