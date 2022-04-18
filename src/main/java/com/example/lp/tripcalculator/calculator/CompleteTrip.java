package com.example.lp.tripcalculator.calculator;

import com.example.lp.tripcalculator.model.Fare;
import com.example.lp.tripcalculator.model.Tap;
import com.example.lp.tripcalculator.model.TripStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Set;

@Component
public class CompleteTrip implements TripCostCalculator {

    @Override
    public BigDecimal calculateCost(Tap tapOn, Tap tapOff) {
        return Arrays.stream(Fare.values())
                .filter(fare -> fare.getBetweenStops().containsAll(Set.of(tapOn.getStop(), tapOff.getStop())))
                .findFirst()
                .map(Fare::getCost)
                .orElse(BigDecimal.ZERO);
    }

    @Override
    public TripStatus forTripStatus() {
        return TripStatus.COMPLETE;
    }
}
