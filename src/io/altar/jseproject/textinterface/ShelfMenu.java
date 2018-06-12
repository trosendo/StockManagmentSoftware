package io.altar.jseproject.textinterface;

import io.altar.jseproject.controller.ProductService;
import io.altar.jseproject.controller.ShelfService;

import java.util.ArrayList;
import java.util.Scanner;

public class ShelfMenu extends Menu{
    private String menu =  "Por favor selecione uma das seguintes opções:\n" +
                        "1) Criar nova prateleira\n" +
                        "2) Editar uma prateleira existente\n" +
                        "3) Consultar o detalhe de uma prateleira\n" +
                        "4) Remover uma prateleira\n" +
                        "5) Voltar ao ecrã anterior";
    private final int ERROR = -1;
    private final int CREATE_SHELF = 1;
    private final int EDIT_SHELF = 2;
    private final int SHELF_DETAILS = 3;
    private final int REMOVE_SHELF = 4;
    private final int LAST_MENU = 5;
    Scanner input;

    ShelfMenu(){
        input = new Scanner(System.in);
    }

    void run(){
        int op;
        while (true) {
            showShelves();

            System.out.println(menu);
            System.out.print(">>> ");
            try {
                op = Integer.parseInt(input.next());
            } catch (NumberFormatException e) {
                op = ERROR;
                input = new Scanner(System.in);
            }

            if(op == CREATE_SHELF) {
                createShelf();
            } else if(op == EDIT_SHELF) {
                editShelf();
            } else if(op == SHELF_DETAILS){
                getShelfDetails();
            } else if(op == REMOVE_SHELF){
                removeShelf();
            } else if(op == LAST_MENU){
                break;
            } else {
                System.out.println("***INVALID INPUT***");
            }
        }
    }

    private void createShelf() {
        System.out.println("\nCREATE SHELF MENU");
        System.out.print("Inserir Capacidade: ");
        int capcity = input.nextInt();
        System.out.print("Inserir Preço de Aluger (diário): ");
        double rent = input.nextDouble();
        input.nextLine();
        System.out.print("Associar Produto [Vazio se não desejar associar um produto]: ");
        String temp = input.nextLine();
        long productID = assertValidity(temp);
        long result = ShelfService.createShelf(capcity, rent, productID);
        if(result != -1){
            System.out.println("SHELF SUCCESSFULLY ADDED\n");
        } else {
            System.out.println("**********ERROR**********\n");
        }

    }

    private void editShelf() {
        // TODO: 6/8/2018
        System.out.println("\nEDIT SHELF MENU");
    }

    private void getShelfDetails() {
        // TODO: 6/8/2018
        System.out.println("\nGET SHELF DETAILS");
    }

    private void removeShelf() {
        // TODO: 6/8/2018
        System.out.println("\nREMOVE SHELF");
    }


    private void showShelves() {
        String header[] = {"ID", "CAPACIDADE", "PREÇO ALUGUER", "PRODUTO PRESENTE"};
        String leftAlign = "| %-5s | %-15s | %-15s | %-16s |\n";
        ArrayList<String[]> temp = ShelfService.shelvesToString();
        if(temp == null){
            System.out.println("\nNão há prateleiras no sistema!");
            return;
        }
        String separator = "+-------+-----------------+-----------------+------------------+";
        System.out.println("\n" + separator);
        System.out.format(leftAlign, header);
        System.out.println(separator);
        for(String[] t : temp){
            System.out.format(leftAlign, t[0], t[1], t[2], t[3]);
            System.out.println(separator);
        }
        System.out.format("Showing %d shelves\n", ShelfService.addedShelves());

    }
}
