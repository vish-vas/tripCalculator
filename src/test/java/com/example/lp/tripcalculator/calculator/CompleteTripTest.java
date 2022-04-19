package com.example.lp.tripcalculator.calculator;

import com.example.lp.tripcalculator.model.Stop;
import com.example.lp.tripcalculator.model.Tap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CompleteTripTest {
    private TripCostCalculator calculator;
    Tap stop1;
    Tap stop2;
    Tap stop3;

    @BeforeEach
    void setup() {
        this.calculator = new CompleteTrip();
        this.stop1 = mock(Tap.class);
        this.stop2 = mock(Tap.class);
        this.stop3 = mock(Tap.class);
        when(stop1.getStop()).thenReturn(Stop.Stop1);
        when(stop2.getStop()).thenReturn(Stop.Stop2);
        when(stop3.getStop()).thenReturn(Stop.Stop3);
    }

    @Test
    void calculateCost_between_Stop1_Stop2() {
        assertEquals(BigDecimal.valueOf(3.25),
                calculator.calculateCost(stop1, stop2));
        assertEquals(BigDecimal.valueOf(3.25),
                calculator.calculateCost(stop2, stop1));
    }

    @Test
    void calculateCost_between_Stop2_Stop3() {
        assertEquals(BigDecimal.valueOf(5.5),
                calculator.calculateCost(stop2, stop3));
        assertEquals(BigDecimal.valueOf(5.5),
                calculator.calculateCost(stop3, stop2));
    }

    @Test
    void calculateCost_between_Stop1_Stop3() {
        assertEquals(BigDecimal.valueOf(7.3),
                calculator.calculateCost(stop1, stop3));
        assertEquals(BigDecimal.valueOf(7.3),
                calculator.calculateCost(stop3, stop1));
    }
}