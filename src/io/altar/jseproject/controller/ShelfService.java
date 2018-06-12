package io.altar.jseproject.controller;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.repositories.EntityRepository;
import io.altar.jseproject.repositories.ShelfRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class ShelfService {
    // TODO: 6/8/2018
    static EntityRepository<Shelf> shelfDB = ShelfRepository.getInstance();

    public static ArrayList<String[]> shelvesToString(){
        Collection<Shelf> collection = shelfDB.getValues();
        ArrayList<String[]> data = new ArrayList<>();
        if(collection.size() == 0){
            return null;
        }
        for(Shelf shelf : collection){
            String temp[] = {Long.toString(shelf.getID()),
                            Integer.toString(shelf.getCapacity()),
                            Double.toString(shelf.getDailyRent()),
                            (shelf.getProduct() == null) ? "---" : Long.toString(shelf.getProduct().getID())};
            data.add(temp);
        }
        return data;
    }

    public static long createShelf(int capacity, double rent, long productID){
        Product p = null;
        p = ProductService.getProduct(productID);
        Shelf s = new Shelf(capacity, rent, p);
        if(shelfDB.storeEntity(s) != null){
            if(p != null){
                ProductService.updateProduct(p, s);
            }
            return s.getID();
        } else {
            return -1;
        }
    }

    public static Set<Long> getIDs(){
        return shelfDB.getKeys();
    }

    static Shelf getShelf(long id){
        return shelfDB.getEntity(id);
    }

    public static void updateShelf(Shelf s, Product p) {
        s.setProduct(p);
    }

    public static int addedShelves() {
        return shelfDB.size();
    }

    public static ArrayList<Shelf> getShelves(String[] ids){
        return shelfDB.getEntities(ids);
    }
}
