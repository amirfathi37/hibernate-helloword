package org.fathi;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.fathi.conf.HibernateUtil;
import org.fathi.entities.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class App {
    public static void main(String[] args) {

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

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Student> students = session.createQuery
                            ("from Student t where t.degree.isValid", Student.class)
                    .list();
            students.forEach(s -> System.out.println(s.getFirstName() + " " + s.getLastName()));
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}