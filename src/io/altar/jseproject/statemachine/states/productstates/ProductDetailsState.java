package io.altar.jseproject.statemachine.states.productstates;

import io.altar.jseproject.controller.ProductService;
import io.altar.jseproject.statemachine.states.State;
import io.altar.jseproject.statemachine.states.StateType;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ProductDetailsState implements State {
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

            arr = ProductService.getProductDetails(id);
        } catch (InputMismatchException e) {
            //log error
        }

        if (arr != null) {
            temp.add(arr);
            printTable(ProductInitialState.header, ProductInitialState.separator, ProductInitialState.leftAlign, temp);
            System.out.println("[Enter] para continuar...");
            input.nextLine();
        } else {
            System.out.println("Nenhum Produto coincide com o ID inserido!");
        }

        return StateType.AUTOMATIC_TRANSITION;
    }
}
