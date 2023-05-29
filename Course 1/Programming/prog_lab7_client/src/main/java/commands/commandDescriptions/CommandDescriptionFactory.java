package commands.commandDescriptions;

import exceptions.AnnotationException;
import exceptions.BadArgumentException;
import exceptions.ConvertInstructionException;
import exceptions.EndOfFileException;
import model.*;
import model.userInput.StudyGroupUserInput;
import utils.Converter;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.function.Consumer;

public class CommandDescriptionFactory {
    public static CommandDescription get(String commandWithArgs, User user, InputStream inputStream, Consumer<String> printer) throws BadArgumentException {
        String[] splitCommand = commandWithArgs.split(" ");
        String commandName = splitCommand[0];

        try {
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
                case "exit":
                    checkCountOfArgs(splitCommand, 1);
                    return new CommandDescription("exit", user);
                case "login":
                    checkCountOfArgs(splitCommand, 1);
                    return new CommandDescription("login", user);
                case "register":
                    checkCountOfArgs(splitCommand, 1);
                    return new CommandDescription("register", user);
                case "remove_by_id":
                    checkCountOfArgs(splitCommand, 2);
                    return new CommandDescription("remove_by_id", user, Integer.parseInt(splitCommand[1]));
                case "execute_script":
                    checkCountOfArgs(splitCommand, 2);
                    return new CommandDescription("execute_script", user, splitCommand[1]);
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
                case "add":
                    checkCountOfArgs(splitCommand, 1);
                    return new CommandDescription("add", user, getStGr(inputStream, printer));
                case "update":
                    checkCountOfArgs(splitCommand, 2);
                    return new CommandDescription("update", user, Integer.parseInt(splitCommand[1]), getStGr(inputStream, printer));
                case "add_if_min":
                    checkCountOfArgs(splitCommand, 1);
                    return new CommandDescription("add_if_min", user, getStGr(inputStream, printer));
                default:
                    throw new BadArgumentException("Команды " + commandName + " не существует");
            }
        } catch (AnnotationException | EndOfFileException | InstantiationException | IllegalAccessException | IOException | NumberFormatException e) {
            throw new BadArgumentException(e.getMessage());
        }
    }

    private static void checkCountOfArgs(String[] splitCommand, int count) throws BadArgumentException {
        if (splitCommand.length != count) throw new BadArgumentException();
    }

    private static StudyGroupUserInput getStGr(InputStream inputStream, Consumer<String> printer) throws AnnotationException, IOException, InstantiationException, EndOfFileException, IllegalAccessException {
        Converter<StudyGroupUserInput> converter = new Converter(StudyGroupUserInput.class);
        Arrays.asList(Color.class, Country.class, FormOfEducation.class, Semester.class).forEach((enumClass) -> {
            converter.addInstructionForEnum(enumClass);
            converter.addPossibleValuesForEnum(enumClass);
        });
        converter.addInstruction(LocalDateTime.class, (string) -> {
            try {
                return LocalDateTime.parse(string);
            } catch (DateTimeParseException var2) {
                throw new ConvertInstructionException(string);
            }
        });
        return converter.inputStreamToObject(inputStream, printer);
    }
}
