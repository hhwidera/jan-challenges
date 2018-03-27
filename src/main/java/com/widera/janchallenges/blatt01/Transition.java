package com.widera.janchallenges.blatt01;

class Transition {

    private final String steps;
    private final Function function;
    private final State nextState;
    Transition(final String steps, final Function function, final State nextState) {
        this.steps = steps;
        this.function = function;
        this.nextState = nextState;
    }

    public long calc(long keyValue) {
        return this.function.calc(keyValue);
    }

    public State nextState() {
        return this.nextState;
    }

    public String steps() {
        return this.steps;
    }

    @Override
    public String toString() {
        return "Transition{" +
                "steps='" + steps + '\'' +
                ", nextState=" + nextState.name() +
                '}';
    }

    public interface Function {
        long calc(long value);
    }

}
