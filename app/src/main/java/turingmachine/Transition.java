package turingmachine;

public class Transition {
    private String nextState;
    private int write;
    private Direction move;

    public Transition(String nextState, int write, Direction move) {
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

    public Direction getMove() {
        return move;
    }

    @Override
    public String toString() {
        return "(" + nextState + ", " + write + ", " + move + ")";
    }
}

