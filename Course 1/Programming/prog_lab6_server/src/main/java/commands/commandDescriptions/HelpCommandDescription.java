package commands.commandDescriptions;

import exceptions.BadArgumentException;

public class HelpCommandDescription extends CommandDescription {
    public HelpCommandDescription(String arguments) throws BadArgumentException {
        super("help");
        if (!argumentsCountIsEqual(arguments, 0)) throw new BadArgumentException();
    }
}

