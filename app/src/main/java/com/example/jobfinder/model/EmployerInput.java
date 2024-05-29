package com.example.jobfinder.model;

public class EmployerInput {
    private User user;
    private Employer employee;

    public EmployerInput(String username, String password, String CompanyName, String Industry, String Email, String phone) {
        this.user = new User(username, password);
        this.employee = new Employer(CompanyName, Industry, Email, phone);
    }

    public class User {
        private String username;
        private String password;
        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }

    public class Employer {
        private String CompanyName;
        private String Industry;
        private String Email;
        private String Phone;

        public Employer(String CompanyName, String Industry, String Email, String phone) {
            this.CompanyName = CompanyName;
            this.Industry = Industry;
            this.Email = Email;
            this.Phone = phone;
        }
    }

}
