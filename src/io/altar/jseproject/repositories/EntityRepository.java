package io.altar.jseproject.repositories;

import io.altar.jseproject.model.Entity;

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

    public T removeEntity(long id){
        return database.remove(id); // if no entity is found this returns null
    }

    public Set<Long> getKeys(){
        return database.keySet();
    }

}
