package com.example.lp.tripcalculator.model;

import java.math.BigDecimal;
import java.util.Set;

public enum Fare {
    _1(BigDecimal.valueOf(3.25), Set.of(Stop.Stop1, Stop.Stop2)),
    _2(BigDecimal.valueOf(5.5), Set.of(Stop.Stop2, Stop.Stop3)),
    _3(BigDecimal.valueOf(7.3), Set.of(Stop.Stop1, Stop.Stop3)),
    ;

    private final BigDecimal cost;
    private final Set<Stop> betweenStops;

    Fare(BigDecimal cost, Set<Stop> betweenStops) {
        this.cost = cost;
        this.betweenStops = betweenStops;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public Set<Stop> getBetweenStops() {
        return betweenStops;
    }
}
