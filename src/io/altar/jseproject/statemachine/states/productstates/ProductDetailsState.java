package io.altar.jseproject.statemachine.states.productstates;

import io.altar.jseproject.controller.ProductService;
import io.altar.jseproject.statemachine.states.State;
import io.altar.jseproject.statemachine.states.StateType;

import java.util.ArrayList;
import java.util.Scanner;

public class ProductDetailsState implements State {
    @Override
    public StateType executeState() {

        Scanner input = new Scanner(System.in);

        long id = scan("Inserir ID: ", Long.class, false);
        ArrayList<String[]> temp = new ArrayList<>(1);

        String[] arr = ProductService.getProductDetails(id);

        if (arr != null) {
            temp.add(arr);
            printTable(ProductInitialState.header, ProductInitialState.separator, ProductInitialState.leftAlign, temp);
            System.out.print("[Enter] para continuar...");
            input.nextLine();
        } else {
            System.out.println("Nenhum Produto coincide com o ID inserido!");
        }

        return StateType.AUTOMATIC_TRANSITION;
    }
}
