package io.altar.jseproject.statemachine.states;

public class InitialState implements State {
    private String menu = "1) Listar produtos\n" +
            "2) Listar prateleiras\n" +
            "3) Sair";

    @Override
    public StateType executeState() {
        System.out.println(menu);
        return StateType.EVENT_TRANSITION;
    }
}
