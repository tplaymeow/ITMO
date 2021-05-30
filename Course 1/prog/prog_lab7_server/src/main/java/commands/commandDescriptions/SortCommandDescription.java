package commands.commandDescriptions;

import exceptions.BadArgumentException;

public class SortCommandDescription extends CommandDescription {
    public SortCommandDescription(String arguments) throws BadArgumentException {
        super("sort");
        if (!argumentsCountIsEqual(arguments, 0)) throw new BadArgumentException();
    }
}

