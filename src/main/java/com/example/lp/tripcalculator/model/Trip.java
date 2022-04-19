package com.example.lp.tripcalculator.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Trip {
    @JsonProperty(value = "Started", index = 0)
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private final Date started;
    @JsonProperty(value = "Finished", index = 1)
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private Date finished;
    @JsonProperty(value = "DurationSecs", index = 2)
    private Long durationSecs;
    @JsonProperty(value = "FromStopId", index = 3)
    private final Stop fromStop;
    @JsonProperty(value = "ToStopId", index = 4)
    private Stop toStop;
    @JsonProperty(value = "ChargeAmount", index = 5)
    private final String chargeAmount;
    @JsonProperty(value = "CompanyId", index = 6)
    private final String companyId;
    @JsonProperty(value = "BusID", index = 7)
    private final String busId;
    @JsonProperty(value = "PAN", index = 8)
    private final String pan;
    @JsonProperty(value = "Status", index = 9)
    private final TripStatus status;

    public Trip(Date started, Stop fromStop, String chargeAmount, String companyId,
                String busId, String pan, TripStatus status) {
        this.started = started;
        this.fromStop = fromStop;
        this.chargeAmount = chargeAmount;
        this.companyId = companyId;
        this.busId = busId;
        this.pan = pan;
        this.status = status;
    }

    public void setFinished(Date finished) {
        this.finished = finished;
    }

    public void setDurationSecs(Long durationSecs) {
        this.durationSecs = durationSecs;
    }

    public void setToStop(Stop toStop) {
        this.toStop = toStop;
    }

    public Date getStarted() {
        return started;
    }

    public Date getFinished() {
        return finished;
    }

    public Long getDurationSecs() {
        return durationSecs;
    }

    public Stop getFromStop() {
        return fromStop;
    }

    public Stop getToStop() {
        return toStop;
    }

    public String getChargeAmount() {
        return chargeAmount;
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

    public TripStatus getStatus() {
        return status;
    }
}
