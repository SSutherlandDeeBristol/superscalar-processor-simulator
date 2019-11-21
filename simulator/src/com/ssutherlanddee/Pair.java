package com.ssutherlanddee;

public class Pair<S1, S2> {

    private S1 a;
    private S2 b;

    public Pair(S1 a, S2 b) {
        this.a = a;
        this.b = b;
    }

    public S1 first() {
        return this.a;
    }

    public S2 second() {
        return this.b;
    }
}