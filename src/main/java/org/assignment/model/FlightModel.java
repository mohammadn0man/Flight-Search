package org.assignment.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "flight_details")
public class FlightModel {
    @Id
    @CsvBindByName(column = "FLIGHT_NO")
    private String flightNo;
    @CsvBindByName(column = "DEP_LOC")
    private String departureLocation;
    @CsvBindByName(column = "ARR_LOC")
    private String arrivalLocation;
    @CsvDate(value = "dd-MM-yyyy")
    @CsvBindByName(column = "VALID_TILL")
    private Date validTill;
    @CsvBindByName(column = "FLIGHT_TIME")
    private String flightTime;
    @CsvBindByName(column = "FLIGHT_DUR")
    private String flightDuration;
    @CsvBindByName(column = "FARE")
    private String fare;
    @CsvBindByName(column = "SEAT_AVAILABILITY")
    private String seatAvailability;
    @CsvBindByName(column = "CLASS")
    private String flightClass;

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public void setArrivalLocation(String arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    public void setValidTill(Date validTill) {
        this.validTill = validTill;
    }

    public void setFlightTime(String flightTime) {
        this.flightTime = flightTime;
    }

    public void setFlightDuration(String flightDuration) {
        this.flightDuration = flightDuration;
    }

    public void setFare(String fare) {
        this.fare = fare;
    }

    public void setFlightClass(String flightClass) {
        this.flightClass = flightClass;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public String getArrivalLocation() {
        return arrivalLocation;
    }

    public Date getValidTill() {
        return validTill;
    }

    public String getFlightTime() {
        return flightTime;
    }

    public String getSeatAvailability() {
        return seatAvailability;
    }

    public void setSeatAvailability(String seatAvailability) {
        this.seatAvailability = seatAvailability;
    }

    public String getFlightDuration() {
        return flightDuration;
    }

    public String getFare() {
        return fare;
    }

    public String getFlightClass() {
        return flightClass;
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightNo,
                departureLocation,
                arrivalLocation,
                validTill,
                flightTime,
                flightDuration,
                fare,
                seatAvailability,
                flightClass);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightModel that = (FlightModel) o;
        return seatAvailability == that.seatAvailability
                && flightClass == that.flightClass
                && flightNo.equals(that.flightNo)
                && departureLocation.equals(that.departureLocation)
                && arrivalLocation.equals(that.arrivalLocation)
                && validTill.equals(that.validTill)
                && flightTime.equals(that.flightTime)
                && flightDuration.equals(that.flightDuration)
                && fare.equals(that.fare);
    }

    /**
     * prints the object data in formatted fashion
     * @param rateOfFare for business it is 140% else for economy 100%
     */
    public void print(float rateOfFare) {
        System.out.println(flightNo +
                "\t|\t" + departureLocation +
                "\t|\t" + arrivalLocation +
                "\t|\t" + validTill +
                "\t|\t" + flightTime +
                "\t|\t" + flightDuration +
                "\t|\t" + rateOfFare * Float.parseFloat(fare));
    }

    @Override
    public String toString() {
        return "FlightModel{\n" +
                "\tflightNo='" + flightNo + "\'\n" +
                "\tdepartureLocation='" + departureLocation + "\'\n" +
                "\tarrivalLocation='" + arrivalLocation + "\'\n" +
                "\tvalidTill='" + validTill + "\'\n" +
                "\tflightTime='" + flightTime + "\'\n" +
                "\tflightDuration='" + flightDuration + "\'\n" +
                "\tfare='" + fare + "\'\n" +
                "\tseatAvailability='" + seatAvailability + "\'\n" +
                "\tflightClass=" + flightClass + "\'\n" +
                '}' + "\'\n";
    }
}
