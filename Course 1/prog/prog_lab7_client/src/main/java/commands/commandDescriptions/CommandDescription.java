package commands.commandDescriptions;

import model.User;

import java.io.Serializable;

public class CommandDescription implements Serializable {
    private final String name;
    private  User user;
    private Integer value;
    private Object object;

    public CommandDescription(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public CommandDescription(String name, User user, Integer value) {
        this.name = name;
        this.user = user;
        this.value = value;
    }

    public CommandDescription(String name, User user, Integer value, Object object) {
        this.name = name;
        this.user = user;
        this.value = value;
        this.object = object;
    }

    public CommandDescription(String name, User user, Object object) {
        this.name = name;
        this.user = user;
        this.object = object;
    }

    public String getName() {
        return name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}

