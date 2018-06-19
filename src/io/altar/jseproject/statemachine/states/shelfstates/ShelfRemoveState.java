package io.altar.jseproject.statemachine.states.shelfstates;

import io.altar.jseproject.controller.ShelfService;
import io.altar.jseproject.statemachine.states.State;
import io.altar.jseproject.statemachine.states.StateType;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ShelfRemoveState implements State {
    @Override
    public StateType executeState() {

        Scanner input = new Scanner(System.in);

        long id = -1;
        try {
            System.out.print("Inserir ID: ");
            id = input.nextLong();
        } catch (InputMismatchException e) {
            // log error
        }

        if(ShelfService.removeShelf(id) != -1){
            System.out.println("Prateleira removida com sucesso!");
        } else {
            System.out.println("Ocurreu um erro! Por favor tente novamente.");
        }

        return StateType.AUTOMATIC_TRANSITION;
    }
}
