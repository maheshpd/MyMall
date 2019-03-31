package com.example.mymall.Model;

public class RewardModel {
    private String title, expirydate, coupenBody;

    public RewardModel(String title, String expirydate, String coupenBody) {
        this.title = title;
        this.expirydate = expirydate;
        this.coupenBody = coupenBody;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExpirydate() {
        return expirydate;
    }

    public void setExpirydate(String expirydate) {
        this.expirydate = expirydate;
    }

    public String getCoupenBody() {
        return coupenBody;
    }

    public void setCoupenBody(String coupenBody) {
        this.coupenBody = coupenBody;
    }
}
