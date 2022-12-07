package org.app.dto;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Change {

    private BigDecimal change;

    public Change(BigDecimal change) {
        this.change = change;
    }

    public static Map<String, Integer> calculateChange(@NotNull BigDecimal changeInCash ) {
        changeInCash = changeInCash.multiply(BigDecimal.valueOf(100));
        Map<String, Integer> change = new HashMap<>();

        int eur = changeInCash.divide(Cents.EUR.getValue()).intValue();
        changeInCash = changeInCash.remainder(Cents.EUR.getValue());
        change.put(Cents.EUR.toString(), eur);

        int c50 = changeInCash.divide(Cents.C50.getValue()).intValue();
        changeInCash = changeInCash.remainder(Cents.C50.getValue());
        change.put(Cents.C50.toString(), c50);

        int c20 = changeInCash.divide(Cents.C20.getValue()).intValue();
        changeInCash = changeInCash.remainder(Cents.C20.getValue());
        change.put(Cents.C20.toString(), c20);

        int c10 = changeInCash.divide(Cents.C10.getValue()).intValue();
        changeInCash = changeInCash.remainder(Cents.C10.getValue());
        change.put(Cents.C10.toString(), c10);

        int c5 = changeInCash.divide(Cents.C5.getValue()).intValue();
        changeInCash = changeInCash.remainder(Cents.C5.getValue());
        change.put(Cents.C5.toString(), c5);

        int c2 = changeInCash.divide(Cents.C2.getValue()).intValue();
        changeInCash = changeInCash.remainder(Cents.C2.getValue());
        change.put(Cents.C2.toString(), c2);

        int c1 = changeInCash.divide(Cents.C1.getValue()).intValue();
        changeInCash = changeInCash.remainder(Cents.C1.getValue());
        change.put(Cents.C1.toString(), c1);

        // return only coins that have value, not zero
        return change.entrySet().stream()
                .filter((coin) -> coin.getValue() > 0)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}