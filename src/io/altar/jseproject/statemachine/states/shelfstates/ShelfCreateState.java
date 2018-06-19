package io.altar.jseproject.statemachine.states.shelfstates;

import io.altar.jseproject.controller.ShelfService;
import io.altar.jseproject.statemachine.states.State;
import io.altar.jseproject.statemachine.states.StateType;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ShelfCreateState implements State {

    @Override
    public StateType executeState() {

        Scanner input = new Scanner(System.in);

        long result = -1;
        try {
            System.out.print("Inserir Capacidade: ");
            int capacity = input.nextInt();

            System.out.print("Inserir Preço de Aluger (diário): ");
            double rent = input.nextDouble();

            input.nextLine(); // clear buffer

            System.out.print("Associar Produto [Vazio se não desejar associar um produto]: ");
            String temp = input.nextLine();

            long productID = assertValidity(temp);

            result = ShelfService.createShelf(capacity, rent, productID);
        } catch (InputMismatchException | NumberFormatException ion) {
            // log error
        }

        if (result != -1) {
            System.out.println("Prateleira adicionada com sucesso!");
        } else {
            System.out.println("Ocurreu um erro! Por favor tente novamente.");
        }


        return StateType.AUTOMATIC_TRANSITION;
    }
}
