package com.example.lp.tripcalculator.calculator;

import com.example.lp.tripcalculator.model.Tap;
import com.example.lp.tripcalculator.model.TripStatus;

import java.math.BigDecimal;

public interface TripCostCalculator {

    BigDecimal calculateCost(Tap tapOn, Tap tapOff);

    TripStatus forTripStatus();
}
