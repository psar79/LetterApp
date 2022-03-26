package com.horyzonty.service.login.response;

public class PhoneNumberAndCodeResponse {

    private boolean logged;

    public PhoneNumberAndCodeResponse(boolean logged) {
        this.logged = logged;
    }

    public PhoneNumberAndCodeResponse() {
    }
    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }
}

