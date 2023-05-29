package model;

import annotations.validatorAnnotations.NotNull;

import java.io.Serializable;

public class Location implements Serializable {
    @NotNull
    private Float x;
    @NotNull
    private Long y;
    private long z;
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
