package model;

import annotations.converterAnnotations.AutomaticGenerated;
import annotations.validatorAnnotations.GreaterThan;
import annotations.validatorAnnotations.NotEqualString;
import annotations.validatorAnnotations.NotNull;
import model.userInput.StudyGroupUserInput;

import java.time.LocalDateTime;

public class StudyGroup implements Comparable<StudyGroup> {
    @AutomaticGenerated
    @GreaterThan
    private long id;
    @NotNull
    @NotEqualString
    private String name;
    @NotNull
    private Coordinates coordinates;
    @NotNull
    @AutomaticGenerated
    private java.time.LocalDateTime creationDate;
    @GreaterThan
    private int studentsCount;
    @GreaterThan
    private int shouldBeExpelled;
    @NotNull
    private FormOfEducation formOfEducation;
    @NotNull
    private Semester semesterEnum;
    @NotNull
    private Person groupAdmin;

    private static int instancesCreated = 0;

    public StudyGroup() {
        this.id = instancesCreated++;
        this.creationDate = LocalDateTime.now();
    }

    public StudyGroup(StudyGroupUserInput userInput) {
        this.id = instancesCreated++;
        this.creationDate = LocalDateTime.now();

        this.name = userInput.getName();
        this.coordinates = userInput.getCoordinates();
        this.studentsCount = userInput.getStudentsCount();
        this.shouldBeExpelled = userInput.getShouldBeExpelled();
        this.formOfEducation = userInput.getFormOfEducation();
        this.semesterEnum = userInput.getSemesterEnum();
        this.groupAdmin = userInput.getGroupAdmin();
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

    public long getId() {
        return id;
    }

    public int getStudentsCount() {
        return studentsCount;
    }

    public Semester getSemesterEnum() {
        return semesterEnum;
    }

    public int getShouldBeExpelled() {
        return shouldBeExpelled;
    }

    @Override
    public int compareTo(StudyGroup o) {
        return this.studentsCount - o.studentsCount;
    }
}

