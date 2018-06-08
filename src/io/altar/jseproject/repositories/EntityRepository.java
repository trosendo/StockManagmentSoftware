package io.altar.jseproject.repositories;

import io.altar.jseproject.model.Entity;

import java.util.Collection;
import java.util.LinkedHashMap;

public abstract class EntityRepository<T extends Entity> {
    // TODO: 6/8/2018
    LinkedHashMap<Long, T> database;
    long highestID;

    private long nextID(){
        highestID++;
        return highestID;
    }

    public long createEntity(T obj){
        obj.setID(nextID());
        return highestID;
    }

    public Collection<T> getEntity(){
        return database.values();
    }

    public T getEntity(long id){
        return database.get(id);
    }

    public T removeEntity(long id){
        return database.remove(id); // if no entity is found this returns null
    }
}