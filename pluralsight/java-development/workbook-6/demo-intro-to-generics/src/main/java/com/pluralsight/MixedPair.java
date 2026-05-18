package com.pluralsight;

public class MixedPair<T, K> {
    private T leftThing;
    private K rightThing;

    public MixedPair(T leftThing, K rightThing) {
        this.leftThing = leftThing;
        this.rightThing = rightThing;
    }

    public T getLeftThing() {
        return leftThing;
    }

    public K getRightThing() {
        return rightThing;
    }


}
