package model;

import annotations.validatorAnnotations.NotNull;

import java.util.Objects;

public class Location {
    @NotNull
    private Float x; //Поле не может быть null
    @NotNull
    private Long y; //Поле не может быть null
    private long z;
    @NotNull
    private String name; //Поле может быть null

    public Location(Float x, Long y, long z, String name) {
        this.x = Objects.requireNonNull(x, "X can't be null");
        this.y = Objects.requireNonNull(y, "Y can't be null");
        this.z = z;
        this.name = Objects.requireNonNull(name, "Name can't be null");
    }

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