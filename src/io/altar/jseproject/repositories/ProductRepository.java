package io.altar.jseproject.repositories;

import io.altar.jseproject.model.Product;

public class ProductRepository extends EntityRepository<Product> {
    // TODO: 6/8/2018
    private static final ProductRepository INSTANCE = new ProductRepository();

    private ProductRepository() {

    }

    public static ProductRepository getInstance() {
        return INSTANCE;
    }


}
