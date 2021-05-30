package model;

import annotations.validatorAnnotations.GreaterThan;
import annotations.validatorAnnotations.NotNull;

import java.io.Serializable;

public class Coordinates implements Serializable {
    @NotNull
    @GreaterThan(num = -387)
    private Long x;
    private double y;

    public Coordinates() {
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
