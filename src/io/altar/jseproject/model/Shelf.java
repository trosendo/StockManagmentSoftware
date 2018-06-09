package io.altar.jseproject.model;

import java.util.ArrayList;

public class Shelf extends Entity {

//    IDGenerator idGenerator;
//    int id;
    int capacity;
    Product product;
    double dailyRent;

    public Shelf(){
        capacity = 0;
        dailyRent = 0.0;
    }

    public Shelf(int capacity, Product product, double dailyRent) {
//        idGenerator = new IDGenerator();
//        id = idGenerator.increment();
        this.capacity = capacity;
        this.product = product;
        this.dailyRent = dailyRent;
    }

//    void setID(long id){
//
//    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getDailyRent() {
        return dailyRent;
    }

    public void setDailyRent(double dailyRent) {
        this.dailyRent = dailyRent;
    }

    @Override
    public boolean saveToDB() {
        return false;
    }
}
