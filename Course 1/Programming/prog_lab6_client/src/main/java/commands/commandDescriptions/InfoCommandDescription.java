package commands.commandDescriptions;

import exceptions.BadArgumentException;

public class InfoCommandDescription extends CommandDescription {
    public InfoCommandDescription(String arguments) throws BadArgumentException {
        super("info");
        if (!argumentsCountIsEqual(arguments, 0)) throw new BadArgumentException();
    }
}

