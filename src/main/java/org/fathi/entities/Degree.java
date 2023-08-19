package org.fathi.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.Where;

import java.util.Date;

@Entity
@Table(name = "TBL_DEGREE")
@Where(clause = "D_IS_VALID = false")
public class Degree {
    @Id
    private Long id;
    @Column(name = "D_SUBMIT_DATE")
    private Date submitDate;
    @Column(name = "D_IS_VALID")
    private boolean isValid;

    @OneToOne
    @MapsId
    @JoinColumn(name = "STUDENT_ID", referencedColumnName = "id")
    private Student student;

    public Degree() {
    }

    public Degree(Date submitDate, boolean isValid) {
        this.submitDate = submitDate;
        this.isValid = isValid;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Degree{" +
                "id=" + id +
                ", submitDate=" + submitDate +
                ", isValid=" + isValid +
                ", student=" + student +
                '}';
    }
}
