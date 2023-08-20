package org.fathi;

import jakarta.persistence.TypedQuery;
import org.fathi.conf.HibernateUtil;
import org.fathi.entities.Course;
import org.fathi.entities.Degree;
import org.fathi.entities.Student;
import org.fathi.services.SaveService;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class App {
    public static void main(String[] args) {

        SaveService.saveDefaultData();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            List<Student> students = session.createQuery
//                            ("from Student t where t.degree.isValid", Student.class)
//                    .list();
//            students.forEach(s -> System.out.println(s.getFirstName() + " " + s.getLastName()));

            Query<Student> studentQuery = session.createQuery("from Student s where s.id =: iddd", Student.class)
                    .setParameter("iddd", 1);
            Student studentRes = (Student) studentQuery.getSingleResult();
            System.out.println(studentRes.getFirstName() + " " + studentRes.getLastName());


            TypedQuery<Degree> typeQuery = session.createQuery
                    ("from Degree d where d.isValid = true", Degree.class);
            List<Degree> resultList = typeQuery.getResultList();
            resultList.stream()
                    .forEach(degree -> System.out.println(degree.getSubmitDate() + " " + degree.isValid()));

            Query<Course> courseQuery = session.createNamedQuery("course.getAll", Course.class);
            List<Course> courses = courseQuery.list();
            courses.stream()
                    .forEach(course -> System.out.println(course.getStudents().size()));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}