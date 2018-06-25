package io.altar.jseproject.statemachine.states.productstates;

import io.altar.jseproject.controller.ProductService;
import io.altar.jseproject.statemachine.states.State;
import io.altar.jseproject.statemachine.states.StateType;

public class ProductRemoveState implements State {
    @Override
    public StateType executeState() {

        long id = scan("Inserir ID: ", Long.class, false);

        if (ProductService.getProduct(id) != null) {
            String confirmation = scan("Deseja remover o produto?\n" +
                    "[S = remover; Enter = não remover] ", String.class, false);
            if (confirmation.equalsIgnoreCase("s")) {
                if (ProductService.removeProduct(id) != -1) {
                    System.out.println("Produto removido com sucesso!");
                } else {
                    System.out.println("Ocurreu um erro! Por favor tente novamente.");
                }
            } else {
                System.out.println("Produto não removido!");
            }
        } else {
            System.out.println("Produto Inexistente!");
        }

        return StateType.AUTOMATIC_TRANSITION;
    }
}
