package org.assignment.controller;

import org.assignment.model.FlightModel;
import org.assignment.model.InputModel;
import org.assignment.service.SearchService;
import org.assignment.service.SearchServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class SearchController {

    @GetMapping("/search")
    public String getResult(@RequestParam("source") String source,
                            @RequestParam("destination") String destination,
                            @RequestParam("date") String date,
                            @RequestParam("flight_class") String flightClass,
                            @RequestParam("sort") int sort,
                            ModelMap modelMap) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dateParsed = formatter.parse(date);
        InputModel input = new InputModel(destination, source, dateParsed, flightClass, sort);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("org.assignment.service");
        context.refresh();

        SearchService searchService = context.getBean(SearchServiceImpl.class);
        List<FlightModel> result = searchService.search(input);
        modelMap.put("results", result);
        modelMap.put("src", source.toUpperCase());
        modelMap.put("des", destination.toUpperCase());
        modelMap.put("date", date);

        return "result";
    }

}
