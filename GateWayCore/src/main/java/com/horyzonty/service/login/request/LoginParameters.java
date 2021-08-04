package com.horyzonty.service.login.request;

public class LoginParameters {

    private String phoneNumber;
    private String freshTokenParam;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFreshTokenParam() {
        return freshTokenParam;
    }

    public void setFreshTokenParam(String freshTokenParam) {
        this.freshTokenParam = freshTokenParam;
    }
}

