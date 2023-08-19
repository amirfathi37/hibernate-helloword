package org.fathi.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "TBL_COURSE")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "C_NAME", length = 20)
    private String name;
    @Column(name = "C_UNIT")
    private Integer unit;

    @ManyToMany(mappedBy = "courses")

    List<Student> students;

    @Override
    public String toString() {
        return "Course{" + "id=" + id + ", name='" + name + '\'' + ", unit=" + unit + ", students=" + students + '}';
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
}
