package commands.commandDescriptions;

import exceptions.BadArgumentException;

public class ClearCommandDescription extends CommandDescription {
    public ClearCommandDescription(String arguments) throws BadArgumentException {
        super("clear");
        if (!argumentsCountIsEqual(arguments, 0)) throw new BadArgumentException();
    }
}

