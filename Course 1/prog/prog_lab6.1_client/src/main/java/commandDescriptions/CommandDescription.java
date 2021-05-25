package commandDescriptions;

import support.CommandName;

import java.io.Serializable;

public abstract class CommandDescription implements Serializable {
    private final CommandName name;

    public CommandDescription(CommandName name) {
        this.name = name;
    }
}
