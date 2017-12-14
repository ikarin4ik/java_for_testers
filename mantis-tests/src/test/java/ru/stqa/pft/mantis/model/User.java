package ru.stqa.pft.mantis.model;

public class User {
    String id;
    String email;
    String login;

    public User(String id, String email, String login) {
        this.id = id;
        this.email = email;
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
