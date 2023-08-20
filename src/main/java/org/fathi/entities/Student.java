package org.fathi.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.WhereJoinTable;

import java.util.List;

@Entity
@Table(name = "TBL_STUDENT")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "S_FIRST_NAME")
    private String firstName;

    @Column(name = "S_LAST_NAME")
    private String lastName;

    @Column(name = "S_EMAIL")
    private String email;

    @ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinTable(name = "student_course", joinColumns = @JoinColumn(name = "STUDENT_ID"), inverseJoinColumns = @JoinColumn(name = "COURSE_ID"))
    List<Course> courses;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "student")
    private Identity identity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_department_id", referencedColumnName = "id")
    private Department department;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    @WhereJoinTable(clause = "isActive = true")
    private Degree degree;

    @Enumerated(EnumType.STRING)
    private Nationality nationality;


    public Student() {

    }

    public Student(String firstName,
                   String lastName,
                   String email,
                   List<Course> courses,
                   Identity identity,
                   Department department,
                   Degree degree,
                   Nationality nationality) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.courses = courses;
        this.identity = identity;
        this.department = department;
        this.degree = degree;
        this.nationality = nationality;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", courses=" + courses +
                ", identity=" + identity +
                ", department=" + department +
                ", degree=" + degree +
                ", nationality=" + nationality +
                '}';
    }
}
