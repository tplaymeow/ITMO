package model;

import annotations.GreaterThan;
import annotations.NotNull;

public class Coordinates {
    @NotNull
    @GreaterThan(num = -387)
    private Long x; //Значение поля должно быть больше -387, Поле не может быть null
    private double y;

    public Coordinates(Long x, double y) {
        if (x == null)
            throw new NullPointerException("X can't be null");
        else if (x <= -387)
            throw new IllegalArgumentException("X should be > -387");
        this.x = x;
        this.y = y;
    }
}