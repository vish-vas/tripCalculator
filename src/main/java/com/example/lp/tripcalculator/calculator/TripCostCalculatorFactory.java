package com.example.lp.tripcalculator.calculator;

import com.example.lp.tripcalculator.model.TripStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class TripCostCalculatorFactory {
    private final Map<TripStatus, TripCostCalculator> tripCostCalculators;

    TripCostCalculatorFactory(List<TripCostCalculator> tripCostCalculators) {
        this.tripCostCalculators = tripCostCalculators
                .stream()
                .collect(Collectors.toMap(TripCostCalculator::forTripStatus, Function.identity()));
    }

    public TripCostCalculator getCalculatorForTripStatus(TripStatus tripStatus) {
        return tripCostCalculators.get(tripStatus);
    }
}
