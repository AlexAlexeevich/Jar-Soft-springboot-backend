package com.example.springbootbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String reqName;

    private boolean deleted;

    @JsonIgnore
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private Set<Banner> banners;

    public Category() {
    }

    public Category(String name, String reqName, boolean deleted) {
        this.name = name;
        this.reqName = reqName;
        this.deleted = deleted;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReqName() {
        return reqName;
    }

    public void setReqName(String reqName) {
        this.reqName = reqName;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Set<Banner> getBanners() {
        return banners;
    }

    public void setBanners(Set<Banner> banners) {
        this.banners = banners;
    }
}
