package model;

import annotations.Id;
import annotations.Table;
import annotations.constraints.GreaterThan;
import annotations.constraints.MinLength;
import annotations.constraints.NoNull;
import annotations.relationshipType.Element;
import annotations.validatorAnnotations.NotEqualString;
import annotations.validatorAnnotations.NotNull;
import model.userInput.StudyGroupUserInput;

@Table("Groups")
public class StudyGroup implements Comparable<StudyGroup> {
    @Id
    private int id;
    @Element
    @NoNull
    @MinLength(1)
    @NotEqualString
    private String name;
    @Element
    @NoNull
    @NotNull
    private Coordinates coordinates;
    @Element
    @GreaterThan(0)
    @annotations.validatorAnnotations.GreaterThan
    private int studentsCount;
    @Element
    @GreaterThan(0)
    @annotations.validatorAnnotations.GreaterThan
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
//    @Element
//    private User owner;

    public StudyGroup() {
    }

    public StudyGroup(StudyGroupUserInput userInput) {
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

//    public User getOwner() {
//        return owner;
//    }

//    public void setOwner(User owner) {
//        this.owner = owner;
//    }

    @Override
    public int compareTo(StudyGroup o) {
        return this.studentsCount - o.studentsCount;
    }

    public void setId(int id) {
        this.id = id;
    }
}

