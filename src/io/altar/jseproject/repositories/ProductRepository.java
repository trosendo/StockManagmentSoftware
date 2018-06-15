package io.altar.jseproject.repositories;

public class ProductRepository extends EntityRepository {
    // TODO: 6/8/2018
    private static final ProductRepository INSTANCE = new ProductRepository();

    private ProductRepository() {

    }

    public static ProductRepository getInstance() {
        return INSTANCE;
    }


}
