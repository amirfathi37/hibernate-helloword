package org.fathi.services;

import org.fathi.conf.HibernateUtil;
import org.fathi.entities.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SaveService {
    public static void saveDefaultData() {

        Workspace parentWS = new Workspace("azerbaijan", null);
        Workspace wSLvl1 = new Workspace("tabriz", parentWS);
        Workspace wSLvl2 = new Workspace("marava", wSLvl1);
        Session session1 = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction1 = session1.beginTransaction();
        session1.persist(parentWS);
        session1.persist(wSLvl1);
        session1.persist(wSLvl2);
        transaction1.commit();
        session1.close();

        Workspace2 parentWS1 = new Workspace2("azerbaijan", null);
        Workspace2 WwSLvl1 = new Workspace2("tabriz", parentWS1);
        Workspace2 WwSLvl2 = new Workspace2("marava", WwSLvl1);
        Session session2 = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction2 = session2.beginTransaction();
        session2.persist(parentWS1);
        session2.persist(WwSLvl1);
        session2.persist(WwSLvl2);
        transaction2.commit();
        session2.close();


        List<Course> courses = new ArrayList<>();
        courses.add(new Course("programming", 4));
        courses.add(new Course("software", 3));

        Identity identity = new Identity(new Date(), true);
        Department department = new Department("IT", "Qom");
        Degree degree = new Degree(new Date(), true);
        Phone phone1 = new Phone();
        phone1.setNumber("09109006726");
        Phone phone2 = new Phone();
        phone2.setNumber("77159365");

        Student student = new Student
                ("amir",
                        "fathi",
                        "fathiamir37@gmail.com",
                        courses,
                        identity,
                        department,
                        degree,
                        Nationality.IRANIAN,
                        Arrays.asList(phone1, phone2));
        degree.setStudent(student);
        identity.setStudent(student);
        phone1.setStudent(student);
        phone2.setStudent(student);
        Transaction transaction = null;

        List<ColoredItem> coloredItems = Arrays.asList(
                new ColoredItem("red", "A", "ksher"),
                new ColoredItem("blue", "C", "ksher1"),
                new ColoredItem("purple", "B", "ksher2"),
                new ColoredItem("red", "B", "ksher3")
        );

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(department);
            session.persist(student);
            transaction.commit();


            for (int i = 0; i < 5; i++) {
                transaction.begin();
                Item item = new Item("name " + i, "desc " + i, 1000L + i);
                session.persist(item);
                transaction.commit();
            }

            for (ColoredItem item : coloredItems) {
                transaction.begin();
                session.persist(item);
                transaction.commit();
            }

        } catch (Exception e) {
            if (transaction != null) {
                e.printStackTrace();
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
