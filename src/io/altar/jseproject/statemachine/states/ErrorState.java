package io.altar.jseproject.statemachine.states;

public class ErrorState implements State {
    @Override
    public StateType executeState() {
        System.out.println("Por favor selecione uma das seguintes opções: ");
        return StateType.AUTOMATIC_TRANSITION;
    }
}
