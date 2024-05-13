package turingmachine;

public class TuringMachine {
    private Tape tape;
    private String input;


    public TuringMachine(String input) {
        this.input = input;
        tape = new Tape(input);
    }

    
}
