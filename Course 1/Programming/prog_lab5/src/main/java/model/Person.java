package model;

import annotations.validatorAnnotations.LongerThan;
import annotations.validatorAnnotations.NotEqualString;
import annotations.validatorAnnotations.NotNull;
import annotations.validatorAnnotations.ShorterThan;

import java.util.Objects;

public class Person {
    @NotNull
    @NotEqualString
    private String name; //Поле не может быть null, Строка не может быть пустой
    @LongerThan(length = 6)
    @ShorterThan(length = 49)
    @NotNull
    private String passportID; //Длина строки должна быть не меньше 6, Длина строки не должна быть больше 49, Поле не может быть null
    @NotNull
    private Color eyeColor; //Поле не может быть null
    @NotNull
    private Color hairColor; //Поле не может быть null
    @NotNull
    private Country nationality; //Поле не может быть null
    @NotNull
    private Location location; //Поле может быть null

    public Person(String name,
                  String passportID,
                  Color eyeColor,
                  Color hairColor,
                  Country nationality,
                  Location location) {

        name = Objects.requireNonNull(name, "Name can't be null");
        if (name.equals(""))
            throw new IllegalArgumentException("Name can't be \"\"");
        this.name = name;

        passportID = Objects.requireNonNull(passportID, "passportID can't be null");
        if (passportID.length() <= 6 && passportID.length() >= 49)
            throw new IllegalArgumentException("Incorrect passportID length");
        this.passportID = passportID;

        this.eyeColor = Objects.requireNonNull(eyeColor, "Eye color can't be null");
        this.hairColor = Objects.requireNonNull(hairColor, "Hair color can't be null");
        this.nationality = Objects.requireNonNull(nationality, "Nationality can't be null");
        this.location = Objects.requireNonNull(location, "model.Location can't be null");
    }

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