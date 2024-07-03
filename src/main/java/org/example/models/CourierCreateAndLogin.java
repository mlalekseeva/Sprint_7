package org.example.models;


public class CourierCreateAndLogin {

    private String login;
    private String password;
    private String firstName;

    public CourierCreateAndLogin withLogin(String login) {
        this.login = login;
        return this;
    }

    public CourierCreateAndLogin withPassword(String password) {
        this.password = password;
        return this;
    }

    public CourierCreateAndLogin withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }






}
