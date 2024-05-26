package data.model;

public class Job {
    private int jobID;
    private int employerID;
    private String jobTitle;
    private String location;
    private String description;
    private String requirements;
    private int persons;
    private String salaryRange;
    private String postDate;
    private String closeDate;

    public Job(int jobID, int employerID, String jobTitle, String location, String description, String requirements, int persons, String salaryRange, String postDate, String closeDate) {
        this.jobID = jobID;
        this.employerID = employerID;
        this.jobTitle = jobTitle;
        this.location = location;
        this.description = description;
        this.requirements = requirements;
        this.persons = persons;
        this.salaryRange = salaryRange;
        this.postDate = postDate;
        this.closeDate = closeDate;
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
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public int getPersons() {
        return persons;
    }

    public void setPersons(int persons) {
        this.persons = persons;
    }

    public String getSalaryRange() {
        return salaryRange;
    }

    public void setSalaryRange(String salaryRange) {
        this.salaryRange = salaryRange;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(String closeDate){
        this.closeDate = closeDate;
    }

}
