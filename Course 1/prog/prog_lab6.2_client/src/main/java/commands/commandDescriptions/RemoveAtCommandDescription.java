package commands.commandDescriptions;

import exceptions.BadArgumentException;

public class RemoveAtCommandDescription extends CommandDescription {
    private final int index;
    public RemoveAtCommandDescription(String argument) throws BadArgumentException {
        super("remove_at");
        if (!argumentsCountIsEqual(argument, 1)) throw new BadArgumentException();
        try {
            this.index = Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            throw new BadArgumentException("Не верный формат числа.");
        }
    }

    public int getIndex() {
        return index;
    }

}
