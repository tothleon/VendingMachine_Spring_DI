package org.app.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class Item {
    private int ID;
    private String name;
    private BigDecimal cost;
    private int inventory;

    public Item(int ID, String name, BigDecimal cost, int inventory) {
        this.ID = ID;
        this.name = name;
        this.cost = cost;
        this.inventory = inventory;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }


    @Override
    public String toString() {
        return "Item{" +
                "ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", inventory=" + inventory +
                '}';
    }
}
