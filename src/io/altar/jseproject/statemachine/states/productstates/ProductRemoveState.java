package io.altar.jseproject.statemachine.states.productstates;

import io.altar.jseproject.controller.ProductService;
import io.altar.jseproject.statemachine.states.State;
import io.altar.jseproject.statemachine.states.StateType;

import java.util.Scanner;

public class ProductRemoveState implements State {
    @Override
    public StateType executeState() {
        Scanner input = new Scanner(System.in);

        System.out.print("Inserir ID: ");
        long id = input.nextLong();

        ProductService.removeProduct(id);

        return StateType.AUTOMATIC_TRANSITION;
    }
}
