package model;

import annotations.Id;
import annotations.Table;
import annotations.constraints.GreaterThan;
import annotations.constraints.NoNull;
import annotations.converterAnnotations.NotWrite;
import annotations.relationshipType.Element;
import annotations.validatorAnnotations.NotNull;

import java.io.Serializable;

@Table("Coords")
public class Coordinates implements Serializable {
    @Element
    @GreaterThan(-387)
    @NoNull
    @annotations.validatorAnnotations.GreaterThan(num = -387)
    @NotNull
    private Long x;
    @Element
    private double y;
    @Id
    @NotWrite
    private int id;

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
