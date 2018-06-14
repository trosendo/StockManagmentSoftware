package io.altar.jseproject.controller;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.repositories.EntityRepository;
import io.altar.jseproject.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class ProductService {

    static EntityRepository<Product> productDB = ProductRepository.getInstance();

    public static ArrayList<String[]> productsToString(){
        Collection<Product> c = productDB.getValues();
        ArrayList<String[]> data = new ArrayList<>();
        if(c.size() == 0){
            return null;
        }
        for(Product p : c){
//            String s = p.getShelvesString();
//            String temp[] = {Long.toString(p.getID()), Integer.toString(p.getDiscount()),
//                    Double.toString(p.getIVA()), Double.toString(p.getPVP()), (s.equals("") ? "---" : s)};
            data.add(p.getDetails());
        }
        return data;
    }

    public static long createProduct(int discount, double iva, double pvp, long shelfID){
        Shelf s = null;
        s = ShelfService.getShelf(shelfID);
        Product p = new Product(discount, iva, pvp, s);
        if(productDB.storeEntity(p) != null){
            if(s != null){
                ShelfService.updateShelf(s, p);
            }
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
        String arr[] = {Integer.toString(p.getDiscount()),
                Double.toString(p.getIVA()),
                Double.toString(p.getPVP()),
                p.getShelvesString()};
        return arr;
    }

    public static boolean editProduct(long id, int discount, double iva, double pvp, String[] newShelves){
        Product p = productDB.getEntity(id);
        p.setDiscount(discount);
        p.setIVA(iva);
        p.setPVP(pvp);
        ArrayList<Shelf> shelves = null;
        if(newShelves != null) {
            shelves = ShelfService.getShelves(newShelves);
            if(shelves == null){
                System.out.println("*******PRATELEIRAS DO PRODUTO INALTERADAS*******");
            } else {
                for(Shelf s : shelves){
                    Product temp = s.getProduct();
                    if(temp != null){
                        temp.removeShelf(s);
                    }
                    ShelfService.updateShelf(s, p);
                }
                p.setShelves(shelves);
            }
        } else {
            ArrayList<Shelf> arrList = p.getShelvesList();
            for(Shelf s : arrList){
                s.setProduct(null);
            }
            p.removeShelves();
        }
        return p.getDiscount() == discount && p.getIVA() == iva && p.getPVP() == pvp;
    }

    public static Set<Long> getIDs(){
        return productDB.getKeys();
    }

    public static Product getProduct(long productID) {
        return productDB.getEntity(productID);
    }

    public static void updateProduct(Product p, Shelf s) {
        p.addShelf(s);
    }

    public static int addedProducts() {
        return productDB.size();
    }

    public static void removeShelfFromProduct(Product p, Shelf s) {
        if(p != null) {
            p.removeShelf(s);
        }
    }
}
