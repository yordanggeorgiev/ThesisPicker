package com.example.jgeorgiev.thesispicker.models;

/**
 * Created by jgeorgiev on 4/23/17.
 */

public class Student {

    public String name;
    public int facultyNumber;
    public String specialty;
    public int adminGroup;
    public boolean isBachelor;
    public String thesis;
    public String reviewer;

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

    public String getThesis() {
        return thesis;
    }

    public void setThesis(String thesis) {
        this.thesis = thesis;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }
}
