package turingmachine;

import java.util.Arrays;

public class TuringMachine {
    private Tape tape;
    private TransitionTable transitionTable;
    private String input;


    public TuringMachine(String input) {
        this.input = input;
        // Split the input at "111", use the first part for transitions, the entire string for the tape
        String[] parts = input.split("111", 2); // Split into 2 parts only
        String transitionsPart = parts[0];
        this.tape = new Tape(input); // The Tape class handles its own initialization
        this.transitionTable = new TransitionTable();
        parseAndLoadTransitions(transitionsPart);
    }

    private void parseAndLoadTransitions(String encoded) {
        // Using "11" as the delimiter to separate each transition
        String[] transitions = encoded.split("11");
        for (String transition : transitions) {
            String[] elements = transition.split("1");
            System.out.println(Arrays.toString(elements));
            if (elements.length < 5) continue;  // Ensure we have enough parts to form a valid transition

            String currentState = "q" + (elements[0].length());
            int readSymbol = elements[1].length() - 1; // Assuming zero-based counting
            String nextState = "q" + (elements[2].length());
            int writeSymbol = elements[3].length() - 1; // Assuming zero-based counting
            Direction moveDirection = elements[4].length() == 1 ? Direction.L : Direction.R;

            Transition newTransition = new Transition(nextState, writeSymbol, moveDirection);
            transitionTable.addTransition(currentState, readSymbol, newTransition);
        }
    }

    public static void main(String[] args) {
        TuringMachine test = new TuringMachine("010010000100100110000101000101001100001000100100001001100010010000100000100110001010101001111001");
        test.tape.getTape();
        System.out.println(test.transitionTable.getAllTransitions());
    }
}
