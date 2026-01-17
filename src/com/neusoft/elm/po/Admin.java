package com.neusoft.elm.po;

/* Admin entity */
public class Admin {
    private int id; // admin id
    private String name; // admin name
    private String password; // admin password

    /* Get id */
    public int getId() {
        return id;
    }

    /* Set id */
    public void setId(int id) {
        this.id = id;
    }

    /* Get name */
    public String getName() {
        return name;
    }

    /* Set name */
    public void setName(String name) {
        this.name = name;
    }

    /* Get password */
    public String getPassword() {
        return password;
    }

    /* Set password */
    public void setPassword(String password) {
        this.password = password;
    }
}
