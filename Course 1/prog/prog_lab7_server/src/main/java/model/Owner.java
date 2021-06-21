package model;

import annotations.Id;
import annotations.Table;
import annotations.relationshipType.Element;
import annotations.relationshipType.OneToMany;
import utils.Hasher;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;

@Table("Users")
public class Owner {
    @Id
    private int id;
    @Element
    private String login;
    @Element
    private String password;
    @Element
    private String salt;
    @OneToMany
    private ArrayList<StudyGroup> studyGroups;

    public Owner() {
    }

    public Owner(String login, String password) {
        this.login = login;
        byte[] array = new byte[9];
        new Random().nextBytes(array);
        this.salt = new String(array, StandardCharsets.UTF_8);
        this.password = Hasher.hash(password, this.salt);
        studyGroups = new ArrayList<>();
    }

    public boolean equalsUser(User user) {
        return this.getLogin().equals(user.getLogin())
                && this.getPassword().equals(Hasher.hash(user.getPassword(), this.salt));
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<StudyGroup> getStudyGroups() {
        return studyGroups;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStudyGroups(ArrayList<StudyGroup> studyGroups) {
        this.studyGroups = studyGroups;
    }
}
