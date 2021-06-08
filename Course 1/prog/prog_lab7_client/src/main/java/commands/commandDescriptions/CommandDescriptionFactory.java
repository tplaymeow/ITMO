package commands.commandDescriptions;

import exceptions.BadArgumentException;
import model.Semester;
import model.User;

public class CommandDescriptionFactory {
    public static CommandDescription get(String commandWithArgs, User user) throws BadArgumentException {
        String[] splitCommand = commandWithArgs.split(" ");
        String commandName = splitCommand[0];

        switch (commandName) {
            case "help":
                checkCountOfArgs(splitCommand, 1);
                return new CommandDescription("help", user);
            case "info":
                checkCountOfArgs(splitCommand, 1);
                return new CommandDescription("info", user);
            case "show":
                checkCountOfArgs(splitCommand, 1);
                return new CommandDescription("show", user);
            case "clear":
                checkCountOfArgs(splitCommand, 1);
                return new CommandDescription("clear", user);
            case "sort":
                checkCountOfArgs(splitCommand, 1);
                return new CommandDescription("sort", user);
            case "remove_by_id":
                checkCountOfArgs(splitCommand, 2);
                return new CommandDescription("remove_by_id", user, Integer.parseInt(splitCommand[1]));
            case "remove_at":
                checkCountOfArgs(splitCommand, 2);
                return new CommandDescription("remove_at", user, Integer.parseInt(splitCommand[1]));
            case "count_less_than_should_be_expelled":
                checkCountOfArgs(splitCommand, 2);
                return new CommandDescription("count_less_than_should_be_expelled", user, Integer.parseInt(splitCommand[1]));
            case "count_greater_than_students_count":
                checkCountOfArgs(splitCommand, 2);
                return new CommandDescription("count_greater_than_students_count", user, Integer.parseInt(splitCommand[1]));
            case "filter_by_semester_enum":
                checkCountOfArgs(splitCommand, 2);
                return new CommandDescription("filter_by_semester_enum", user, Semester.valueOf(splitCommand[1]));


        }
    }

    private static void checkCountOfArgs(String[] splitCommand, int count) throws BadArgumentException {
        if (splitCommand.length != count) throw new BadArgumentException();
    }
}
