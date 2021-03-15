package model;

import annotations.GreaterThan;
import annotations.NotNull;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Objects;

public class StudyGroup {
    private static long CreatedStudyGroupsCount = 0;
    @GreaterThan
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @NotNull
    private String name; //Поле не может быть null, Строка не может быть пустой
    @NotNull
    private Coordinates coordinates; //Поле не может быть null
    @NotNull
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @GreaterThan
    private int studentsCount; //Значение поля должно быть больше 0
    @GreaterThan
    private int shouldBeExpelled; //Значение поля должно быть больше 0
    @NotNull
    private FormOfEducation formOfEducation; //Поле не может быть null
    @NotNull
    private Semester semesterEnum; //Поле не может быть null
    @NotNull
    private Person groupAdmin; //Поле может быть null

    {
        CreatedStudyGroupsCount += 1;
    }

    //TODO: добавить конструктор без даты
    public StudyGroup(String name,
                      Coordinates coordinates,
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
        this.creationDate = LocalDateTime.now();
        if (studentsCount <= 0)
            throw new IllegalArgumentException("Students count should be > 0");
        this.studentsCount = studentsCount;
        if (shouldBeExpelled <= 0)
            throw new IllegalArgumentException("Should be expelled count should be > 0");
        this.shouldBeExpelled = shouldBeExpelled;
        this.formOfEducation = Objects.requireNonNull(formOfEducation, "Form of education can't be null");
        this.semesterEnum = Objects.requireNonNull(semesterEnum, "model.Semester enum can't be null");
        this.groupAdmin = Objects.requireNonNull(groupAdmin, "Group admin can't be null");
        this.id = CreatedStudyGroupsCount;
    }

    public StudyGroup() {

    }

    public static long getCreatedStudyGroupsCount() {
        return CreatedStudyGroupsCount;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public int getStudentsCount() {
        return studentsCount;
    }

    public int getShouldBeExpelled() {
        return shouldBeExpelled;
    }

    public FormOfEducation getFormOfEducation() {
        return formOfEducation;
    }

    public Semester getSemesterEnum() {
        return semesterEnum;
    }

    public Person getGroupAdmin() {
        return groupAdmin;
    }
}