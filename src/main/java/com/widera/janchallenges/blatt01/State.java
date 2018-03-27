package com.widera.janchallenges.blatt01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class State {

    private final String name;

    private final List<Transition> transitions = new ArrayList<>();

    State(String name) {
        this.name = name;
    }

    public String name() {
        return this.name;
    }

    public void addTransition(final Transition transition) {
        this.transitions.add(transition);
    }

    public List<Transition> allTransitions() {
        return Collections.unmodifiableList(this.transitions);
    }

    @Override
    public String toString() {
        return "State{" +
                "name='" + name + '\'' +
                ", transitions=" + transitions +
                '}';
    }

}
