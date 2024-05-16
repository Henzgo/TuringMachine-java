package turingmachine;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

public class TransitionTable {
    private Map<String, Map<Integer, Transition>> transitions;

    public TransitionTable() {
        this.transitions = new HashMap<>();
    }

    public void addTransition(String currentState, int inputSymbol, Transition transition) {
        this.transitions.computeIfAbsent(currentState, k -> new HashMap<>()).put(inputSymbol, transition);
    }

    public Transition getTransition(String currentState, int inputSymbol) {
        return this.transitions.getOrDefault(currentState, new HashMap<>()).get(inputSymbol);
    }

    @Override
    public String toString() {
        return transitions.toString();
    }

    public Set<Entry<String, Map<Integer, Transition>>> getAllTransitions() {
        return transitions.entrySet();
    }

    public String getStates() {
        return transitions.keySet().toString();
    }

    public List<Integer> getInnerKeys() {
        List<Integer> innerKeys = new ArrayList<>();
        for (Map.Entry<String, Map<Integer, Transition>> entry : transitions.entrySet()) {
            innerKeys.addAll(entry.getValue().keySet());
        }
        return innerKeys;
    }
}
