package io.altar.jseproject.statemachine.states;

public enum Transition {
    INVALID(0, ""),
    OPTION_1(1, "1"),
    OPTION_2(2, "2"),
    OPTION_3(3, "3"),
    OPTION_4(4, "4"),
    OPTION_5(5, "5");

    private int index;
    private String text;

    private Transition(int index, String text) {
        this.index = index;
        this.text = text;
    }

    public int getIndex() {
        return index;
    }

    public String getText() {
        return text;
    }

    public static int getTransitionIndex(String text) {
        Transition selected = INVALID;

        for (Transition t : Transition.values()) {
            if (t.getText().equals(text)) {
                selected = t;
                break;
            }
        }

        return selected.getIndex();
    }
}
