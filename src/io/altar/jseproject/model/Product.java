package io.altar.jseproject.model;

import java.util.ArrayList;

public class Product extends Entity{

    private ArrayList<Shelf> shelvesList;
    private int discount;
    private double iva;
    private double pvp;

    public Product(int discount, double iva, double pvp, Shelf shelf){
        shelvesList = new ArrayList<>();
        this.discount = discount;
        this.iva = iva;
        this.pvp = pvp;
        if(shelf != null){
            shelvesList.add(shelf);
        }
    }

    public String getShelvesList() {
        String shelves = "";
        for(Shelf s : shelvesList){
            shelves += Long.toString(s.getID()) + "; ";
        }
        return shelves;
    }

    public void addShelf(Shelf shelf){
        shelvesList.add(shelf);
    }

    public void removeShelf(Shelf shelf){
        shelvesList.remove(shelf);
    }

    public void setShelves(ArrayList<Shelf> shelves){
//        if(shelves == null){
//            System.out.println("*******PRATELEIRAS DO PRODUTO INALTERADAS*******");
//            return;
//        }
        shelvesList.clear();
        shelvesList.addAll(shelves);
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
