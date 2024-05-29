package com.example.jobfinder.data.model;

public class JobGroup {
    private int groupID;
    private String jobGroupTitle;
    private String Description;
    private int jobCount;

    public JobGroup(int groupID, String jobGroupTitle, String description, int jobCount) {
        this.groupID = groupID;
        this.jobGroupTitle = jobGroupTitle;
        Description = description;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getJobGroupTitle() {
        return jobGroupTitle;
    }

    public void setJobGroupTitle(String jobGroupTitle) {
        this.jobGroupTitle = jobGroupTitle;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getJobCount() {
        return jobCount;
    }

    public void setJobCount(int jobCount) {
        this.jobCount = jobCount;
    }
}
