/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package turingmachine;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Turing machine input:");
        String input = scanner.nextLine();
        TuringMachine machine = new TuringMachine(input);

        System.out.println("Choose mode (1 for Step-mode, 2 for Run-mode):");
        int mode = scanner.nextInt();
        boolean stepMode = (mode == 1);

        machine.execute(stepMode);
    }
}
