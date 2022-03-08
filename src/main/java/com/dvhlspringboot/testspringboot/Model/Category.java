package com.dvhlspringboot.testspringboot.Model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cid;
    @Column(unique=true)
    private String categoryName;
    public long getCid() {
        return cid;
    }
    public void setCid(long cid) {
        this.cid = cid;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categorytName) {
        this.categoryName = categorytName;
    }
}
