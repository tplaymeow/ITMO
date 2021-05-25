package support;

import exceptions.UnknownCommandNameException;

public enum CommandName {
    HELP("help"),
    INFO("info"),
    FILTERLESSTHANNUMBEROFROOMS("filter_less_than_number_of_rooms"),
    SHOW("show");

    private final String name;

    CommandName(String name) {
        this.name = name;
    }

    public static CommandName findByName(String name) throws UnknownCommandNameException {
        for (CommandName commandName :
                values()) {
            if (commandName.name.equals(name)) return commandName;
        }
        throw new UnknownCommandNameException();
    }
}
