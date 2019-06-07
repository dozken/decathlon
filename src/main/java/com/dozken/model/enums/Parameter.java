package com.dozken.model.enums;

public class Parameter {

    private Double a;
    private Double b;
    private Double c;

    public Parameter(double a, double b, double c) {
        this.a = Double.valueOf(a);
        this.b = Double.valueOf(b);
        this.c = Double.valueOf(c);
    }

    public Double getA() {
        return a;
    }

    public Double getB() {
        return b;
    }

    public Double getC() {
        return c;
    }
}
