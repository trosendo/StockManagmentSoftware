package io.altar.jseproject.statemachine.states.shelfstates;

import io.altar.jseproject.controller.ShelfService;
import io.altar.jseproject.statemachine.states.State;
import io.altar.jseproject.statemachine.states.StateType;

public class ShelfCreateState implements State {

    @Override
    public StateType executeState() {

        int capacity = scan("Inserir Capacidade: ", Integer.class, false);

        double rent = scan("Inserir Preço de Aluger (diário): ", Double.class, false);

        String temp = String.valueOf(scan("Associar Produto [Vazio se não desejar associar um produto]: ", Long.class, true));

        long productID = assertValidity(temp);

        long result = ShelfService.createShelf(capacity, rent, productID);

        if (result != -1) {
            System.out.println("Prateleira adicionada com sucesso!");
        } else {
            System.out.println("Ocurreu um erro! Por favor tente novamente.");
        }

        return StateType.AUTOMATIC_TRANSITION;
    }
}
