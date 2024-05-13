package turingmachine;

import java.util.HashMap;
import java.util.List;

public class Transition {
    private HashMap<String, List<Character>> transitions;
    private String input;
    private String startState;
    private String state;
    private int inputNumber;
    private int outputNumber;
    private Direction direction;

    public Transition(String input) {
        this.input = input;
    }

    public void initialize() {
        
    }
}
