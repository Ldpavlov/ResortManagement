package com.example.model;

public class Instructor {
    private String name;
    private boolean isSkiInstructor;
    private double hours;

    public Instructor(String name, boolean isSkiInstructor, double hours) {
        this.name = name;
        this.isSkiInstructor = isSkiInstructor;
        this.hours = hours;
    }

    public String getName() {
        return name;
    }

    public boolean isSkiInstructor() {
        return isSkiInstructor;
    }

    public double getHours() {
        return hours;
    }

    @Override
    public String toString() {
        return "Instructor [name=" + name + ", hours=" + hours + "]";
    }
}
