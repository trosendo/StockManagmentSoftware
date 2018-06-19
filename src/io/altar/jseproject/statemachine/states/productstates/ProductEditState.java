package io.altar.jseproject.statemachine.states.productstates;

import io.altar.jseproject.controller.ProductService;
import io.altar.jseproject.statemachine.states.State;
import io.altar.jseproject.statemachine.states.StateType;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ProductEditState implements State {
    @Override
    public StateType executeState() {

        Scanner input = new Scanner(System.in);

        long id = -1;
        try {
            System.out.print("Inserir ID: ");
            id = input.nextLong();
        } catch (InputMismatchException e) {
            //log error
        }

        input.nextLine(); // Previous nextLong does not capture \n so the next nextLine is skipped because it captures the \n
        //////////////////// this way we clear the input so no other is skipped

        String arr[] = ProductService.getProductDetails(id);
        if (arr == null) {
            System.out.println("Nenhum produto coincide com o ID inserido!");
            return StateType.AUTOMATIC_TRANSITION;
        }

        int currentDiscount = Integer.parseInt(arr[0]);
        double currentIVA = Double.parseDouble(arr[1]);
        double currentPVP = Double.parseDouble(arr[2]);
        String shelves = arr[3];

        int discount = 0;
        double iva = 0;
        double pvp = 0;
        String[] newShelves = null;
        try {
            System.out.format("Desconto atual é %d.\nMudar para [vazio deixa o valor autal]: ", currentDiscount);
            String scan = input.nextLine();
            discount = (scan.equals("")) ? currentDiscount : Integer.parseInt(scan);

            System.out.format("IVA atual é %f.\nMudar para [vazio deixa o valor autal]: ", currentIVA);
            scan = input.nextLine();
            iva = (scan.equals("")) ? currentIVA : Double.parseDouble(scan);

            System.out.format("PVP atual é %f. \nMudar para [vazio deixa o valor autal]: ", currentIVA);
            scan = input.nextLine();
            pvp = (scan.equals("")) ? currentPVP : Double.parseDouble(scan);

            System.out.format("Prateleira/s [%s].\nMudar para [vazio desassocia todas as parteleiras]\n" +
                    "[em caso de múltiplas, separar com espaços. Por exemplo: \"1 2 3\"]: ", shelves);
            scan = input.nextLine();
            newShelves = (scan.equals("")) ? null : scan.split(" ");
        } catch (NumberFormatException e) {
            //log error
            id = -1;
        }

        if (ProductService.editProduct(id, discount, iva, pvp, newShelves)) {
            System.out.println("Produto editado com sucesso!");
        } else {
            System.out.println("Ocurreu um erro! Por favor tente novamente.");
        }
        return StateType.AUTOMATIC_TRANSITION;
    }
}
