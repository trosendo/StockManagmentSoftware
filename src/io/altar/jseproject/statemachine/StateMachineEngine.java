package io.altar.jseproject.statemachine;

import io.altar.jseproject.statemachine.states.*;
import io.altar.jseproject.statemachine.states.productstates.*;
import io.altar.jseproject.statemachine.states.shelfstates.*;

import java.util.Scanner;

public class StateMachineEngine {

    private State[] machineStates = {
            new ErrorState(),               // 0
            new ExitState(),                // 1
            new InitialState(),             // 2
            new ProductInitialState(),      // 3
            new ShelfInitialState(),        // 4
            new ProductCreateState(),       // 5
            new ProductEditState(),         // 6
            new ProductDetailsState(),      // 7
            new ProductRemoveState(),       // 8
            new ShelfCreateState(),         // 9
            new ShelfEditState(),           // 10
            new ShelfDetailsState(),        // 11
            new ShelfRemoveState()          // 12
    };

    private int[][] transitionMatrix = {
            {2},
            {},
            {0, 3, 4, 1, 0, 0},
            {0, 5, 6, 7, 8, 2},
            {0, 9, 10, 11, 12, 2},
            {3},
            {3},
            {3},
            {3},
            {4},
            {4},
            {4},
            {4},
    };

    private int currentState = 2;

    private StateType nextState(String text) {
        int transitionText = Transition.getTransitionIndex(text);

        // In the case of an error the execution should not return to the ErrorState
        if (currentState != 0) { // store current state (if not error) to know where to go if error happens
            transitionMatrix[0][0] = currentState;
        }

        currentState = transitionMatrix[currentState][transitionText];
        return machineStates[currentState].executeState();
    }

    private void launchStateMachine() {
        String transition = null;

        while (true) {
            if (nextState(transition) == StateType.EVENT_TRANSITION) {
                transition = listenUserInput();
            } else {
                transition = Transition.INVALID.getText();
            }
        }
    }

    private String listenUserInput() {
        System.out.print(">>> ");
        Scanner input = new Scanner(System.in);
        String txt = input.nextLine();
        System.out.println();
        return txt;
    }

    public static void main(String[] args) {
        StateMachineEngine sme = new StateMachineEngine();
        sme.launchStateMachine();
    }

}
