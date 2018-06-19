package io.altar.jseproject.repositories;

import io.altar.jseproject.model.Shelf;

public class ShelfRepository extends EntityRepository<Shelf> {
    // TODO: 6/8/2018
    private static final ShelfRepository INSTANCE = new ShelfRepository();

    private ShelfRepository() {

    }

    public static ShelfRepository getInstance() {
        return INSTANCE;
    }
}
