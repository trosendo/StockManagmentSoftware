package io.altar.jseproject.controller;

import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.repositories.EntityRepository;
import io.altar.jseproject.repositories.ShelfRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class ShelfService {
    // TODO: 6/8/2018
    static EntityRepository<Shelf> shelfDB = ShelfRepository.getInstance();

    public static ArrayList<String[]> getShelves(){
        Collection<Shelf> collection = shelfDB.getValues();
        ArrayList<String[]> data = new ArrayList<>();
        if(collection.size() == 0){
            return null;
        }
        for(Shelf shelf : collection){
            String temp[] = {Long.toString(shelf.getID()),
                            Integer.toString(shelf.getCapacity()),
                            Double.toString(shelf.getDailyRent()),
                            (shelf.getProduct() == -1) ? "---" : Long.toString(shelf.getProduct())};
            data.add(temp);
        }
        return data;
    }

    public static long createShelf(int capacity, double rent, long productID){
        Shelf s = new Shelf(capacity, rent, productID);
        if(shelfDB.storeEntity(s) != null){
            s.addShelfToProduct(productID);
            return s.getID();
        } else {
            return -1;
        }
    }

    public static void addProductToShelf(long productID, long shelfID){
        Shelf s = shelfDB.getEntity(shelfID);
        if(s != null) {
            s.setProduct(productID);
        }
    }

    public static Set<Long> getIDs(){
        return shelfDB.getKeys();
    }
}
