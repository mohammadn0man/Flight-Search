package org.assignment.util;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import org.assignment.model.FlightModel;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVUtil {

    private CSVUtil() {
    }

    /**
     * read and parse data form csv file and add them to MYSql database using hibernate
     *
     * @param filePath path of csv file to load
     */
    public static void read(String filePath) {
        Transaction transaction = null;
        Map<String, String> mapping = new
                HashMap<>();
        mapping.put("FLIGHT_NO", "flightNo");
        mapping.put("DEP_LOC", "departureLocation");
        mapping.put("ARR_LOC", "arrivalLocation");
        mapping.put("VALID_TILL", "validTill");
        mapping.put("FLIGHT_TIME", "flightTime");
        mapping.put("FLIGHT_DUR", "flightDuration");
        mapping.put("FARE", "fare");
        mapping.put("CLASS", "flightClass");
        mapping.put("SEAT_AVAILABILITY", "seatAvailability");
        CsvToBean<FlightModel> csvToBean = new CsvToBean<>();
        HeaderColumnNameTranslateMappingStrategy<FlightModel> strategy =
                new HeaderColumnNameTranslateMappingStrategy<>();
        strategy.setType(FlightModel.class);
        strategy.setColumnMapping(mapping);
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(filePath))
                .withCSVParser(new CSVParserBuilder().withSeparator('|').build())
                .build();
             Session session = HibernateUtil.getSessionFactory().openSession()) {
            @Deprecated
            List<FlightModel> list = csvToBean.parse(strategy, reader);
            transaction = session.beginTransaction();
            for (FlightModel model : list){
                session.save(model);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

