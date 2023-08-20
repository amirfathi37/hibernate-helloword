package org.fathi.services;

import org.fathi.conf.HibernateUtil;
import org.fathi.entities.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SaveService {
    public static void saveDefaultData() {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("programming", 4));
        courses.add(new Course("software", 3));

        Identity identity = new Identity(new Date(), true);
        Department department = new Department("IT", "Qom");
        Degree degree = new Degree(new Date(), true);

        Student student = new Student
                ("Ramesh",
                        "Fadatare",
                        "rameshfadatare@javaguides.com",
                        courses,
                        identity,
                        department,
                        degree,
                        Nationality.IRANIAN);
        degree.setStudent(student);
        identity.setStudent(student);
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(department);
            session.persist(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                e.printStackTrace();
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
