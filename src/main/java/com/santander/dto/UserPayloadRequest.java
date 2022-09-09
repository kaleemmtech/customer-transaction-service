package com.santander.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class UserPayloadRequest implements Serializable {
    @NotEmpty
    @Email
    private String sender;
    @NotEmpty
    @Email
    private String receiver;
    @NotNull
    @DecimalMin(value = "0", inclusive = false)
    private double amount;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
