package com.example.jobfinder.data.model;

public class Employee {
    private int employeeID;
    private int userID;
    private String name;
    private int age;
    private String phone;
    private String email;
    private String aboutMe;
    private String hobbies;

    public Employee(int employeeID, int userID, String name, int age, String phone, String email, String aboutMe, String hobbies) {
        this.employeeID = employeeID;
        this.userID = userID;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.aboutMe = aboutMe;
        this.hobbies = hobbies;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }
}
