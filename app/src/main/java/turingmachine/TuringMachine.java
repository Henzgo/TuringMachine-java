package turingmachine;

public class TuringMachine {
    private Tape tape;
    private TransitionTable transitionTable;
    private String input;


    public TuringMachine(String input) {
        this.input = input;
        tape = new Tape(input);
        this.transitionTable = new TransitionTable();
    }

    
}
