package org.fathi.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "TBL_IDENTITY")
public class Identity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "I_LOGIN_DATE")
    private Date loginDate;
    @Column(name = "I_HAS_SUPSIT")
    private Boolean hasSupsit;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_student_id", referencedColumnName = "id")
    private Student student;

    public Identity() {
    }

    public Identity(Date loginDate, Boolean hasSupsit) {
        this.loginDate = loginDate;
        this.hasSupsit = hasSupsit;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public Boolean getHasSupsit() {
        return hasSupsit;
    }

    public void setHasSupsit(Boolean hasSupsit) {
        this.hasSupsit = hasSupsit;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Identity{" +
                "id=" + id +
                ", loginDate=" + loginDate +
                ", hasSupsit=" + hasSupsit +
                ", student=" + student +
                '}';
    }
}
