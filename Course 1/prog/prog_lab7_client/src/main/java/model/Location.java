package model;

import annotations.Table;
import annotations.constraints.NoNull;
import annotations.relationshipType.Element;
import annotations.validatorAnnotations.NotNull;

import java.io.Serializable;

@Table("location")
public class Location implements Serializable {
    @Element
    @NoNull
    @NotNull
    private Float x;
    @Element
    @NoNull
    @NotNull
    private Long y;
    @Element
    private long z;
    @Element
    @NoNull
    @NotNull
    private String name;

    public Location() {
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", name='" + name + '\'' +
                '}';
    }
}
