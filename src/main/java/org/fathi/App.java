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
        Course chemistry = new Course();
        chemistry.setName("chemistry");
        chemistry.setUnit(3);
        Course phisic = new Course();
        phisic.setName("phisic");
        phisic.setUnit(2);

        courses.add(chemistry);
        courses.add(phisic);

        Identity identity = new Identity();
        identity.setLoginDate(new Date());
        identity.setHasSupsit(true);

        Department department = new Department();
        department.setName("IT");
        department.setAddress("Qom");

        Degree degree = new Degree();
        degree.setSubmitDate(new Date());
        degree.setValid(true);


        Student student = new Student
                ("Ramesh",
                        "Fadatare",
                        "rameshfadatare@javaguides.com",
                        courses,
                        identity,
                        department,
                        degree,
                        Nationality.IRANIAN);
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(department);
            degree.setStudent(student);
            session.save(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                e.printStackTrace();
                transaction.rollback();
            }
            e.printStackTrace();
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Student> students = session.createQuery("from Student", Student.class).list();
            students.forEach(s -> System.out.println(s.getFirstName() + " " + s.getCourses().toString()));
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}