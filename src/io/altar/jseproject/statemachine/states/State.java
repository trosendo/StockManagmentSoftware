package io.altar.jseproject.statemachine.states;

import java.util.ArrayList;

public interface State {
    public StateType executeState();

    default long assertValidity(String temp) {
        long productID;
        if (temp.equals("")) {
            productID = -1;
        } else {
            productID = Long.parseLong(temp);
        }
        return productID;
    }

    default void printTable(String[] header, String separator, String format, ArrayList<String[]> content) {
        System.out.println("\n" + separator);
        System.out.format(format, (Object[]) header);
        System.out.println(separator);
        for (String[] t : content) {
            if (t[3].equals("-1")) {
                t[3] = "---";
            }
            System.out.format(format, (Object[]) t);
            System.out.println(separator);
        }
    }
}
