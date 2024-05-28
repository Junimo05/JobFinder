package com.example.jobfinder.data.model;

public class Employee {
    private int employeeID;
    private int userID;
    private String name;
    private int age;
    private String Phone;
    private String Email;
    private String AboutMe;
    private String Hobbies;

    public Employee(int employeeID, int userID, String name, int age, String phone, String email, String aboutMe, String hobbies) {
        this.employeeID = employeeID;
        this.userID = userID;
        this.name = name;
        this.age = age;
        this.Phone = phone;
        this.Email = email;
        this.AboutMe = aboutMe;
        this.Hobbies = hobbies;
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
        return Phone;
    }

    public void setPhone(String phone) {
        this.Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getAboutMe() {
        return AboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.AboutMe = aboutMe;
    }

    public String getHobbies() {
        return Hobbies;
    }

    public void setHobbies(String hobbies) {
        this.Hobbies = hobbies;
    }
}
