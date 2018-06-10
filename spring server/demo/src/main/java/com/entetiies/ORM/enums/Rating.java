package com.entetiies.ORM.enums;

public enum Rating {
    ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5);
    private int value;

    Rating(int i) {
        this.value = i;
    }

    public int getValue() {
        return value;
    }
}
