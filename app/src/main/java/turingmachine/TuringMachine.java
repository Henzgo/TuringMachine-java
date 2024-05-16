package turingmachine;

import java.util.Scanner;

public class TuringMachine {
    private Tape tape;
    private TransitionTable transitionTable;
    private String input;
    private int stepCount;
    private String currentState;
    private Scanner scanner = new Scanner(System.in);


    public TuringMachine(String input) {
        this.input = input;
        stepCount = 0;
        this.currentState = "q1";
        String[] parts = input.split("111", 2);
        String transitionsPart = parts[0];
        this.tape = new Tape(input);
        this.transitionTable = new TransitionTable();
        parseAndLoadTransitions(transitionsPart);
    }

    public void execute(boolean stepMode) {
        boolean isActive = true;
        while (isActive && !currentState.equals("HALT")) {
            String currentSymbol = tape.getCurrentSymbol();
            int symbolIndex = tape.getCurrentSymbolIndex();
    
            System.out.println("Current State: " + currentState);
            //System.out.println("Current Symbol (index): " + currentSymbol + " (" + symbolIndex + ")");
            tape.printTapeWindow(30);
            System.out.println("Step Count: " + stepCount);
    
            Transition transition = transitionTable.getTransition(currentState, symbolIndex);
            if (transition == null) {
                System.out.println("No transition found for state " + currentState + " and symbol " + currentSymbol + ". Halting.");
                isActive = false;
                break;
            }
    
            String symbolToWrite = convertIndexToSymbol(transition.getWrite());
            //tape.writeSymbol(String.valueOf(transition.getWrite()));
            tape.writeSymbol(symbolToWrite);
            tape.moveTapeHead(transition.getMove());
            currentState = transition.getNextState();
            stepCount++;
    
            if (stepMode) {
                System.out.println("Press enter to continue...");
                scanner.nextLine();
            }
        }
    
        if (!stepMode) {
            System.out.println("Final Tape State:");
            tape.printTapeWindow(30);
        }
    }

    private String convertIndexToSymbol(int index) {
        if (index == 0) return "0";
        if (index == 1) return "1";
        if (index >= 3) return Character.toString((char) ('a' + index - 3));
        return "_";
    }

    private void parseAndLoadTransitions(String encoded) {
        if (encoded.startsWith("1")) {
            encoded = encoded.substring(1);
        }

        String[] transitions = encoded.split("11");
        System.out.println("Loading transitions...");
        for (String transition : transitions) {
            String[] elements = transition.split("1");
            if (elements.length < 5) continue;
    
            String currentState = "q" + (elements[0].length());
            int readSymbol = elements[1].length() - 1;
            String nextState = "q" + (elements[2].length());
            int writeSymbol = elements[3].length() - 1;
            Direction moveDirection = elements[4].length() == 1 ? Direction.L : Direction.R;
    
            Transition newTransition = new Transition(nextState, writeSymbol, moveDirection);
            transitionTable.addTransition(currentState, readSymbol, newTransition);
            System.out.println("Added transition from " + currentState + " on symbol " + readSymbol + " to " + nextState + " with write " + writeSymbol + " and move " + moveDirection);
        }
        System.out.println("Transitions loaded.");
    }
}
