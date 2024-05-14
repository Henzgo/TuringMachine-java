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
            tapeHeadPosition--;
        } else if (direction == Direction.R) {
            tapeHeadPosition++;
        }
        // Todo: Check if ensureCapacity method is needed.
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
