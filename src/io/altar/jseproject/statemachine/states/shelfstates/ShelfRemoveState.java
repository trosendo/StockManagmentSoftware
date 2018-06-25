package io.altar.jseproject.statemachine.states.shelfstates;

import io.altar.jseproject.controller.ShelfService;
import io.altar.jseproject.statemachine.states.State;
import io.altar.jseproject.statemachine.states.StateType;

public class ShelfRemoveState implements State {
    @Override
    public StateType executeState() {

        long id = scan("Inserir ID: ", Long.class, false);

        if (ShelfService.getShelf(id) != null) {
            String confirmation = scan("Deseja remover a prateleira?\n" +
                    "[S = remover; Enter = não remover] ", String.class, false);
            if (confirmation.equalsIgnoreCase("s")) {
                if (ShelfService.removeShelf(id) != -1) {
                    System.out.println("Prateleira removida com sucesso!");
                } else {
                    System.out.println("Ocurreu um erro! Por favor tente novamente.");
                }
            } else {
                System.out.println("Prateleira não removida!");
            }
        } else {
            System.out.println("Prateleira Inexistente!");
        }

        return StateType.AUTOMATIC_TRANSITION;
    }
}
