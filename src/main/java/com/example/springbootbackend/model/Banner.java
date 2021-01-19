package com.example.springbootbackend.model;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "banner")
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private BigDecimal price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    private String content;

    private boolean deleted;

    public Banner() {
    }

    public Banner(String name, BigDecimal price, Category category, String content, boolean deleted) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.content = content;
        this.deleted = deleted;
    }

    public Banner(String name, BigDecimal price, Category category, String content) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.content = content;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}

