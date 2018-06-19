package io.altar.jseproject.statemachine.states.shelfstates;

import io.altar.jseproject.controller.ShelfService;
import io.altar.jseproject.statemachine.states.State;
import io.altar.jseproject.statemachine.states.StateType;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ShelfDetailsState implements State {
    @Override
    public StateType executeState() {

        Scanner input = new Scanner(System.in);

        ArrayList<String[]> temp = null;
        String[] arr = null;
        try {
            System.out.print("Inserir ID: ");
            long id = input.nextLong();
            input.nextLine();
            temp = new ArrayList<>(1);

            arr = ShelfService.getShelfDetails(id);
        } catch (InputMismatchException e) {
            // log error
        }

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
