package com.spring.production.model.secondary;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="rating")
public class EmpRating {

    @Id
    private int id;
    private String name;
    private int punctuality;
    private int workEfficiency;
    private int upskilling;
    private int communication;
    private int discipline;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPunctuality() {
        return punctuality;
    }

    public void setPunctuality(int punctuality) {
        this.punctuality = punctuality;
    }

    public int getWorkEfficiency() {
        return workEfficiency;
    }

    public void setWorkEfficiency(int workEfficiency) {
        this.workEfficiency = workEfficiency;
    }

    public int getUpskilling() {
        return upskilling;
    }

    public void setUpskilling(int upskilling) {
        this.upskilling = upskilling;
    }

    public int getCommunication() {
        return communication;
    }

    public void setCommunication(int communication) {
        this.communication = communication;
    }

    public int getDiscipline() {
        return discipline;
    }

    public void setDiscipline(int discipline) {
        this.discipline = discipline;
    }
}
