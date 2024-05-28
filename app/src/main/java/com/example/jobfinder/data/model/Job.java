package com.example.jobfinder.data.model;

public class Job {
    private int jobID;
    private int employerID;
    private String JobTitle;
    private String Location;
    private String Description;
    private String Requirements;
    private int Persons;
    private String SalaryRange;
    private String PostDate;
    private String CloseDate;

    public Job(int jobID, int employerID, String jobTitle, String location, String description, String requirements, int persons, String salaryRange, String postDate, String closeDate) {
        this.jobID = jobID;
        this.employerID = employerID;
        this.JobTitle = jobTitle;
        this.Location = location;
        this.Description = description;
        this.Requirements = requirements;
        this.Persons = persons;
        this.SalaryRange = salaryRange;
        this.PostDate = postDate;
        this.CloseDate = closeDate;
    }

    public int getJobID() {
        return jobID;
    }

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    public int getEmployerID() {
        return employerID;
    }

    public void setEmployerID(int employerID) {
        this.employerID = employerID;
    }

    public String getJobTitle() {
        return JobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.JobTitle = jobTitle;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        this.Location = location;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public String getRequirements() {
        return Requirements;
    }

    public void setRequirements(String requirements) {
        this.Requirements = requirements;
    }

    public int getPersons() {
        return Persons;
    }

    public void setPersons(int persons) {
        this.Persons = persons;
    }

    public String getSalaryRange() {
        return SalaryRange;
    }

    public void setSalaryRange(String salaryRange) {
        this.SalaryRange = salaryRange;
    }

    public String getPostDate() {
        return PostDate;
    }

    public void setPostDate(String postDate) {
        this.PostDate = postDate;
    }


    public String getCloseDate() {
        return CloseDate;
    }

    public void setCloseDate(String closeDate) {
        this.CloseDate = closeDate;
    }

}
