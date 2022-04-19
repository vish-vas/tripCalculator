package com.example.lp.tripcalculator.calculator;

import com.example.lp.tripcalculator.model.TripStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TripCostCalculatorFactoryTest {

    TripCostCalculatorFactory tripCostCalculatorFactory;
    TripCostCalculator cancelledTripCalculator;
    TripCostCalculator completeTripCalculator;
    TripCostCalculator incompleteTripCalculator;

    @BeforeEach
    void setup() {
        this.cancelledTripCalculator = mock(CancelledTrip.class);
        this.completeTripCalculator = mock(CompleteTrip.class);
        this.incompleteTripCalculator = mock(IncompleteTrip.class);
        when(cancelledTripCalculator.forTripStatus()).thenReturn(TripStatus.CANCELED);
        when(completeTripCalculator.forTripStatus()).thenReturn(TripStatus.COMPLETE);
        when(incompleteTripCalculator.forTripStatus()).thenReturn(TripStatus.INCOMPLETE);
        this.tripCostCalculatorFactory =
                new TripCostCalculatorFactory(Set.of(cancelledTripCalculator, completeTripCalculator, incompleteTripCalculator));
    }

    @Test
    void getCalculatorForTripStatus() {
        assertSame(cancelledTripCalculator, tripCostCalculatorFactory.getCalculatorForTripStatus(TripStatus.CANCELED));
        assertSame(completeTripCalculator, tripCostCalculatorFactory.getCalculatorForTripStatus(TripStatus.COMPLETE));
        assertSame(incompleteTripCalculator, tripCostCalculatorFactory.getCalculatorForTripStatus(TripStatus.INCOMPLETE));
    }

}