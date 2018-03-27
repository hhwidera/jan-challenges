package com.widera.janchallenges.blatt01;

import java.util.List;
import java.util.stream.Collectors;

class Marker {

    private final Marker prevMarker;
    private final Transition transition;
    private final long keyValue;

    Marker(final Marker prevMarker, final Transition transition, final long keyValue) {
        this.prevMarker = prevMarker;
        this.transition = transition;
        this.keyValue = keyValue;
    }

    public State getState() {
        return this.transition.nextState();
    }

    public long getKeyValue() {
        return keyValue;
    }

    public List<Marker> generateNextMarkers() {
        return this.getState().allTransitions().stream()
                .map(transition -> new Marker(this, transition, transition.calc(keyValue)))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Marker{" +
                "state=" + getState().name() +
                ", keyValue=" + keyValue +
                ", prev=" + prevMarker +
                '}';
    }

    public String steps() {
        String result = "";
        if (prevMarker != null) {
            result += prevMarker.steps();
        }
        return result + transition.steps();
    }

}
