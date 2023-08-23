package org.fathi;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.Root;
import org.fathi.conf.HibernateUtil;
import org.fathi.entities.*;
import org.fathi.services.SaveService;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.*;

import java.util.List;

public class App {
    public static void main(String[] args) {

        SaveService.saveDefaultData();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

//            fetch student that has valid degree
            List<Student> students = session.createQuery
                            ("from Student t where t.degree.isValid", Student.class)
                    .list();
            students.forEach(s -> System.out.println(s.getFirstName() + " " + s.getLastName()));


//            fetch student with parameterized Id
            Query<Student> studentQuery = session.createQuery("from Student s where s.id =: param", Student.class)
                    .setParameter("param", 1);
            Student studentRes = (Student) studentQuery.getSingleResult();
            System.out.println(studentRes.getFirstName() + " " + studentRes.getLastName());

//            fetch courses with namedQuery
            Query<Course> courseQuery = session.createNamedQuery("course.getAll", Course.class);
            List<Course> courses = courseQuery.list();
            courses.stream()
                    .forEach(course -> System.out.println(course.getStudents().size()));

//            fetch phones with join from indirection entity
            TypedQuery<Phone> phones = session.createQuery
                            ("select ph.number from Student s inner JOIN s.phones ph  where s.id = ?1", Phone.class)
                    .setParameter(1, 1);
            phones.getResultList();

//          fetch Item with criteria
            HibernateCriteriaBuilder cb = session.getCriteriaBuilder();
            JpaCriteriaQuery<Item> cr = cb.createQuery(Item.class);
            Root<Item> itemRoot = cr.from(Item.class);
            cr.select(itemRoot);

            Query<Item> query = session.createQuery(cr);
            List<Item> items = query.getResultList();
            items.stream()
                    .forEach(item -> System.out.println(item.toString()));

            JpaPredicate redEq = cb.equal(itemRoot.get("itemName"), "red");
            JpaPredicate color = cb.gt(itemRoot.get("itemPrice"), 1000L);

            JpaPredicate and = cb.and(redEq, color);
            cr.select(itemRoot)
                    .where(and);


            session.createQuery(cr)
                    .list()
                    .stream()
                    .forEach(
                            o -> System.out.println(o.toString())
                    );

//          fetch ColoredItem with criteria
            HibernateCriteriaBuilder cbColoredItem = session.getCriteriaBuilder();
            JpaCriteriaQuery<ColoredItem> qy = cbColoredItem.createQuery(ColoredItem.class);
            JpaRoot<ColoredItem> coloredItem = qy.from(ColoredItem.class);
            //part01
            JpaPredicate cRed = cbColoredItem.equal(coloredItem.get("color"), "red");
            JpaPredicate cBlue = cbColoredItem.equal(coloredItem.get("color"), "blue");
            JpaPredicate or = cbColoredItem.or(cRed, cBlue);

            JpaPredicate gA = cbColoredItem.equal(coloredItem.get("grade"), "A");
            JpaPredicate gB = cbColoredItem.equal(coloredItem.get("grade"), "B");
            JpaPredicate or1 = cbColoredItem.or(gA, gB);

            JpaPredicate andItemColored = cbColoredItem.and(or1, or);
            qy.select(coloredItem).where(andItemColored);

            //part02(same as part 01)
            JpaPredicate colorPredict = coloredItem.get("color").in("red", "blue");
            JpaPredicate gradePredict = coloredItem.get("grade").in("A", "B");

            JpaPredicate colorAndGrade = cbColoredItem.and(colorPredict, gradePredict);

            JpaOrder gradeOder = cbColoredItem.asc(coloredItem.get("grade"));
            qy.orderBy(gradeOder);

            qy.select(coloredItem).
                    where(colorAndGrade);


            Query<ColoredItem> coloredItemQuery = session.createQuery(qy);
            List<ColoredItem> coloredItems = coloredItemQuery.getResultList();
            coloredItems.stream()
                    .forEach(c -> System.out.println(c.toString()));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}