package src;

import java.util.HashSet;
import java.util.Iterator;

public class AFN<S> {

    private final HashSet<Letter> alphabet;
    private States<S> setOfStates;
    private final States<S> setOfInitialStates;
    private final States<S> setOfFinalStates;
    private final Transitions<S> transitionRelation;

    public AFN(HashSet<Letter> alphabet,
               States<S> setOfStates,
               States<S> setOfInitialStates,
               States<S> setOfFinalStates,
               Transitions<S> transitionRelation) {
        this.alphabet = alphabet;
        this.setOfStates = setOfStates;
        this.setOfInitialStates = setOfInitialStates;
        this.setOfFinalStates = setOfFinalStates;
        this.transitionRelation = transitionRelation;
    }

    public HashSet<Letter> getAlphabet() {
        return alphabet;
    }

    public States<S> getSetOfStates() {
        return setOfStates;
    }

    public States<S> getSetOfInitialStates() {
        return setOfInitialStates;
    }

    public States<S> getSetOfFinalStates() {
        return setOfFinalStates;
    }

    public Transitions<S> getTransitionRelation() {
        return transitionRelation;
    }

    private boolean contain(S state, States<S> states){
        return states.getSetOfStates().contains(state);
    }

    private boolean contain(Iterator<S> iterator, States<S> states) {
        if(!(iterator.hasNext())){
            return true;
        }
        return contain(iterator.next(),states) && contain(iterator,states);
    }

    public boolean recognize(Word w){
        Iterator<Letter> letter = w.iterator();
        if(!(letter.hasNext())){
            return false;
        }
        States<S>  statesRead = this.transitionRelation.successors(this.setOfInitialStates, letter.next());
        boolean res = true;
        for (Letter value : this.alphabet){
            statesRead = (this.transitionRelation.successors(statesRead, value));
            res = contain(statesRead.iterator(),this.setOfFinalStates);

        }
        return res;
    }

    private boolean emptyLanguage(States<S> listStatesRead ,States<S> statesRead) {
        if (contain(listStatesRead.iterator(), this.getSetOfFinalStates())) {
            return true;
        }
        if (contain(statesRead.iterator(), listStatesRead)) {
            return false;
        }

        States<S> newStatesRead = new States<>();
        listStatesRead.addAllStates(statesRead);
        for (Letter value : this.alphabet){
            newStatesRead.addAllStates(this.transitionRelation.successors(statesRead, value));
        }
        return emptyLanguage(listStatesRead, newStatesRead);
    }

    public boolean emptyLanguage() {
        if (this.setOfInitialStates.getSetOfStates().isEmpty() ||
                this.setOfFinalStates.getSetOfStates().isEmpty()){
            return true;
        }

        States<S> listStatesRead = new States<>();
        States<S> statesRead = this.transitionRelation.successors(this.setOfInitialStates,
                                                                  this.alphabet.iterator().next());
        listStatesRead.addAllStates(this.setOfInitialStates);
        return emptyLanguage(listStatesRead,statesRead);

    }

    public boolean isDeterministic() {
        if (this.setOfInitialStates.getSetOfStates().size() > 1){
            return false;
        }
        for (S states : this.setOfStates.getSetOfStates()){
            for (Letter value : this.alphabet) {
                States<S> reader = this.transitionRelation.successor(states, value);
                if (reader.getSetOfStates().size() > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isComplete() {
        for (S state : this.setOfStates.getSetOfStates()) {
            for (Letter value : this.alphabet) {
                States<S> reader = this.transitionRelation.successor(state, value);
                if (reader.getSetOfStates().size() == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void complete() {
        if (!isComplete()) {
            S trash = (S) new State("trash");
            this.setOfStates.addState(trash);
            for (S state : this.setOfStates.getSetOfStates()) {
                for (Letter value : this.alphabet) {
                    States<S> reader = this.transitionRelation.successor(state, value);
                    if (reader.getSetOfStates().size() == 0) {
                        this.transitionRelation.addTransition(new Transition<>(state, value, trash));
                    }
                }
            }
        }
    }
}
