package com.pluralsight;

public class Main {
    public static void main(String[] args) {
        // Asset asset = new Asset("90 inch TV", 2022, 3500);
        House house = new House("5 bedroom", 2023, 400000,
                "123 El Street", 5000, 5);

        // System.out.println(asset.getValue());
        System.out.println(house.getValue());
    }
}
