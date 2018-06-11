package io.altar.jseproject.textinterface;

import java.util.Scanner;

public class MainMenu {
    private String menu = "Por favor selecione uma das seguintes opções:\n" +
                        "1) Listar produtos\n" +
                        "2) Listar prateleiras\n" +
                        "3) Sair";
    private final int ERROR = -1;
    private final int LIST_PRODUCTS = 1;
    private final int LIST_SHELVES = 2;
    private final int EXIT = 3;
    Scanner input;

    MainMenu(){
        input = new Scanner(System.in);
    }

    void run(){
        int op = 0;
        while (op != EXIT) {
            System.out.println(menu);
            System.out.print(">>> ");
            try {
                op = Integer.parseInt(input.next());
            } catch (NumberFormatException e) {
                op = ERROR;
                input = new Scanner(System.in);
            }
            if(op == LIST_PRODUCTS){
                productMenu();
            } else if(op == LIST_SHELVES){
                shelfMenu();
            } else if(op != EXIT){
                System.out.println("***INVALID INPUT***");
            }
        }
        System.out.println(" + Exiting");
        System.exit(0);
    }

    private void productMenu(){
        ProductMenu menu = new ProductMenu();
        menu.run();
    }

    private void shelfMenu(){
        ShelfMenu menu = new ShelfMenu();
        menu.run();
    }
}
