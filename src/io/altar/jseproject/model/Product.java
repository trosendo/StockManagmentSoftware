package io.altar.jseproject.model;

import java.util.ArrayList;

public class Product extends Entity{

//    IDGenerator idGenerator;
//    int id;
    ArrayList<Shelf> shelvesList;
    int discount;
    double iva;
    double pvp;

    public Product(Shelf shelf, int discount, double iva, double pvp) {
//        idGenerator = new IDGenerator();
//        id = idGenerator.increment();

        shelvesList = new ArrayList<>();
        shelvesList.add(shelf);

        this.discount = discount;
        this.iva = iva;
        this.pvp = pvp;
    }

//    public int getId() {
//        return id;
//    }

    public ArrayList<Shelf> getShelvesList() {
        return shelvesList;
    }

    public void setShelvesList(ArrayList<Shelf> shelvesList) {
        this.shelvesList = shelvesList;
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
