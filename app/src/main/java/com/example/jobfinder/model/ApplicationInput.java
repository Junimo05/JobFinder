package com.example.jobfinder.model;

public class ApplicationInput {
    private int employeeID;
    private int jobID;
    private String Description;

    public ApplicationInput(int employeeID, int jobID, String description) {
        this.employeeID = employeeID;
        this.jobID = jobID;
        Description = description;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getJobID() {
        return jobID;
    }

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
