package com.example.lp.tripcalculator.model;

import java.util.Arrays;
import java.util.Optional;

public enum Stop {
    Stop1("Stop 1", "Stop1"),
    Stop2("Stop 2", "Stop2"),
    Stop3("Stop 3", "Stop3"),
    ;

    private final String name;
    private final String val;

    Stop(String name, String val) {
        this.name = name;
        this.val = val;
    }

    public String getName() {
        return name;
    }

    public String getVal() {
        return val;
    }

    public Optional<Stop> find(String val) {
        return Arrays.stream(values()).filter(i -> i.getVal().equals(val)).findFirst();
    }
}
