package org.app.dto;

import java.math.BigDecimal;

public enum Cents {
    C1("1"),
    C2("2"),
    C5("5"),
    C10("10"),
    C20("20"),
    C50("50"),
    EUR("100");

    private BigDecimal value;

    private Cents(String value) {
        this.value = new BigDecimal(value);
    }

    public BigDecimal getValue() {
        return value;
    }

}
