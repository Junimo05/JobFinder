package com.example.jobfinder.model;

public class Post {
    private String jobID;
    private String employerID;
    private String JobTitle;
    private String PostDate;
    private String Description;
    private String SalaryRange;
    private String Requirements;
    public Post(String jobID , String JobTitle, String PostDate, String Description, String SalaryRange, String Requirements) {
        this.jobID = jobID;
        this.JobTitle = JobTitle;
        this.PostDate = PostDate;
        this.Description = Description;
        this.SalaryRange = SalaryRange;
        this.Requirements = Requirements;
    }
    public String getJobID() {
        return jobID;
    }

    public void setJobID(String jobID) {
        this.jobID = jobID;
    }

    public String getEmployerID() {
        return employerID;
    }

    public void setEmployerID(String employerID) {
        this.employerID = employerID;
    }

    public String getJobTitle() {
        return JobTitle;
    }

    public void setJobTitle(String jobTitle) {
        JobTitle = jobTitle;
    }

    public String getPostDate() {
        return PostDate;
    }

    public void setPostDate(String postDate) {
        PostDate = postDate;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getSalaryRange() {
        return SalaryRange;
    }

    public void setSalaryRange(String salaryRange) {
        SalaryRange = salaryRange;
    }

    public String getRequirements() {
        return Requirements;
    }

    public void setRequirements(String requirements) {
        Requirements = requirements;
    }
}
