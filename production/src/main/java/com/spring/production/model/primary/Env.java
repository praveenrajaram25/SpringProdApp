package com.spring.production.model.primary;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="env")
public class Env {

    @Id
    private String env;


    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }
}
