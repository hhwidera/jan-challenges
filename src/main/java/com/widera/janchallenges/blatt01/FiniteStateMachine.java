package com.widera.janchallenges.blatt01;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

class FiniteStateMachine {

    private final State START;
    private final State END;

    /**
     * State table:
     *   G   H
     * E   F
     *   C   D
     * A   B
     * START
     */

    FiniteStateMachine() {
        // generate states
        State start = new State("START");
        State a = new State("A");
        State b = new State("B");
        State c = new State("C");
        State d = new State("D");
        State e = new State("E");
        State f = new State("F");
        State g = new State("G");
        State h = new State("H");

        this.START = start;
        this.END = h;


        // start transition from state START to state A
        addTransition(start, "", value -> 22L, a);

        addTransition(a, "OO", value -> value - 9L, b);
        addTransition(a, "ON", value -> value - 4L, c);
        addTransition(a, "NO", value -> value + 4L, c);
        addTransition(a, "NN", value -> value + 4L, e);

        addTransition(b, "OW", value -> value * 9L, b);
        addTransition(b, "NS", value -> value - 9L, b);
        addTransition(b, "WO", value -> value - 9L, b);
        addTransition(b, "ON", value -> value * 18L, d);
        addTransition(b, "NO", value -> value - 18L, d);
        addTransition(b, "NW", value -> value - 4L, c);
        addTransition(b, "WN", value -> value - 4L, c);

        addTransition(c, "OW", value -> value - 4L, c);
        addTransition(c, "SN", value -> value - 4L, c);
        addTransition(c, "NS", value -> value * 4L, c);
        addTransition(c, "WO", value -> value + 4L, c);
        addTransition(c, "OO", value -> value - 18L, d);
        addTransition(c, "ON", value -> value - 11L, f);
        addTransition(c, "NO", value -> value * 11L, f);
        addTransition(c, "NW", value -> value * 4L, e);
        addTransition(c, "WN", value -> value + 4L, e);
        addTransition(c, "SO", value -> value - 9L, b);
        addTransition(c, "OS", value -> value - 9L, b);

        addTransition(d, "NS", value -> value * 18L, d);
        addTransition(d, "WO", value -> value - 18L, d);
        addTransition(d, "SN", value -> value * 18L, d);
        addTransition(d, "NN", value -> value * 1L, h);
        addTransition(d, "NW", value -> value * 11L, f);
        addTransition(d, "WN", value -> value - 11L, f);
        addTransition(d, "WW", value -> value - 4L, c);
        addTransition(d, "WS", value -> value - 9L, b);
        addTransition(d, "SW", value -> value * 9L, b);

        addTransition(e, "NS", value -> value * 4L, e);
        addTransition(e, "OW", value -> value * 4L, e);
        addTransition(e, "SN", value -> value + 4L, e);
        addTransition(e, "ON", value -> value * 8L, g);
        addTransition(e, "NO", value -> value * 8L, g);
        addTransition(e, "SO", value -> value + 4L, c);
        addTransition(e, "OS", value -> value * 4L, c);

        addTransition(f, "OW", value -> value * 11L, f);
        addTransition(f, "NS", value -> value - 11L, f);
        addTransition(f, "WO", value -> value * 11L, f);
        addTransition(f, "SN", value -> value - 11L, f);
        addTransition(f, "ON", value -> value * 1L, h);
        addTransition(f, "NO", value -> value - 1L, h);
        addTransition(f, "NW", value -> value - 8L, g);
        addTransition(f, "WN", value -> value * 8L, g);
        addTransition(f, "WS", value -> value * 4L, c);
        addTransition(f, "SW", value -> value - 4L, c);
        addTransition(f, "SO", value -> value - 18L, d);
        addTransition(f, "OS", value -> value * 18L, d);

        addTransition(g, "OW", value -> value - 8L, g);
        addTransition(g, "WO", value -> value * 8L, g);
        addTransition(g, "SN", value -> value * 8L, g);
        addTransition(g, "OO", value -> value - 1L, h);
        addTransition(g, "WS", value -> value * 4L, e);
        addTransition(g, "SW", value -> value * 4L, e);
        addTransition(g, "SO", value -> value * 11L, f);
        addTransition(g, "OS", value -> value - 11L, f);
    }

    public List<Marker> simulate(final long endValue) {
        final int MAX_ROUNDS = 20;

        List<Marker> markers = new ArrayList<>(10_000_000);
        List<Marker> markersOfThisRound = new ArrayList<>(10_000_000);

        // init
        Transition startTransition = START.allTransitions().get(0);
        markers.add(new Marker(null, startTransition, startTransition.calc(0L)));

        int round = 0;
        Date startTime = new Date();
        while (round < MAX_ROUNDS && !isOneOrMoreMarkersFinished(markers, endValue)) {
            round++;
            markersOfThisRound.clear();
            markersOfThisRound.addAll(markers);

            markers.clear();
            markersOfThisRound.forEach(marker -> markers.addAll(marker.generateNextMarkers()));
        }

        System.out.println("simulation time: " + (new Date().getTime() - startTime.getTime()) + " ms");
        System.out.println("needed rounds: " + round);
        System.out.println("active end markers in system: " + markers.size());

        return finishedMarkers(markers, endValue);
    }

    private boolean isOneOrMoreMarkersFinished(final List<Marker> markersToCheck, final long endValue) {
        return finishedMarkers(markersToCheck, endValue).size() > 0;
    }

    private List<Marker> finishedMarkers(final List<Marker> markersToCheck, final long endValue) {
        return markersToCheck.stream()
                .filter(marker -> marker.getState().equals(END) && marker.getKeyValue() == endValue)
                .collect(Collectors.toList());
    }

    private void addTransition(final State from, final String steps, final Transition.Function function, final State to) {
        Transition transition = new Transition(steps, function, to);
        from.addTransition(transition);
    }

}
