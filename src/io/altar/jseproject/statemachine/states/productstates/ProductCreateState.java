package io.altar.jseproject.statemachine.states.productstates;

import io.altar.jseproject.controller.ProductService;
import io.altar.jseproject.model.Product;
import io.altar.jseproject.statemachine.states.State;
import io.altar.jseproject.statemachine.states.StateType;

import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class ProductCreateState implements State {
    @Override
    public StateType executeState() {

        Scanner input = new Scanner(System.in);

        long result = -1;

        try {
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

            result = ProductService.createProduct(d, iva, pvp, shelfID); // may throw NumberFormatException if user enters non-numeric char
        } catch (InputMismatchException | NumberFormatException ion) {
            //log error
        }

        if(result != -1){
            System.out.println("Produto criado com sucesso!");
        } else {
            System.out.println("Ocurreu um erro! Por favor tente novamente.");
        }

        return StateType.AUTOMATIC_TRANSITION;
    }
}
