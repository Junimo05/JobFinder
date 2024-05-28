package com.example.jobfinder.data.model;

public class Employer {
    private int employerID;
    private int userID;
    private String CompanyName;
    private String Industry;
    private String Email;
    private String phone;

    public Employer(int employerID, int userID, String companyName, String industry, String email, String phone) {
        this.employerID = employerID;
        this.userID = userID;
        this.CompanyName = companyName;
        this.Industry = industry;
        this.Email = email;
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
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        this.CompanyName = companyName;
    }

    public String getIndustry() {
        return Industry;
    }

    public void setIndustry(String industry) {
        this.Industry = industry;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
