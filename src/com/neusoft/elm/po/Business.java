package com.neusoft.elm.po;

/* Business entity */
public class Business {
    private int id; // business id
    private String account; // login account
    private String password; // login password
    private String name; // business name
    private String phone; // contact phone
    private String address; // business address
    private String description; // business description

    /* Get id */
    public int getId() {
        return id;
    }

    /* Set id */
    public void setId(int id) {
        this.id = id;
    }

    /* Get account */
    public String getAccount() {
        return account;
    }

    /* Set account */
    public void setAccount(String account) {
        this.account = account;
    }

    /* Get password */
    public String getPassword() {
        return password;
    }

    /* Set password */
    public void setPassword(String password) {
        this.password = password;
    }

    /* Get name */
    public String getName() {
        return name;
    }

    /* Set name */
    public void setName(String name) {
        this.name = name;
    }

    /* Get phone */
    public String getPhone() {
        return phone;
    }

    /* Set phone */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /* Get address */
    public String getAddress() {
        return address;
    }

    /* Set address */
    public void setAddress(String address) {
        this.address = address;
    }

    /* Get description */
    public String getDescription() {
        return description;
    }

    /* Set description */
    public void setDescription(String description) {
        this.description = description;
    }
}
