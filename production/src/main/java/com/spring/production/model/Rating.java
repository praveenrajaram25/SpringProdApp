package com.spring.production.model;

public class Rating {

    private int punctuality;
    private int workEfficiency;
    private int upskilling;
    private int communication;
    private int discipline;

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
