package commands.commandDescriptions;

import java.io.Serializable;

public abstract class CommandDescription implements Serializable {
    private final String name;

    public CommandDescription(String name) {
        this.name = name;
    }

    protected final boolean argumentsCountIsEqual(String arguments, int count) {
        if (arguments.equals("")) return count == 0;
        else return arguments.split(" ").length == count;
    }

    public String getName() {
        return name;
    }
}