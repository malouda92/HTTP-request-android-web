package com.cnaps.ramanandafylovamialy.mylibrary;

public class User {

    private int id;
    private String username;
    private String passord;

    public User() {

    }

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.passord = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassord() {
        return passord;
    }

    public void setPassord(String passord) {
        this.passord = passord;
    }
}
