package com.example.lp.tripcalculator.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Tap {
    private Integer id;
    private Date dateTime;
    private TapType tapType;
    private Stop stop;
    private String companyId;
    private String busId;
    private String pan;

    @JsonCreator
    public Tap(@JsonProperty("ID") Integer id,
               @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
               @JsonProperty("DateTimeUTC") Date dateTime,
               @JsonProperty("TapType") TapType tapType,
               @JsonProperty("StopId") Stop stop,
               @JsonProperty("CompanyId") String companyId,
               @JsonProperty("BusID") String busId,
               @JsonProperty("PAN") String pan) {
        this.id = id;
        this.dateTime = dateTime;
        this.tapType = tapType;
        this.stop = stop;
        this.companyId = companyId;
        this.busId = busId;
        this.pan = pan;
    }

    public Integer getId() {
        return id;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public TapType getTapType() {
        return tapType;
    }

    public Stop getStop() {
        return stop;
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getBusId() {
        return busId;
    }

    public String getPan() {
        return pan;
    }
}
