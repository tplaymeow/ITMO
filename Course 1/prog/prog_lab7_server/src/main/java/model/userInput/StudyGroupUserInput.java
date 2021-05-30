package model.userInput;

import annotations.validatorAnnotations.GreaterThan;
import annotations.validatorAnnotations.NotEqualString;
import annotations.validatorAnnotations.NotNull;
import model.Coordinates;
import model.FormOfEducation;
import model.Person;
import model.Semester;

import java.io.Serializable;

public class StudyGroupUserInput implements Serializable {
    @NotNull
    @NotEqualString
    private String name;
    @NotNull
    private Coordinates coordinates;
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

    public StudyGroupUserInput() {
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
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
