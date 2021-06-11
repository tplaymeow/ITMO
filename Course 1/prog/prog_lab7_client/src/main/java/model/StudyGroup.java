package model;

import annotations.Id;
import annotations.Table;
import annotations.constraints.MinLength;
import annotations.constraints.NoNull;
import annotations.converterAnnotations.AutomaticGenerated;
import annotations.relationshipType.Element;
import annotations.validatorAnnotations.GreaterThan;
import annotations.validatorAnnotations.NotEqualString;
import annotations.validatorAnnotations.NotNull;
import model.userInput.StudyGroupUserInput;

@Table("groups")
public class StudyGroup implements Comparable<StudyGroup> {
    @Id
    @AutomaticGenerated
    @GreaterThan
    private final int id;
    @Element
    @NoNull
    @MinLength(1)
    @NotNull
    @NotEqualString
    private String name;
    @Element
    @NoNull
    @NotNull
    private Coordinates coordinates;
    @Element
    @annotations.constraints.GreaterThan(0)
    @GreaterThan
    private int studentsCount;
    @Element
    @annotations.constraints.GreaterThan(0)
    @GreaterThan
    private int shouldBeExpelled;
    @Element
    @NoNull
    @NotNull
    private FormOfEducation formOfEducation;
    @Element
    @NoNull
    @NotNull
    private Semester semesterEnum;
    @Element
    @NoNull
    @NotNull
    private Person groupAdmin;
    private User owner;

    private static int instancesCreated = 0;

    public StudyGroup() {
        this.id = instancesCreated++;
    }

    public StudyGroup(StudyGroupUserInput userInput) {
        this.id = instancesCreated++;

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
                ", studentsCount=" + studentsCount +
                ", shouldBeExpelled=" + shouldBeExpelled +
                ", formOfEducation=" + formOfEducation +
                ", semesterEnum=" + semesterEnum +
                ", groupAdmin=" + groupAdmin +
                '}';
    }

    public int getId() {
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public int compareTo(StudyGroup o) {
        return this.studentsCount - o.studentsCount;
    }
}

