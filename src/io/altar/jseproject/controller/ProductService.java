package io.altar.jseproject.controller;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.repositories.EntityRepository;
import io.altar.jseproject.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class ProductService {

    static EntityRepository<Product> productDB = ProductRepository.getInstance();

    public static ArrayList<String[]> getProducts(){
        Collection<Product> c = productDB.getValues();
        ArrayList<String[]> data = new ArrayList<>();
        if(c.size() == 0){
            return null;
        }
        for(Product p : c){
            String temp[] = {Long.toString(p.getID()), Integer.toString(p.getDiscount()),
                    Double.toString(p.getIVA()), Double.toString(p.getPVP()), p.getShelvesList()};
            data.add(temp);
        }
        return data;
    }

    public static long createProduct(int discount, double iva, double pvp, long shelfID){
        Product p = new Product(discount, iva, pvp, shelfID);
        if(productDB.storeEntity(p) != null){
            p.addShelf(shelfID);
            return p.getID();
        } else {
            return -1;
        }
    }

    public static String[] getProductDetails(long id){
        Product p = productDB.getEntity(id);
        if(p == null){
            return null;
        }
        String arr[] = {Integer.toString(p.getDiscount()), Double.toString(p.getIVA()), Double.toString(p.getPVP())};
        return arr;
    }

    public static boolean editProduct(long id, int discount, double iva, double pvp){
        Product p = productDB.getEntity(id);
        p.setDiscount(discount);
        p.setIVA(iva);
        p.setPVP(pvp);

        return p.getDiscount() == discount && p.getIVA() == iva && p.getPVP() == pvp;
    }

    public static void addShelfToProduct(long productID, long shelfID){
        Product p = productDB.getEntity(productID);
        if(p != null) {
            p.addToShelf(shelfID);
        }
    }

    public static Set<Long> getIDs(){
        return productDB.getKeys();
    }

}
