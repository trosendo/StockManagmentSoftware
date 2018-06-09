package io.altar.jseproject.controller;

import com.sun.scenario.effect.impl.prism.PrDrawable;
import io.altar.jseproject.model.Product;
import io.altar.jseproject.repositories.EntityRepository;
import io.altar.jseproject.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.Collection;

public class ProductService {
    // TODO: 6/8/2018

    static EntityRepository<Product> productDB = new EntityRepository<Product>() {
        @Override
        public Product storeEntity(Product obj) {
            return super.storeEntity(obj);
        }

        @Override
        public Collection<Product> getValues() {
            return super.getValues();
        }

        @Override
        public Product getEntity(long id) {
            return super.getEntity(id);
        }

        @Override
        public Product removeEntity(long id) {
            return super.removeEntity(id);
        }
    };

    public static ArrayList<String[]> getProducts(){
        Collection<Product> c = productDB.getValues();
        ArrayList<String[]> data = new ArrayList<>();
        if(c.size() == 0){
            return null;
        }
        for(Product p : c){
            String temp[] = {Long.toString(p.getID()), Integer.toString(p.getDiscount()),
                    Double.toString(p.getIVA()), Double.toString(p.getPVP())};
            data.add(temp);
        }
        return data;
    }

    public static boolean createProduct(int discount, double iva, double pvp){
        Product p = new Product(discount, iva, pvp);
        return productDB.storeEntity(p) != null;
    }

    public static String[] getProductDetails(long id){
        Product p = productDB.getEntity(id);
        String arr[] = {Integer.toString(p.getDiscount()), Double.toString(p.getIVA()), Double.toString(p.getPVP())};
        return arr;
    }

    public static boolean editProduct(){
        // TODO: 6/9/2018  
        return false;
    }

}
