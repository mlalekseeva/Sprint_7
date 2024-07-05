package org.example.models;


public class Courier {

    private String login;
    private String password;
    private String firstName;
    private String id;

    public Courier withLogin(String login) {
        this.login = login;
        return this;
    }

    public Courier withPassword(String password) {
        this.password = password;
        return this;
    }

    public Courier withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }



    public Courier withId(String id) {
        this.id = id;
        return this;
    }






}
