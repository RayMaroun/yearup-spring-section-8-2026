package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Order> allOrders = new ArrayList<>();

        Order order1 = new Order(123, "Mason");
        order1.addItem(new LineItem(11, "Computer", 100, 1));
        order1.addItem(new LineItem(12, "Mouse", 15, 2));
        allOrders.add(order1);

        Order order2 = new Order(456, "Rohit");
        order2.addItem(new LineItem(13, "Tv", 200, 1));
        order2.addItem(new LineItem(14, "Keychain", 10, 2));
        allOrders.add(order2);

        Order order3 = new Order(365, "Brandon");
        order3.addItem(new LineItem(15, "ToothBrush", 2, 3));
        order3.addItem(new LineItem(16, "Notebook", 4, 1));
        allOrders.add(order3);

        List<Order> impulseOrders = new ArrayList<>();

        for (Order order : allOrders) {
            if (order.getTotal() < 25) {
                impulseOrders.add(order);
            }
        }

        for (Order impulseOrder : impulseOrders) {
            System.out.println(impulseOrder);
        }

        double salesTotalAllOrders = 0;

        for (Order order : allOrders) {
            salesTotalAllOrders += order.getTotal();
        }

        System.out.println("Total: $" + salesTotalAllOrders);

    }
}
