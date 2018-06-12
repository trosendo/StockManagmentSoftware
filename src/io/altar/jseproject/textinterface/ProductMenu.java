package io.altar.jseproject.textinterface;

import io.altar.jseproject.controller.ProductService;

import java.util.ArrayList;
import java.util.Scanner;

public class ProductMenu extends Menu{

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
    private String header[] = {"ID", "DESCONTO", "IVA", "PVP", "PRATELEIRA"};
    private String leftAlign = "| %-5s | %-15s | %-15s | %-15s | %-15s |\n";
    private String separator = "+-------+-----------------+-----------------+-----------------+-----------------+";
    Scanner input;

    ProductMenu(){
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
        System.out.print("\nInserir Desconto: ");
        int d = input.nextInt();
        System.out.print("Inserir IVA: ");
        double iva = input.nextDouble();
        System.out.print("Inserir PVP: ");
        double pvp = input.nextDouble();
        input.nextLine();
        System.out.print("Adicionar produto à prateleira (ID) [Vazio se não desejar associar um produto]: ");
        String temp = input.nextLine();
        long shelfID = assertValidity(temp);
        long result = ProductService.createProduct(d, iva, pvp, shelfID);
        if(result != -1){
            System.out.println("PRODUCT SUCCESSFULLY ADDED\n");
        } else {
            System.out.println("**********ERROR**********\n");
        }
    }

    private void editProduct() {
        System.out.println("\nEDIT PRODUCT MENU");
        System.out.print("Inserir ID: ");
        long id = input.nextLong();
        ////////////////////
        input.nextLine(); // Previous nextLong does not capture \n so the next nextLine is skipped because it captures the \n
        //////////////////// this way we clear the input so no other is skipped
        ////////////////////
        String arr[] = ProductService.getProductDetails(id);
        if(arr == null){
            System.out.println("**********NO PRODUCT WITH ID " + id + "**********\n");
            return;
        }
        int currentDiscount = Integer.parseInt(arr[0]);
        double currentIVA = Double.parseDouble(arr[1]);
        double currentPVP = Double.parseDouble(arr[2]);
        String shelves = arr[3];

        System.out.format("Desconto atual é %d.\nMudar para [vazio deixa o valor autal]: ", currentDiscount);
        String scan = input.nextLine();
        int discount = (scan.equals("")) ? currentDiscount : Integer.parseInt(scan);

        System.out.format("IVA atual é %f.\nMudar para [vazio deixa o valor autal]: ", currentIVA);
        scan = input.nextLine();
        double iva = (scan.equals("")) ? currentIVA : Double.parseDouble(scan);

        System.out.format("PVP atual é %f. \nMudar para [vazio deixa o valor autal]: ", currentIVA);
        scan = input.nextLine();
        double pvp = (scan.equals("")) ? currentPVP : Double.parseDouble(scan);

        System.out.format("Prateleira/s [%s].\nMudar para [vazio desassocia todas as parteleiras]\n" +
                                    "[em caso de múltiplas, separar com espaços. Por exemplo: \"1 2 3\"]: ", shelves);
        scan = input.nextLine();
        String[] newShelves = (scan.equals("")) ? null : scan.split(" ");

        if(ProductService.editProduct(id, discount, iva, pvp, newShelves)){
            System.out.println("PRODUCT SUCESSFULLY EDITED\n");
        } else {
            System.out.println("**********ERROR EDITING PRODUCT**********\n");
        }
    }

    private void getProductDetails() {
        // TODO: 6/8/2018
        System.out.println("\nGET PRODUCT DETAILS");
    }

    private void removeProduct() {
        // TODO: 6/8/2018
        System.out.println("\nREMOVE PRODUCT");
    }


    private void showProducts() {
        ArrayList<String[]> temp = ProductService.productsToString();
        if(temp == null){
            System.out.println("\nNão há produtos no sistema!");
            return;
        }
        printTable(header, separator, leftAlign, temp);
        System.out.format("Showing %d products\n", ProductService.addedProducts());
    }

}
