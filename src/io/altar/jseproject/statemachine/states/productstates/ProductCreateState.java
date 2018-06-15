package io.altar.jseproject.statemachine.states.productstates;

import io.altar.jseproject.controller.ProductService;
import io.altar.jseproject.statemachine.states.State;
import io.altar.jseproject.statemachine.states.StateType;

import java.util.Scanner;

public class ProductCreateState implements State {
    @Override
    public StateType executeState() {

        Scanner input = new Scanner(System.in);

        System.out.print("\nInserir Desconto: ");
        int d = input.nextInt();

        System.out.print("Inserir IVA: ");
        double iva = input.nextDouble();

        System.out.print("Inserir PVP: ");
        double pvp = input.nextDouble();

        input.nextLine(); // clear buffer

        System.out.print("Adicionar produto à prateleira (ID) [Vazio se não desejar associar um produto]: ");
        String temp = input.nextLine();

        long shelfID = assertValidity(temp);

        long result = ProductService.createProduct(d, iva, pvp, shelfID);

        if (result != -1) {
            System.out.println("PRODUCT SUCCESSFULLY ADDED\n");
        } else {
            System.out.println("**********ERROR**********\n");
        }

        return StateType.AUTOMATIC_TRANSITION;
    }
}
