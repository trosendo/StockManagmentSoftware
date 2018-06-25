package io.altar.jseproject.statemachine.states.productstates;

import io.altar.jseproject.controller.ProductService;
import io.altar.jseproject.statemachine.states.State;
import io.altar.jseproject.statemachine.states.StateType;

public class ProductCreateState implements State {
    @Override
    public StateType executeState() {

        int d = scan("Inserir Desconto: ", Integer.class, false);

        double iva = scan("Inserir IVA: ", Double.class, false);

        double pvp = scan("Inserir PVP: ", Double.class, false);

        String temp = String.valueOf(scan("Adicionar produto à prateleira (ID) [Vazio se não desejar associar um produto]: ", Long.class, true));

        long shelfID = assertValidity(temp);       // NOT ANYMORE ->   may throw NumberFormatException if user enters non-numeric char

        long result = ProductService.createProduct(d, iva, pvp, shelfID);

        if (result != -1) {
            System.out.println("Produto criado com sucesso!");
        } else {
            System.out.println("Ocurreu um erro! Por favor tente novamente.");
        }

        return StateType.AUTOMATIC_TRANSITION;
    }
}
