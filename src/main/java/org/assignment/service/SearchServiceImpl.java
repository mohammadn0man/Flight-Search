package org.assignment.service;

import org.assignment.model.FlightModel;
import org.assignment.model.InputModel;
import org.assignment.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Override
    public List<FlightModel> search(InputModel userInput) {
        List<FlightModel> result = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String sort = (userInput.getOutputPreference() == 1) ? "fare" : "fare, flightDuration";
            Query query = session.createQuery("FROM FlightModel  WHERE seatAvailability=:available " +
                    "AND arrivalLocation=:arrival AND departureLocation=:departure AND validTill>=:date AND flightClass LIKE :class" +
                    " ORDER BY " + sort);
            query.setParameter("available", "Y");
            query.setParameter("arrival", userInput.getArrivalLocation());
            query.setParameter("departure", userInput.getDepartureLocation());
            query.setParameter("date", userInput.getFlightDate());
            query.setParameter("class", "%" + userInput.getFlightClass() + "%");
            result = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
