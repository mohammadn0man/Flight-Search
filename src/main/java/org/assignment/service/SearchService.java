package org.assignment.service;

import org.assignment.model.FlightModel;
import org.assignment.model.InputModel;

import java.util.List;

public interface SearchService {

    /**
     * preform hql query for given user input and return the result from database
     *
     * @param userInput InputModel object from user
     * @return list of flight matches input
     */
    List<FlightModel> search(InputModel userInput);
}
