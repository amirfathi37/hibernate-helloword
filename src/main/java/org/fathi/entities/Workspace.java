package org.fathi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tbl_workspace")
public class Workspace {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @ManyToOne
    @JsonIgnore
    private Workspace parent;
    @OneToMany(mappedBy = "parent" , fetch = FetchType.EAGER)
    private List<Workspace> childs;

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

    public Workspace getParent() {
        return parent;
    }

    public void setParent(Workspace parent) {
        this.parent = parent;
    }

    public List<Workspace> getChilds() {
        return childs;
    }

    public void setChilds(List<Workspace> childs) {
        this.childs = childs;
    }

    public Workspace() {
    }

    public Workspace(String name, Workspace parent) {
        this.name = name;
        this.parent = parent;
    }
}
