package io.altar.jseproject.repositories;

import io.altar.jseproject.model.Entity;
import io.altar.jseproject.model.Shelf;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Set;

public abstract class EntityRepository<T extends Entity> {
    // TODO: 6/8/2018
    LinkedHashMap<Long, T> database;
    long highestID;

    public EntityRepository(){
        database = new LinkedHashMap<>();
        highestID = 0;
    }

    private long nextID(){
        highestID++;
        return highestID;
    }

    public T storeEntity(T obj){
        obj.setID(nextID());
        database.put(obj.getID(), obj);
        return database.get(obj.getID());
    }

    public Collection<T> getValues(){
        return database.values();
    }

    public T getEntity(long id){
        return database.get(id);
    }

    public ArrayList<T> getEntities(String[] ids){
        ArrayList<T> arr = new ArrayList<>(ids.length);
        boolean foundAtLeastOne = false;
        for(String id : ids){
            T s = getEntity(Long.parseLong(id));
            if(s != null){
                arr.add(s);
                foundAtLeastOne = true;
            }
        }
        return (foundAtLeastOne) ? arr : null;
    }

    public T removeEntity(long id){
        return database.remove(id); // if no entity is found this returns null
    }

    public Set<Long> getKeys(){
        return database.keySet();
    }

    public int size(){
        return database.size();
    }

}
