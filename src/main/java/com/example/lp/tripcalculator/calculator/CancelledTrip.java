package com.example.lp.tripcalculator.calculator;

import com.example.lp.tripcalculator.model.Tap;
import com.example.lp.tripcalculator.model.TripStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CancelledTrip implements TripCostCalculator {
    private static final BigDecimal COST = BigDecimal.ZERO;

    @Override
    public BigDecimal calculateCost(Tap tapOn, Tap tapOff) {
        return COST;
    }

    @Override
    public TripStatus forTripStatus() {
        return TripStatus.CANCELED;
    }
}
