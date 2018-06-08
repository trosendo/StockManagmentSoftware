package io.altar.jseproject.textinterface;

import io.altar.jseproject.model.Product;

import java.util.Scanner;

public class MainProduct {

    String menu = "Por favor selecione uma das seguintes opções:\n" +
                        "1) Criar novo produto\n" +
                        "2) Editar um produto existente\n" +
                        "3) Consultar o detalhe de um produto\n" +
                        "4) Remover um produto\n" +
                        "5) Voltar ao ecrã anterior";
    final int ERROR = -1;
    final int CREATE_PRODUCT = 1;
    final int EDIT_PRODUCT = 2;
    final int PRODUCT_DETAILS = 3;
    final int REMOVE_PRODUCT = 4;
    final int LAST_MENU = 5;
    Scanner input;

    MainProduct(){
        input = new Scanner(System.in);
    }

    void run(){
        int op;
        while (true) {
            showProducts();

            System.out.println(menu);
            System.out.print(">>> ");
            try {
                op = Integer.parseInt(input.next());
            } catch (NumberFormatException e) {
                op = ERROR;
                input = new Scanner(System.in);
            }

            if(op == CREATE_PRODUCT) {
                createProduct();
            } else if(op == EDIT_PRODUCT) {
                editProduct();
            } else if(op == PRODUCT_DETAILS){
                getProductDetails();
            } else if(op == REMOVE_PRODUCT){
                removeProduct();
            } else if(op == LAST_MENU){
                break;
            } else {
                System.out.println("***INVALID INPUT***");
            }
        }
    }

    private void createProduct() {
        // TODO: 6/8/2018
        System.out.println("CREATE PRODUCT MENU");
    }

    private void editProduct() {
        // TODO: 6/8/2018
        System.out.println("EDIT PRODUCT MENU");
    }

    private void getProductDetails() {
        // TODO: 6/8/2018
        System.out.println("GET PRODUCT DETAILS");
    }

    private void removeProduct() {
        // TODO: 6/8/2018
        System.out.println("REMOVE PRODUCT");
    }


    private void showProducts() {
        System.out.println("PRODUCTS GO HERE");
    }

}
