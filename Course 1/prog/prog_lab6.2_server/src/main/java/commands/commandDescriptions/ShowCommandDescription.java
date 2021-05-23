package commands.commandDescriptions;

import exceptions.BadArgumentException;

public class ShowCommandDescription extends CommandDescription {
    public ShowCommandDescription(String arguments) throws BadArgumentException {
        super("show");
        if (!argumentsCountIsEqual(arguments, 0)) throw new BadArgumentException();
    }
}

