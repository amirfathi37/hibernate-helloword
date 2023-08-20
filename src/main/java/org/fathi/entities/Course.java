package org.fathi.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.BatchSize;

import java.util.List;

@Entity
@Table(name = "TBL_COURSE")
@NamedQuery(name = "course.getAll",
        query = "select c from Course c left join fetch c.students"
)
//@NamedQuery(name = "course.getAll",
//        query = "select c from Course c"
//)
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "C_NAME", length = 20)
    private String name;
    @Column(name = "C_UNIT")
    private Integer unit;
    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
//    @BatchSize(size = 10)
    List<Student> students;

    public Course() {
    }

    public Course(String name, Integer unit) {
        this.name = name;
        this.unit = unit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Course{" + "id=" + id + ", name='" + name + '\'' + ", unit=" + unit + '}';
    }
}
