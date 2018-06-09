package io.altar.jseproject.model;

public abstract class Entity {
    // TODO: 6/8/2018
    long id;

    public void setID(long id){
        this.id = id;
    }

    public long getID(){
        return id;
    }

    public abstract boolean saveToDB();
}
