package model;

import annotations.Table;
import annotations.constraints.Unique;
import annotations.relationshipType.Element;
import annotations.relationshipType.OneToMany;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Objects;

@Table("users")
public class User implements Serializable {
    @Element
    private String login;
    @Element
    @Unique
    private String password;
    @OneToMany
    private LinkedList<StudyGroup> studyGroups;

    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LinkedList<StudyGroup> getStudyGroups() {
        return studyGroups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getLogin(), user.getLogin()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getStudyGroups(), user.getStudyGroups());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLogin(), getPassword(), getStudyGroups());
    }
}
