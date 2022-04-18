package com.example.lp.tripcalculator.model;

import java.util.Date;

public class Trip {
    private final Date started;
    private Date finished;
    private Long durationSecs;
    private final Stop fromStop;
    private Stop toStop;
    private final String chargeAmount;
    private final String companyId;
    private final String busId;
    private final String pan;
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
