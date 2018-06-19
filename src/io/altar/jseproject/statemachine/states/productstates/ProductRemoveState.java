package io.altar.jseproject.statemachine.states.productstates;

import io.altar.jseproject.controller.ProductService;
import io.altar.jseproject.statemachine.states.State;
import io.altar.jseproject.statemachine.states.StateType;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ProductRemoveState implements State {
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

        if(ProductService.removeProduct(id) != -1){
            System.out.println("Produto removido com sucesso!");
        } else {
            System.out.println("Ocurreu um erro! Por favor tente novamente.");
        }

        return StateType.AUTOMATIC_TRANSITION;
    }
}
