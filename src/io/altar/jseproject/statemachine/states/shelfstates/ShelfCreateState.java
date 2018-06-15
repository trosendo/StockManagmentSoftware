package io.altar.jseproject.statemachine.states.shelfstates;

import io.altar.jseproject.controller.ShelfService;
import io.altar.jseproject.statemachine.states.State;
import io.altar.jseproject.statemachine.states.StateType;

import java.util.Scanner;

public class ShelfCreateState implements State {

    @Override
    public StateType executeState() {

        Scanner input = new Scanner(System.in);

        System.out.print("Inserir Capacidade: ");
        int capacity = input.nextInt();

        System.out.print("Inserir Preço de Aluger (diário): ");
        double rent = input.nextDouble();

        input.nextLine(); // clear buffer

        System.out.print("Associar Produto [Vazio se não desejar associar um produto]: ");
        String temp = input.nextLine();

        long productID = assertValidity(temp);

        long result = ShelfService.createShelf(capacity, rent, productID);

        if (result != -1) {
            System.out.println("SHELF SUCCESSFULLY ADDED\n");
        } else {
            System.out.println("**********ERROR**********\n");
        }


        return StateType.AUTOMATIC_TRANSITION;
    }
}
