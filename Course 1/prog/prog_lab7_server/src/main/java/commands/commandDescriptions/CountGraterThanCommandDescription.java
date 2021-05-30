package commands.commandDescriptions;

import exceptions.BadArgumentException;

public class CountGraterThanCommandDescription extends CommandDescription {
    private final int id;

    public int getId() {
        return id;
    }

    public CountGraterThanCommandDescription(String arguments) throws BadArgumentException {
        super("count_greater_than_students_count");
        if (!argumentsCountIsEqual(arguments, 1)) throw new BadArgumentException();
        try {
            this.id = Integer.parseInt(arguments);
        } catch (NumberFormatException e) {
            throw new BadArgumentException("Не верный формат числа.");
        }


    }
}

