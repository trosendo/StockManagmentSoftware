package io.altar.jseproject.statemachine.states.shelfstates;

import io.altar.jseproject.controller.ShelfService;
import io.altar.jseproject.statemachine.states.State;
import io.altar.jseproject.statemachine.states.StateType;

import java.util.Scanner;

public class ShelfEditState implements State {
    @Override
    public StateType executeState() {

        Scanner input = new Scanner(System.in);

        System.out.print("Inserir ID: ");
        long id = input.nextLong();

        input.nextLine(); // Previous nextLong does not capture \n so the next nextLine is skipped because it captures the \n
        //////////////////// this way we clear the input so no other is skipped

        String arr[] = ShelfService.getShelfDetails(id);
        if (arr == null) {
            System.out.println("**********NO PRODUCT WITH ID " + id + "**********\n");
            return StateType.AUTOMATIC_TRANSITION;
        }

        int currentCapacity = Integer.parseInt(arr[1]);
        double currentDailyRent = Double.parseDouble(arr[2]);
        long currentProductID = Long.parseLong(arr[3]);

        System.out.format("Capacidade atual é %d.\nMudar para [vazio deixa o valor autal]: ", currentCapacity);
        String scan = input.nextLine();
        int capacity = (scan.equals("")) ? currentCapacity : Integer.parseInt(scan);

        System.out.format("Aluguer diário atual é %f.\nMudar para [vazio deixa o valor autal]: ", currentDailyRent);
        scan = input.nextLine();
        double rent = (scan.equals("")) ? currentDailyRent : Double.parseDouble(scan);

        long nextProductID;
        if (currentProductID != -1) {
            System.out.format("ID do produto atualmente associado é %d. \nMudar para [vazio desassocia o produto]: ", currentProductID);
        } else {
            System.out.print("ID do produto a associar: ");
        }
        scan = input.nextLine();

        nextProductID = (scan.equals("")) ? -1 : Long.parseLong(scan);

        if (ShelfService.editShelf(id, capacity, rent, nextProductID)) {
            System.out.println("PRODUCT SUCESSFULLY EDITED\n");
        } else {
            System.out.println("**********ERROR EDITING PRODUCT**********\n");
        }

        return StateType.AUTOMATIC_TRANSITION;
    }
}
