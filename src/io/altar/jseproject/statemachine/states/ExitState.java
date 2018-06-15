package io.altar.jseproject.statemachine.states;

public class ExitState implements State {
    @Override
    public StateType executeState() {
        System.out.println("Obrigado pela preferência!");
        System.exit(0);
        return StateType.AUTOMATIC_TRANSITION;
    }
}
