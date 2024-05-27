package data.model;

public class User {
//    model User {
//        userID   Int       @id @default(autoincrement())
//            username String    @unique
//            password String
//            Employee Employee?
//            Employer Employer?
//    }
    private int userID;
    private String username;
    private String password;
    private Employee employee;
    private Employer employer;

    public User(int userID, String username, String password, Employee employee, Employer employer) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.employee = employee;
        this.employer = employer;
    }
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }
}
