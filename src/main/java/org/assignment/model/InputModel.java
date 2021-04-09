package org.assignment.model;

import java.util.Date;

public class InputModel {
    private final String departureLocation;
    private final String arrivalLocation;
    private final Date flightDate;
    private final String flightClass;
    private final int outputPreference;

    public InputModel(String departureLocation,
                      String arrivalLocation,
                      Date flightDate,
                      String flightClass,
                      int outputPreference) {
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.flightDate = flightDate;
        this.flightClass = flightClass;
        this.outputPreference = outputPreference;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public String getArrivalLocation() {
        return arrivalLocation;
    }

    public Date getFlightDate() {
        return flightDate;
    }

    public String getFlightClass() {
        return flightClass;
    }

    public int getOutputPreference() {
        return outputPreference;
    }

    @Override
    public String toString() {
        return "InputModel{" +
                "departureLocation='" + departureLocation + '\'' +
                ", arrivalLocation='" + arrivalLocation + '\'' +
                ", flightDate='" + flightDate + '\'' +
                ", flightClass='" + flightClass + '\'' +
                ", outputPreference=" + outputPreference +
                '}';
    }
}
