package com.example.jgeorgiev.thesispicker.models;

/**
 * Thesis
 * Created by ygeorgiev on 20-May-17.
 */
public class Thesis {

    private String title;
    private String details;
    private String lead;
    private boolean isPicked;

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
