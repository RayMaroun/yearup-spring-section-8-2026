package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private String customer;
    private List<LineItem> lineItems;

    public Order(int id, String customer) {
        this.id = id;
        this.customer = customer;
        this.lineItems = new ArrayList<>();
    }

    public void addItem(LineItem lineItem) {
        lineItems.add(lineItem);
    }

    public double getTotal() {
        double total = 0;
        for (LineItem lineItem : lineItems) {
            total += lineItem.getLineTotal();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer='" + customer + '\'' +
                ", lineItems=" + lineItems +
                '}';
    }
}
