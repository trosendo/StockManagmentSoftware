package io.altar.jseproject.statemachine.states;

import java.util.ArrayList;
import java.util.Scanner;

public interface State {
    StateType executeState();

    default long assertValidity(String temp) {
        long productID;
        if (temp.equals("") || temp.equals("-1")) {
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

    default <T> T scan(String msg, Class<T> type, boolean canBeEmpty) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print(msg);
            String value = input.nextLine();

            try {
                if (type == Integer.class) {
                    if (value.equals("") && canBeEmpty) {
                        value = "-1";
                    }
                    return type.cast(Integer.valueOf(value));
                } else if (type == Double.class) {
                    if (value.equals("") && canBeEmpty) {
                        value = "-1";
                    }
                    return type.cast(Double.valueOf(value));
                } else if (type == Long.class) {
                    if (value.equals("") && canBeEmpty) {
                        value = "-1";
                    }
                    return type.cast(Long.valueOf(value));
                } else if (type == Float.class) {
                    if (value.equals("") && canBeEmpty) {
                        value = "-1";
                    }
                    return type.cast(Float.valueOf(value));
                } else if (type == String.class) {
                    return type.cast(String.valueOf(value));
                }
            } catch (NumberFormatException e) {
                // log error and ask for input again
            }
        }
    }
}
