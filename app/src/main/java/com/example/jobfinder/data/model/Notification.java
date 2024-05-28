package com.example.jobfinder.data.model;

public class Notification {
    private int notifyIO;
    private String notifyTitle;
    private String  notifyDate;

    public Notification(int notifyIO, String notifyTitle, String notifyDate) {
        this.notifyIO = notifyIO;
        this.notifyTitle = notifyTitle;
        this.notifyDate = notifyDate;
    }

    public int getNotifyIO() {
        return notifyIO;
    }

    public void setNotifyIO(int notifyIO) {
        this.notifyIO = notifyIO;
    }

    public String getNotifyTitle() {
        return notifyTitle;
    }

    public void setNotifyTitle(String notifyTitle) {
        this.notifyTitle = notifyTitle;
    }

    public String getNotifyDate() {
        return notifyDate;
    }

    public void setNotifyDate(String notifyDate) {
        this.notifyDate = notifyDate;
    }
}
