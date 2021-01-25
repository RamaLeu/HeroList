package com.example.heroapi;

import java.io.Serializable;

public class Hero implements Serializable {
    private String id;
    private String name;
    private String strength;
    private String fullName;
    private String work;



    public Hero(String id, String name, String strength, String fullName, String work) {
        this.id = id;
        this.name = name;
        this.strength = strength;
        this.fullName = fullName;
        this.work = work;
    }

    public Hero(String name, String strength, String fullName, String work) {
        this.name = name;
        this.strength = strength;
        this.fullName = fullName;
        this.work = work;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }
}
