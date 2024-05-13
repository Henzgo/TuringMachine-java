package turingmachine;

import java.util.HashMap;
import java.util.List;

public class Transition {
    private String nextState;
    private int write;
    private char move; // 'L' for left, 'R' for right

    public Transition(String nextState, int write, char move) {
        this.nextState = nextState;
        this.write = write;
        this.move = move;
    }

    public String getNextState() {
        return nextState;
    }

    public int getWrite() {
        return write;
    }

    public char getMove() {
        return move;
    }

    @Override
    public String toString() {
        return "(" + nextState + ", " + write + ", " + move + ")";
    }
}

