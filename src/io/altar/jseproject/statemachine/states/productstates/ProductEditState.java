package io.altar.jseproject.statemachine.states.productstates;

import io.altar.jseproject.controller.ProductService;
import io.altar.jseproject.statemachine.states.State;
import io.altar.jseproject.statemachine.states.StateType;

public class ProductEditState implements State {
    @Override
    public StateType executeState() {

        long id = scan("Inserir ID: ", Long.class, false);

        String arr[] = ProductService.getProductDetails(id);
        if (arr == null) {
            System.out.println("Nenhum produto coincide com o ID inserido!");
            return StateType.AUTOMATIC_TRANSITION;
        }

        int currentDiscount = Integer.parseInt(arr[1]);
        double currentIVA = Double.parseDouble(arr[2]);
        double currentPVP = Double.parseDouble(arr[3]);
        String shelves = arr[4];


        int discount = scan("Desconto atual é " + currentDiscount + ".\nMudar para [vazio deixa o valor autal]: ", Integer.class, true);
        discount = (discount == -1) ? currentDiscount : discount;

        double iva = scan("IVA atual é " + currentIVA + ".\nMudar para [vazio deixa o valor autal]: ", Double.class, true);
        iva = (iva == -1) ? currentIVA : iva;

        double pvp = scan("PVP atual é " + currentPVP + ". \nMudar para [vazio deixa o valor autal]: ", Double.class, true);
        pvp = (pvp == -1) ? currentPVP : pvp;

        String newShelvesTemp = scan("Prateleira/s [" + shelves + "].\nMudar para [vazio desassocia todas as parteleiras]\n" +
                "[em caso de múltiplas, separar com espaços. Por exemplo: \"1 2 3\"]: ", String.class, true);
        String[] newShelves = (newShelvesTemp.equals("")) ? null : newShelvesTemp.split(" ");


        if (ProductService.editProduct(id, discount, iva, pvp, newShelves)) {
            System.out.println("Produto editado com sucesso!");
        } else {
            System.out.println("Ocurreu um erro! Por favor tente novamente.");
        }
        return StateType.AUTOMATIC_TRANSITION;
    }
}
