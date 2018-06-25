package io.altar.jseproject.statemachine.states.shelfstates;

import io.altar.jseproject.controller.ShelfService;
import io.altar.jseproject.statemachine.states.State;
import io.altar.jseproject.statemachine.states.StateType;

import java.util.ArrayList;
import java.util.Scanner;

public class ShelfDetailsState implements State {
    @Override
    public StateType executeState() {

        Scanner input = new Scanner(System.in);

        long id = scan("Inserir ID: ", Long.class, false);
        ArrayList<String[]> temp = new ArrayList<>(1);

        String[] arr = ShelfService.getShelfDetails(id);

        if (arr != null) {
            temp.add(arr);
            printTable(ShelfInitialState.header, ShelfInitialState.separator, ShelfInitialState.leftAlign, temp);
            System.out.println("[Enter] para continuar...");
            input.nextLine();
        } else {
            System.out.println("Nenhuma prateleira coincide com o ID inserido!");
        }

        return StateType.AUTOMATIC_TRANSITION;
    }
}
