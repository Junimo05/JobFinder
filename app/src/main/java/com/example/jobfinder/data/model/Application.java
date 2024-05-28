package com.example.jobfinder.data.model;

public class Application {
    private int appID;
    private int employeeID;
    private int jobID;
    private String Description;
    private String applyDate;
    private String Status;

    public Application(int appID, int employeeID, int jobID, String description, String applyDate, String status) {
        this.appID = appID;
        this.employeeID = employeeID;
        this.jobID = jobID;
        this.Description = description;
        this.applyDate = applyDate;
        this.Status = status;
    }

    public int getAppID() {
        return appID;
    }

    public void setAppID(int appID) {
        this.appID = appID;
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
        this.Description = description;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        this.Status = status;
    }
}
