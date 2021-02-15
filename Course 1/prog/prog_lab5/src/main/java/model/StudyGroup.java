package model;

import java.time.LocalDateTime;
import java.util.Objects;

public class StudyGroup {
    private static long CreatedStudyGroupsCount = 0;
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int studentsCount; //Значение поля должно быть больше 0
    private int shouldBeExpelled; //Значение поля должно быть больше 0
    private FormOfEducation formOfEducation; //Поле не может быть null
    private Semester semesterEnum; //Поле не может быть null
    private Person groupAdmin; //Поле может быть null

    {
        CreatedStudyGroupsCount += 1;
        id = CreatedStudyGroupsCount;
    }

    public StudyGroup(String name,
                      Coordinates coordinates,
                      LocalDateTime creationDate,
                      int studentsCount,
                      int shouldBeExpelled,
                      FormOfEducation formOfEducation,
                      Semester semesterEnum,
                      Person groupAdmin) {

        name = Objects.requireNonNull(name, "Name can't be null");
        if (name.equals(""))
            throw new IllegalArgumentException("Name can't be \"\"");
        this.name = name;
        this.coordinates = Objects.requireNonNull(coordinates, "Coordinate can't be null");
        this.creationDate = Objects.requireNonNull(creationDate, "Creation date can't be null");
        if (studentsCount <= 0)
            throw new IllegalArgumentException("Students count should be > 0");
        this.studentsCount = studentsCount;
        if (shouldBeExpelled <= 0)
            throw new IllegalArgumentException("Should be expelled count should be > 0");
        this.shouldBeExpelled = shouldBeExpelled;
        this.formOfEducation = Objects.requireNonNull(formOfEducation, "Form of education can't be null");
        this.semesterEnum = Objects.requireNonNull(semesterEnum, "model.Semester enum can't be null");
        ;
        this.groupAdmin = Objects.requireNonNull(groupAdmin, "Group admin can't be null");
        ;
    }
}