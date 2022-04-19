package com.example.lp.tripcalculator.calculator;

import com.example.lp.tripcalculator.model.Stop;
import com.example.lp.tripcalculator.model.Tap;
import com.example.lp.tripcalculator.model.TripStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CancelledTripTest {

    private TripCostCalculator calculator;

    @BeforeEach
    void setup() {
        calculator = new CancelledTrip();
    }

    @Test
    void calculateCost() {
        Tap on = mock(Tap.class);
        when(on.getStop()).thenReturn(Stop.Stop1);
        Tap off = mock(Tap.class);
        when(off.getStop()).thenReturn(Stop.Stop1);
        assertEquals(BigDecimal.ZERO, calculator.calculateCost(on, off));
    }

    @Test
    void forTripStatus() {
        assertEquals(TripStatus.CANCELED, calculator.forTripStatus());
    }
}