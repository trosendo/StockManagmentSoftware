package io.altar.jseproject.statemachine.states.shelfstates;

import io.altar.jseproject.controller.ShelfService;
import io.altar.jseproject.statemachine.states.State;
import io.altar.jseproject.statemachine.states.StateType;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ShelfEditState implements State {
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

        input.nextLine(); // Previous nextLong does not capture \n so the next nextLine is skipped because it captures the \n
        //////////////////// this way we clear the input so no other is skipped

        String arr[] = ShelfService.getShelfDetails(id);
        if (arr == null) {
            System.out.println("Nenhuma prateleira coincide com o ID inserido!");
            return StateType.AUTOMATIC_TRANSITION;
        }

        int currentCapacity = Integer.parseInt(arr[1]);
        double currentDailyRent = Double.parseDouble(arr[2]);
        long currentProductID = Long.parseLong(arr[3]);

        int capacity = 0;
        double rent = 0;
        long nextProductID = -1;
        try {
            System.out.format("Capacidade atual é %d.\nMudar para [vazio deixa o valor autal]: ", currentCapacity);
            String scan = input.nextLine();
            capacity = (scan.equals("")) ? currentCapacity : Integer.parseInt(scan);

            System.out.format("Aluguer diário atual é %f.\nMudar para [vazio deixa o valor autal]: ", currentDailyRent);
            scan = input.nextLine();
            rent = (scan.equals("")) ? currentDailyRent : Double.parseDouble(scan);

            if (currentProductID != -1) {
                System.out.format("ID do produto atualmente associado é %d. \nMudar para [vazio desassocia o produto]: ", currentProductID);
            } else {
                System.out.print("ID do produto a associar: ");
            }
            scan = input.nextLine();

            nextProductID = (scan.equals("")) ? -1 : Long.parseLong(scan);
        } catch (NumberFormatException e) {
            // log error
            id = -1;
        }

        if (ShelfService.editShelf(id, capacity, rent, nextProductID)) {
            System.out.println("Prateleira criada com sucesso!");
        } else {
            System.out.println("Ocurreu um erro! Por favor tente novamente.");
        }

        return StateType.AUTOMATIC_TRANSITION;
    }
}
