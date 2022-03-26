package com.horyzonty.api.endpoint.cash;

public class AddIncomeRequest {

    private double value;
//    private String skad;

    public double getValue() {
        return value;
    }

    public AddIncomeRequest setValue(double value) {
        this.value = value;
        return this;
    }
}
