package com.example.lp.tripcalculator.calculator;

import com.example.lp.tripcalculator.model.Stop;
import com.example.lp.tripcalculator.model.Tap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class IncompleteTripTest {

    private TripCostCalculator calculator;

    @BeforeEach
    void setup() {
        calculator = new IncompleteTrip();
    }

    @Test
    void calculateCost_atStop1() {
        Tap on = mock(Tap.class);
        when(on.getStop()).thenReturn(Stop.Stop1);
        assertEquals(BigDecimal.valueOf(7.3), calculator.calculateCost(on, null));
    }

    @Test
    void calculateCost_atStop2() {
        Tap on = mock(Tap.class);
        when(on.getStop()).thenReturn(Stop.Stop2);
        assertEquals(BigDecimal.valueOf(5.5), calculator.calculateCost(on, null));
    }

    @Test
    void calculateCost_atStop3() {
        Tap on = mock(Tap.class);
        when(on.getStop()).thenReturn(Stop.Stop3);
        assertEquals(BigDecimal.valueOf(7.3), calculator.calculateCost(on, null));
    }

}