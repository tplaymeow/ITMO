package model;

import java.util.Objects;

public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String passportID; //Длина строки должна быть не меньше 6, Длина строки не должна быть больше 49, Поле не может быть null
    private Color eyeColor; //Поле не может быть null
    private Color hairColor; //Поле не может быть null
    private Country nationality; //Поле не может быть null
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

        passportID = Objects.requireNonNull(passportID, "passportID can't be null");
        if (passportID.length() >= 6 && passportID.length() <= 49)
            throw new IllegalArgumentException("Incorrect passportID length");

        this.eyeColor = Objects.requireNonNull(eyeColor, "Eye color can't be null");
        this.hairColor = Objects.requireNonNull(hairColor, "Hair color can't be null");
        this.nationality = Objects.requireNonNull(nationality, "Nationality can't be null");
        this.location = Objects.requireNonNull(location, "model.Location can't be null");
        ;
    }
}