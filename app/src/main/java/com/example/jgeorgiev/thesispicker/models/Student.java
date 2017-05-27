package com.example.jgeorgiev.thesispicker.models;

/**
 * Student model
 * Created by ygeorgiev on 20-May-17.
 */
public class Student {

    private String name;
    private int facultyNumber;
    private String specialty;
    private int adminGroup;
    private boolean isBachelor;
    private Thesis thesis;
    private String reviewer;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFacultyNumber() {
        return facultyNumber;
    }

    public void setFacultyNumber(int facultyNumber) {
        this.facultyNumber = facultyNumber;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public int getAdminGroup() {
        return adminGroup;
    }

    public void setAdminGroup(int adminGroup) {
        this.adminGroup = adminGroup;
    }

    public boolean isBachelor() {
        return isBachelor;
    }

    public void setBachelor(boolean bachelor) {
        isBachelor = bachelor;
    }

    public Thesis getThesis() {
        return thesis;
    }

    public void setThesis(Thesis thesis) {
        this.thesis = thesis;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }
}
