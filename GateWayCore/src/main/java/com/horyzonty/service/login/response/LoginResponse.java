package com.horyzonty.service.login.response;

public class LoginResponse {

    private boolean logged;

    public LoginResponse(boolean logged) {
        this.logged = logged;
    }

    public LoginResponse() {
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }
}

