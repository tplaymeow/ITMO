package model;

import annotations.converterAnnotations.AutomaticGenerated;
import annotations.converterAnnotations.NotWrite;
import annotations.validatorAnnotations.GreaterThan;
import annotations.validatorAnnotations.NotEqualString;
import annotations.validatorAnnotations.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;

public class StudyGroup implements Comparable<StudyGroup> {
    @NotWrite
    @AutomaticGenerated
    private static long CreatedStudyGroupsCount = 0;
    @GreaterThan
    @AutomaticGenerated
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @NotNull
    @NotEqualString(string = "")
    private String name; //Поле не может быть null, Строка не может быть пустой
    @NotNull
    private Coordinates coordinates; //Поле не может быть null
    @NotNull
    @AutomaticGenerated
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
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
        this.id = CreatedStudyGroupsCount;
        this.creationDate = LocalDateTime.now();
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

    @Override
    public String toString() {
        return "StudyGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", studentsCount=" + studentsCount +
                ", shouldBeExpelled=" + shouldBeExpelled +
                ", formOfEducation=" + formOfEducation +
                ", semesterEnum=" + semesterEnum +
                ", groupAdmin=" + groupAdmin +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudyGroup group = (StudyGroup) o;
        return id == group.id && studentsCount == group.studentsCount && shouldBeExpelled == group.shouldBeExpelled && Objects.equals(name, group.name) && Objects.equals(coordinates, group.coordinates) && Objects.equals(creationDate, group.creationDate) && formOfEducation == group.formOfEducation && semesterEnum == group.semesterEnum && Objects.equals(groupAdmin, group.groupAdmin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, studentsCount, shouldBeExpelled, formOfEducation, semesterEnum, groupAdmin);
    }

    @Override
    public int compareTo(StudyGroup o) {
        return Integer.compare(this.studentsCount, o.studentsCount);
    }
}