package model;

import annotations.Table;
import annotations.constraints.MaxLength;
import annotations.constraints.MinLength;
import annotations.constraints.NoNull;
import annotations.relationshipType.Element;
import annotations.validatorAnnotations.LongerThan;
import annotations.validatorAnnotations.NotEqualString;
import annotations.validatorAnnotations.NotNull;
import annotations.validatorAnnotations.ShorterThan;

import java.io.Serializable;

@Table("person")
public class Person implements Serializable {
    @Element
    @NoNull
    @NotNull
    @NotEqualString
    private String name;
    @Element
    @MinLength(6)
    @MaxLength(49)
    @NoNull
    @LongerThan(length = 6)
    @ShorterThan(length = 49)
    @NotNull
    private String passportID;
    @Element
    @NoNull
    @NotNull
    private Color eyeColor;
    @Element
    @NoNull
    @NotNull
    private Color hairColor;
    @Element
    @NoNull
    @NotNull
    private Country nationality;
    @Element
    @NoNull
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
