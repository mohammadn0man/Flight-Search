package org.assignment.service;

import org.assignment.model.UserModel;
import org.assignment.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public void addUser(UserModel user) {
        Transaction trns = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            trns = session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public boolean authenticateUser(UserModel user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            UserModel userFromDb = (UserModel) session.createQuery("FROM UserModel U WHERE U.userName = :userName")
                    .setParameter("userName", user.getUserName())
                    .uniqueResult();

            if (userFromDb != null && user.getPassword().equals(userFromDb.getPassword())) {
                return true;
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }
}
