package io.altar.jseproject.model;

import io.altar.jseproject.controller.ProductService;
import io.altar.jseproject.controller.ShelfService;

import java.util.ArrayList;

public class Product extends Entity{

//    IDGenerator idGenerator;
//    int id;
    private ArrayList<Long> shelvesList;
    private int discount;
    private double iva;
    private double pvp;

    public Product(int discount, double iva, double pvp, long shelfID){
        shelvesList = new ArrayList<>();
        this.discount = discount;
        this.iva = iva;
        this.pvp = pvp;
    }

    public String getShelvesList() {
        String shelves = "";
        for(long l : shelvesList){
            shelves += Long.toString(l) + "; ";
        }
        return shelves;
    }

    public void addShelf(long shelfID){
        shelvesList.add(shelfID);
        ShelfService.addProductToShelf(getID(), shelfID);
    }

    public void addToShelf(long shelfID) {
        System.out.println("adding " + shelfID);
        this.shelvesList.add(shelfID);
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public double getIVA() {
        return iva;
    }

    public void setIVA(double iva) {
        this.iva = iva;
    }

    public double getPVP() {
        return pvp;
    }

    public void setPVP(double pvp) {
        this.pvp = pvp;
    }
}
