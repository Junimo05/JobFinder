package com.example.jobfinder.model;

public class EmployeeInput {

    private User user;
    private Employee employee;

    public EmployeeInput(String username, String password, String name, String age, String phone, String email, String aboutMe, String hobbies) {
        this.user = new User(username, password);
        this.employee = new Employee(name, Integer.parseInt(age), phone, email);
    }

    public class User {
        private String username;
        private String password;
        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }

    public class Employee {
        private String name;
        private int age;
        private String Phone;
        private String Email;
        public Employee(String name, int age, String phone, String email) {
            this.name = name;
            this.age = age;
            this.Phone = phone;
            this.Email = email;
        }
    }

}
