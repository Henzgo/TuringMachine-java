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
        int middleIndex = tape.size() / 2;
        String[] stringArray = eingabeWort.split("");
        int startIndex = middleIndex - stringArray.length / 2;

        for (int i = 0; i < stringArray.length; i++) {
            tape.set(startIndex + i, stringArray[i]);
        }
        tapeHeadPosition = startIndex;
    }

    public String getCurrentSymbol() {
        currentSymbol = tape.get(tapeHeadPosition);
        return currentSymbol;
    }

    // public int getCurrentSymbolIndex() {
    //     String currentSymbol = tape.get(tapeHeadPosition);
    //     return symbolToIndex(currentSymbol);
    // }

    // private int symbolToIndex(String symbol) {
    //     switch (symbol) {
    //         case "_": return 2;
    //         case "0": return 0;
    //         case "1": return 1;
    //         default:
    //             return 0;
    //     }
    // }

    public int getCurrentSymbolIndex() {
        String currentSymbol = getCurrentSymbol();
        return convertSymbolToIndex(currentSymbol);
    }

    private int convertSymbolToIndex(String symbol) {
        if (symbol.equals("0")) return 0;
        if (symbol.equals("1")) return 1;
        if (symbol.equals("_")) return 2;
        if (symbol.charAt(0) >= 'a' && symbol.charAt(0) <= 'c') return symbol.charAt(0) - 'a' + 3;
        return 0;
    }

    public void writeSymbol(String symbol) {
        tape.set(tapeHeadPosition, symbol);
    }

    public void moveTapeHead(Direction direction) {
        if (direction == Direction.L && tapeHeadPosition > 0) {
            if (tapeHeadPosition > 0) tapeHeadPosition--;
        } else if (direction == Direction.R) {
            if (tapeHeadPosition < tape.size() - 1) tapeHeadPosition++;
            else tape.add("_");
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
            tape.add("_");
        }
    }

    public int getTapeHeadPosition() {
        return tapeHeadPosition;
    }
    
    public void getTape() {
        System.out.println(this.toString());
    }

    // @Override
    // public String toString() {
    //     StringBuilder sb = new StringBuilder();
    //     for (String cell : tape) {
    //         sb.append("[ ").append(cell).append(" ] ");
    //     }
    //     return sb.toString();
    // }
}
