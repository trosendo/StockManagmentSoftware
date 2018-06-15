package io.altar.jseproject.repositories;

public class ShelfRepository extends EntityRepository {
    // TODO: 6/8/2018
    private static final ShelfRepository INSTANCE = new ShelfRepository();

    private ShelfRepository() {

    }

    public static ShelfRepository getInstance() {
        return INSTANCE;
    }
}
