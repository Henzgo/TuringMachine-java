package turingmachine;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tape {
    private ArrayList<Integer> tape = new ArrayList<>();
    private String input;
    private String eingabeWort;
    private static final Logger LOGGER = Logger.getLogger(Tape.class.getName());

    public Tape(String input) {
        this.input = input;
        initialize(input);
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
        char[] charArray = eingabeWort.toCharArray();

        for (char i : charArray) {
            int digit = Character.getNumericValue(i);

            tape.add(digit);
        }
    }

    public void getTape() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int cell : tape) {
            sb.append("[ ").append(cell).append(" ] ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String input = "01001010000111101010";

        Tape myTape = new Tape(input);
        myTape.getTape();
    }
}
