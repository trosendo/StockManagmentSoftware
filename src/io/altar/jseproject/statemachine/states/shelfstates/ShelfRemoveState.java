package io.altar.jseproject.statemachine.states.shelfstates;

import io.altar.jseproject.controller.ShelfService;
import io.altar.jseproject.statemachine.states.State;
import io.altar.jseproject.statemachine.states.StateType;

import java.util.Scanner;

public class ShelfRemoveState implements State {
    @Override
    public StateType executeState() {

        Scanner input = new Scanner(System.in);

        System.out.print("Inserir ID: ");
        long id = input.nextLong();

        ShelfService.removeShelf(id);

        return StateType.AUTOMATIC_TRANSITION;
    }
}
