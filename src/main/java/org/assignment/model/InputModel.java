package org.assignment.model;

public class InputModel {
    private final String departureLocation;
    private final String arrivalLocation;
    private final String flightDate;
    private final String flightClass;
    private final int outputPreference;

    public InputModel(String departureLocation,
                      String arrivalLocation,
                      String flightDate,
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

    public String getFlightDate() {
        return flightDate;
    }

    public String getFlightClass() {
        return flightClass;
    }

    public int getOutputPreference() {
        return outputPreference;
    }
}
