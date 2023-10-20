package com.pluralsight;

public class Product {
    private String id;
    private String name;
    private float price;
    private String department;

    //constructor
    public Product(String id, String name, float price, String department) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.department = department;
    }
    //getters

    public String getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public float getPrice() {
        return this.price;
    }

    public String getDepartment(){return this.department;}

}
