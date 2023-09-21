package org.fathi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tbl_workspace")
public class Workspace2 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @ManyToOne
    @JsonIgnore
    private Workspace2 parent;
    @OneToMany(mappedBy = "parent" , fetch = FetchType.EAGER)
    private List<Workspace2> childs;

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

    public Workspace2 getParent() {
        return parent;
    }

    public void setParent(Workspace2 parent) {
        this.parent = parent;
    }

    public List<Workspace2> getChilds() {
        return childs;
    }

    public void setChilds(List<Workspace2> childs) {
        this.childs = childs;
    }

    public Workspace2() {
    }

    public Workspace2(String name, Workspace2 parent) {
        this.name = name;
        this.parent = parent;
    }
}
