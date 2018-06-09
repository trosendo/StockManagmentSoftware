package io.altar.jseproject.textinterface;

import io.altar.jseproject.controller.ProductService;

import java.util.ArrayList;
import java.util.Scanner;

public class MainProduct {

    private final String menu = "Por favor selecione uma das seguintes opções:\n" +
                        "1) Criar novo produto\n" +
                        "2) Editar um produto existente\n" +
                        "3) Consultar o detalhe de um produto\n" +
                        "4) Remover um produto\n" +
                        "5) Voltar ao ecrã anterior";
    private final int ERROR = -1;
    private final int CREATE_PRODUCT = 1;
    private final int EDIT_PRODUCT = 2;
    private final int PRODUCT_DETAILS = 3;
    private final int REMOVE_PRODUCT = 4;
    private final int LAST_MENU = 5;
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
                System.out.println("**********INVALID INPUT**********");
            }
        }
    }

    private void createProduct() {
        System.out.print("Inserir Desconto: ");
        int d = input.nextInt();
        System.out.print("Inserir IVA: ");
        double iva = input.nextDouble();
        System.out.print("Inserir PVP: ");
        double pvp = input.nextDouble();
        if(ProductService.createProduct(d, iva, pvp)){
            System.out.println("PRODUCT SUCCESSFULLY ADDED");
        } else {
            System.out.println("**********ERROR**********");
        }
    }

    private void editProduct() {
        // TODO: 6/8/2018
        System.out.println("EDIT PRODUCT MENU");
        System.out.print("Inserir ID: ");
        long id = input.nextLong();
        ////////////////////
        input.nextLine(); // Previous nextLong does not capture \n so the next nextLine is skipped because it captures the \n
        //////////////////// this way we clear the input so no other is skipped
        ////////////////////
        String arr[] = ProductService.getProductDetails(id);
        if(arr == null){
            System.out.println("**********NO PRODUCT WITH ID " + id + "**********");
            return;
        }
        int currentDiscount = Integer.parseInt(arr[0]);
        double currentIVA = Double.parseDouble(arr[1]);
        double currentPVP = Double.parseDouble(arr[2]);
        System.out.format("Desconto atual é %d. Mudar para [vazio deixa o valor autal]: ", currentDiscount);
        String scan = input.nextLine();
        int discount = (scan.equals("")) ? currentDiscount : Integer.parseInt(scan);

        System.out.format("IVA atual é %f. Mudar para [vazio deixa o valor autal]: ", currentIVA);
        scan = input.nextLine();
        double iva = (scan.equals("")) ? currentIVA : Double.parseDouble(scan);

        System.out.format("PVP atual é %f. Mudar para [vazio deixa o valor autal]: ", currentIVA);
        scan = input.nextLine();
        double pvp = (scan.equals("")) ? currentPVP : Double.parseDouble(scan);

        if(ProductService.editProduct(id, discount, iva, pvp)){
            System.out.println("PRODUCT SUCESSFULLY EDITED");
        } else {
            System.out.println("**********ERROR EDITING PRODUCT**********");
        }
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
        String header[] = {"ID", "DISCOUNT", "IVA", "PVP"};
        String leftAlign = "| %-5s | %-15s | %-15s | %-15s |\n";
        ArrayList<String[]> temp = ProductService.getProducts();
        if(temp == null){
            return;
        }
        String separator = "+-------+-----------------+-----------------+-----------------+";
        System.out.println(separator);
        System.out.format(leftAlign, header);
        System.out.println(separator);
        for(String[] t : temp){
            System.out.format(leftAlign, t[0], t[1], t[2], t[3]);
            System.out.println(separator);
        }
    }

}
