package io.altar.jseproject.model;

import io.altar.jseproject.controller.ProductService;

import java.util.ArrayList;

public class Shelf extends Entity {

//    IDGenerator idGenerator;
//    int id;
    int capacity;
    long productID;
    double dailyRent;

    public Shelf(int capacity, double dailyRent, long productID) {
        this.capacity = capacity;
        this.dailyRent = dailyRent;
        if(productID != -1){
            this.productID = productID;
        }
    }

    public void addShelfToProduct(long productID){
        ProductService.addShelfToProduct(productID, getID());
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public long getProduct() {
        if(productID > 0){
            return productID;
        } else {
            return -1;
        }
    }

    public void setProduct(long productID) {
        this.productID = productID;
    }

    public double getDailyRent() {
        return dailyRent;
    }

    public void setDailyRent(double dailyRent) {
        this.dailyRent = dailyRent;
    }
}
