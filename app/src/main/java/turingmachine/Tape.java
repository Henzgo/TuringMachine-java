package turingmachine;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tape {
    private ArrayList<String> tape = new ArrayList<>();
    private String input;
    private String eingabeWort;
    private String currentSymbol;
    private int tapeHeadPosition;
    private static final Logger LOGGER = Logger.getLogger(Tape.class.getName());

    public Tape(String input) {
        this.input = input;
        initialize(input);
        fillEmpty(input);
        findArrayMiddle();
        converter();
    }

    public void initialize(String input) {
        String[] parts = input.split("111");
        if (parts.length > 1) {
            this.eingabeWort = parts[1];
        } else {
            this.eingabeWort = "";
            LOGGER.log(Level.WARNING, "Es gibt kein Eingabewort!");
        }
    }
    public void converter() {
        String[] stringArray = eingabeWort.split("");
        for (String i : stringArray) {
            tape.add(tapeHeadPosition, i);
        }
    }

    public String getCurrentSymbol() {
        currentSymbol = tape.get(tapeHeadPosition);
        return currentSymbol;
    }

    public void writeSymbol(String symbol) {
        tape.set(tapeHeadPosition, symbol);
    }

    public void moveTapeHead(Direction direction) {
        if (direction == Direction.L) {
            if (tapeHeadPosition > 0) tapeHeadPosition--;
        } else if (direction == Direction.R) {
            if (tapeHeadPosition < tape.size() - 1) tapeHeadPosition++;
            else tape.add("");
        }
    }

    public void printTapeWindow(int windowSize) {
        int start = Math.max(0, tapeHeadPosition - windowSize);
        int end = Math.min(tape.size(), tapeHeadPosition + windowSize);
        for (int i = start; i < end; i++) {
            if (i == tapeHeadPosition) {
                System.out.print("[" + tape.get(i) + "]");
            } else {
                System.out.print(" " + tape.get(i) + " ");
            }
        }
        System.out.println();
    }

    private void fillEmpty(String input) {
        for (int index = 0; index < input.length(); index++) {
            tape.add("");
        }
    }

    private void findArrayMiddle() {
        tapeHeadPosition = tape.size() / 2;
    }

    public int getTapeHeadPosition() {
        return tapeHeadPosition;
    }
    
    public void getTape() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String cell : tape) {
            sb.append("[ ").append(cell).append(" ] ");
        }
        return sb.toString();
    }
}
