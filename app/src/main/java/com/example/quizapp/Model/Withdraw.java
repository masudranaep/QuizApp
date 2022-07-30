package com.example.quizapp.Model;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class Withdraw {

    private  String userId;
    private  String emailAddress;
    private  String requestedBy;

    public Withdraw(){

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    public Withdraw(String userId, String emailAddress, String requestedBy) {
        this.userId = userId;
        this.emailAddress = emailAddress;
        this.requestedBy = requestedBy;
    }

    @ServerTimestamp
    private Date createAt;

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
