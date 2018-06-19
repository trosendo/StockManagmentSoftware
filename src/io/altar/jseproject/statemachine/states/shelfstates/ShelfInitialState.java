package io.altar.jseproject.statemachine.states.shelfstates;

import io.altar.jseproject.controller.ShelfService;
import io.altar.jseproject.statemachine.states.State;
import io.altar.jseproject.statemachine.states.StateType;

import java.util.ArrayList;

public class ShelfInitialState implements State {

    private String menu = "1) Criar nova prateleira\n" +
            "2) Editar uma prateleira existente\n" +
            "3) Consultar o detalhe de uma prateleira\n" +
            "4) Remover uma prateleira\n" +
            "5) Voltar ao ecrã anterior";
    static final String header[] = {"ID", "CAPACIDADE", "PREÇO ALUGUER(€)", "PRODUTO PRESENTE"};
    static final String leftAlign = "| %-5s | %-15s | %-15s | %-16s |\n";
    static final String separator = "+-------+-----------------+-----------------+------------------+";

    @Override
    public StateType executeState() {
        ArrayList<String[]> temp = ShelfService.shelvesToString();
        if (temp == null) {
            System.out.println("Não há prateleiras no sistema!");
            System.out.println(menu);
            return StateType.EVENT_TRANSITION;
        }

        printTable(header, separator, leftAlign, temp);
        System.out.format("Showing %d shelves\n", ShelfService.addedShelves());

        System.out.println(menu);

        return StateType.EVENT_TRANSITION;
    }
}
