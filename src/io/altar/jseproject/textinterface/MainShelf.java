package io.altar.jseproject.textinterface;

import java.util.Scanner;

public class MainShelf {
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

    MainShelf(){
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
        // TODO: 6/8/2018
        System.out.println("CREATE SHELF MENU");

    }

    private void editShelf() {
        // TODO: 6/8/2018
        System.out.println("EDIT SHELF MENU");
    }

    private void getShelfDetails() {
        // TODO: 6/8/2018
        System.out.println("GET SHELF DETAILS");
    }

    private void removeShelf() {
        // TODO: 6/8/2018
        System.out.println("REMOVE SHELF");
    }


    private void showShelves() {
        // TODO: 6/8/2018
        System.out.println("SHELVES GO HERE");
    }
}
