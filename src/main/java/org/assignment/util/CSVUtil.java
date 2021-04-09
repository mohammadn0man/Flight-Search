package org.assignment.util;

import com.opencsv.bean.CsvToBeanBuilder;
import org.assignment.model.FlightModel;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.FileReader;
import java.util.List;

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
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<FlightModel> list = new CsvToBeanBuilder(new FileReader(filePath))
                    .withType(FlightModel.class)
                    .withSeparator('|')
                    .build().parse();
            transaction = session.beginTransaction();


            for (FlightModel model : list) {
                FlightModel res = (FlightModel) session.createQuery("FROM FlightModel U WHERE U.flightNo = :no")
                        .setParameter("no", model.getFlightNo())
                        .uniqueResult();
                if (res == null) {
                    session.save(model);
                }
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

