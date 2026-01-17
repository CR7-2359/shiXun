package com.neusoft.elm.po;

import java.math.BigDecimal;

/* Food entity */
public class Food {
    private int id; // food id
    private int businessId; // owner business id
    private String name; // food name
    private BigDecimal price; // food price
    private String description; // food description
    private int status; // food status

    /* Get id */
    public int getId() {
        return id;
    }

    /* Set id */
    public void setId(int id) {
        this.id = id;
    }

    /* Get business id */
    public int getBusinessId() {
        return businessId;
    }

    /* Set business id */
    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    /* Get name */
    public String getName() {
        return name;
    }

    /* Set name */
    public void setName(String name) {
        this.name = name;
    }

    /* Get price */
    public BigDecimal getPrice() {
        return price;
    }

    /* Set price */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /* Get description */
    public String getDescription() {
        return description;
    }

    /* Set description */
    public void setDescription(String description) {
        this.description = description;
    }

    /* Get status */
    public int getStatus() {
        return status;
    }

    /* Set status */
    public void setStatus(int status) {
        this.status = status;
    }
}
