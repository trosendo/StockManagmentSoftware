package io.altar.jseproject.statemachine.states.shelfstates;

import io.altar.jseproject.controller.ShelfService;
import io.altar.jseproject.statemachine.states.State;
import io.altar.jseproject.statemachine.states.StateType;

import java.util.Scanner;

public class ShelfEditState implements State {
    @Override
    public StateType executeState() {

        Scanner input = new Scanner(System.in);

        long id = scan("Inserir ID: ", Long.class, false);

        String arr[] = ShelfService.getShelfDetails(id);
        if (arr == null) {
            System.out.println("Nenhuma prateleira coincide com o ID inserido!");
            return StateType.AUTOMATIC_TRANSITION;
        }

        int currentCapacity = Integer.parseInt(arr[1]);
        double currentDailyRent = Double.parseDouble(arr[2]);
        long currentProductID = Long.parseLong(arr[3]);

        int capacity = scan("Capacidade atual é " + currentCapacity + ".\nMudar para [vazio deixa o valor autal]: ", Integer.class, true);
        capacity = (capacity == -1) ? currentCapacity : capacity;

        double rent = scan("Aluguer diário atual é  " + currentDailyRent + ".\nMudar para [vazio deixa o valor autal]: ", Double.class, true);
        rent = (rent == -1) ? currentDailyRent : rent;

        if (currentProductID != -1) {
            System.out.format("ID do produto atualmente associado é %d. \nMudar para [vazio desassocia o produto]: ", currentProductID);
        } else {
            System.out.print("ID do produto a associar: ");
        }

        long nextProductID = scan("", Long.class, false);


        if (ShelfService.editShelf(id, capacity, rent, nextProductID)) {
            System.out.println("Prateleira criada com sucesso!");
        } else {
            System.out.println("Ocurreu um erro! Por favor tente novamente.");
        }

        return StateType.AUTOMATIC_TRANSITION;
    }
}
