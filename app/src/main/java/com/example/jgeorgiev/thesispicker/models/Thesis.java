package com.example.jgeorgiev.thesispicker.models;

/**
 * Created by jgeorgiev on 4/23/17.
 */

public class Thesis {

    public String title;
    public String details;
    public String lead;
    public boolean isPicked;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getLead() {
        return lead;
    }

    public void setLead(String lead) {
        this.lead = lead;
    }

    public boolean isPicked() {
        return isPicked;
    }

    public void setPicked(boolean picked) {
        isPicked = picked;
    }
}
