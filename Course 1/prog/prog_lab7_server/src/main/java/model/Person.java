package model;

import annotations.validatorAnnotations.LongerThan;
import annotations.validatorAnnotations.NotEqualString;
import annotations.validatorAnnotations.NotNull;
import annotations.validatorAnnotations.ShorterThan;

import java.io.Serializable;

public class Person implements Serializable {
    @NotNull
    @NotEqualString
    private String name;
    @LongerThan(length = 6)
    @ShorterThan(length = 49)
    @NotNull
    private String passportID;
    @NotNull
    private Color eyeColor;
    @NotNull
    private Color hairColor;
    @NotNull
    private Country nationality;
    @NotNull
    private Location location;

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", passportID='" + passportID + '\'' +
                ", eyeColor=" + eyeColor +
                ", hairColor=" + hairColor +
                ", nationality=" + nationality +
                ", location=" + location +
                '}';
    }
}
