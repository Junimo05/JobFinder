package com.example.jobfinder.data.model;

public class Employer {
    private int employerID;
    private int userID;
    private String companyName;
    private String industry;
    private String email;
    private String phone;

    public Employer(int employerID, int userID, String companyName, String industry, String email, String phone) {
        this.employerID = employerID;
        this.userID = userID;
        this.companyName = companyName;
        this.industry = industry;
        this.email = email;
        this.phone = phone;
    }

    public int getEmployerID() {
        return employerID;
    }

    public void setEmployerID(int employerID) {
        this.employerID = employerID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
