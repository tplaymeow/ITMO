package commands.commandDescriptions;

import exceptions.BadArgumentException;

public class RemoveByIdCommandDescription extends CommandDescription {
    private final int id;
    public RemoveByIdCommandDescription(String argument) throws BadArgumentException {
        super("remove_by_id");
        if (!argumentsCountIsEqual(argument, 1)) throw new BadArgumentException();
        try {
            this.id = Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            throw new BadArgumentException("Не верный формат числа.");
        }
    }

    public int getId() {
        return id;
    }
}
