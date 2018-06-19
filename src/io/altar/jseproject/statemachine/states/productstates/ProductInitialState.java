package io.altar.jseproject.statemachine.states.productstates;

import io.altar.jseproject.controller.ProductService;
import io.altar.jseproject.statemachine.states.State;
import io.altar.jseproject.statemachine.states.StateType;

import java.util.ArrayList;

public class ProductInitialState implements State {

    private final String menu = "1) Criar novo produto\n" +
            "2) Editar um produto existente\n" +
            "3) Consultar o detalhe de um produto\n" +
            "4) Remover um produto\n" +
            "5) Voltar ao ecrã anterior";
    static final String header[] = {"ID", "DESCONTO(%)", "IVA(%)", "PVP(€)", "PRATELEIRA"};
    static final String leftAlign = "| %-5s | %-15s | %-15s | %-15s | %-15s |\n";
    static final String separator = "+-------+-----------------+-----------------+-----------------+-----------------+";

    @Override
    public StateType executeState() {
        ArrayList<String[]> temp = ProductService.productsToString();
        if (temp == null) {
            System.out.println("Não há produtos no sistema!");
            System.out.println(menu);
            return StateType.EVENT_TRANSITION;
        }

        printTable(header, separator, leftAlign, temp);
        System.out.format("Showing %d products\n", ProductService.addedProducts());

        System.out.println(menu);

        return StateType.EVENT_TRANSITION;
    }
}
