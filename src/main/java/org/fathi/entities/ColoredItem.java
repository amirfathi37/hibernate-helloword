package org.fathi.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "TBL_COLORED_ITEM")
public class ColoredItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String color;
    private String grade;
    private String name;

    public ColoredItem() {
    }

    public ColoredItem(String color, String grade, String name) {
        this.color = color;
        this.grade = grade;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ColoredItem{" +
                "id=" + id +
                ", color='" + color + '\'' +
                ", grade='" + grade + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
