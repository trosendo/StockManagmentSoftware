package io.altar.jseproject.textinterface;

import java.util.Set;

public class Menu {
    long assertExistence(String temp, Set<Long> list) {
        long productID;
        if(temp.equals("")){
            productID = -1;
        } else {
            productID = Long.parseLong(temp);
            if (!list.contains(productID)) {
                productID = -1;
            }
        }
        return productID;
    }
}
