package io.altar.jseproject.textinterface;

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
    private String header[] = {"ID", "CAPACIDADE", "PREÇO ALUGUER", "PRODUTO PRESENTE"};
    private String leftAlign = "| %-5s | %-15s | %-15s | %-16s |\n";
    private String separator = "+-------+-----------------+-----------------+------------------+";
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
        System.out.println("\nEDIT SHELF MENU");
        System.out.print("Inserir ID: ");
        long id = input.nextLong();
        ////////////////////
        input.nextLine(); // Previous nextLong does not capture \n so the next nextLine is skipped because it captures the \n
        //////////////////// this way we clear the input so no other is skipped
        ////////////////////
        String arr[] = ShelfService.getShelfDetails(id);
        if(arr == null){
            System.out.println("**********NO PRODUCT WITH ID " + id + "**********\n");
            return;
        }
        int currentCapacity = Integer.parseInt(arr[1]);
        double currentDailyRent = Double.parseDouble(arr[2]);
        long currentProductID = Long.parseLong(arr[3]);

        System.out.format("Capacidade atual é %d.\nMudar para [vazio deixa o valor autal]: ", currentCapacity);
        String scan = input.nextLine();
        int capacity = (scan.equals("")) ? currentCapacity : Integer.parseInt(scan);

        System.out.format("Aluguer diário atual é %f.\nMudar para [vazio deixa o valor autal]: ", currentDailyRent);
        scan = input.nextLine();
        double rent = (scan.equals("")) ? currentDailyRent : Double.parseDouble(scan);

        long nextProductID;
        if(currentProductID != -1){
            System.out.format("ID do produto atualmente associado é %d. \nMudar para [vazio desassocia o produto]: ", currentProductID);
        } else {
            System.out.print("ID do produto a associar: ");
        }
        scan = input.nextLine();
        nextProductID = (scan.equals("")) ? -1 : Long.parseLong(scan);

        if(ShelfService.editShelf(id, capacity, rent, nextProductID)){
            System.out.println("PRODUCT SUCESSFULLY EDITED\n");
        } else {
            System.out.println("**********ERROR EDITING PRODUCT**********\n");
        }
    }

    private void getShelfDetails() {
        System.out.println("\nGET SHELF DETAILS");
        System.out.print("Inserir ID: ");
        long id = input.nextLong();
        ArrayList<String[]> temp = new ArrayList<>(1);
        String[] arr = ShelfService.getShelfDetails(id);
        if(arr != null){
            temp.add(arr);
            printTable(header, separator, leftAlign, temp);
        } else {
            System.out.println("Nenhuma prateleira coincide com o ID inserido!");
        }
    }

    private void removeShelf() {
        // TODO: 6/8/2018
        System.out.println("\nREMOVE SHELF");
    }


    private void showShelves() {
        ArrayList<String[]> temp = ShelfService.shelvesToString();
        if(temp == null){
            System.out.println("\nNão há prateleiras no sistema!");
            return;
        }
        printTable(header, separator, leftAlign, temp);
        System.out.format("Showing %d shelves\n", ShelfService.addedShelves());

    }
}
