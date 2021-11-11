package com.example.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "recipient")
public class Recipient {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String inn;

    @Column
    private String name;

    @OneToMany(mappedBy = "recipient", fetch = FetchType.LAZY)
    private Set<Requisite> requisites = new HashSet<>();

    public Recipient() {
    }

    public Recipient(String inn, String name) {
        this.inn = inn;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public Set<Requisite> getRequisites() {
        return requisites;
    }

    public void setRequisites(Set<Requisite> requisites) {
        this.requisites = requisites;
    }

    @Override
    public String toString() {
        return "Recipient{" +
                "id=" + id +
                ", inn='" + inn + '\'' +
                ", name='" + name + '\'' +
                ", requisites=" + requisites +
                '}';
    }
}
