package commands.commandDescriptions;

import exceptions.BadArgumentException;

public class CountLessThanCommandDescription extends CommandDescription {
    private final int id;

    public int getId() {
        return id;
    }

    public CountLessThanCommandDescription(String arguments) throws BadArgumentException {
        super("count_less_than_should_be_expelled");
        if (!argumentsCountIsEqual(arguments, 1)) throw new BadArgumentException();
        try {
            this.id = Integer.parseInt(arguments);
        } catch (NumberFormatException e) {
            throw new BadArgumentException("Не верный формат числа.");
        }


    }
}

