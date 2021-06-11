package model;

import annotations.Table;
import annotations.constraints.NoNull;
import annotations.relationshipType.Element;
import annotations.validatorAnnotations.GreaterThan;
import annotations.validatorAnnotations.NotNull;

import java.io.Serializable;

@Table("ccord")
public class Coordinates implements Serializable {
    @Element
    @NoNull
    @annotations.constraints.GreaterThan(-387)
    @NotNull
    @GreaterThan(num = -387)
    private Long x;
    @Element
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
