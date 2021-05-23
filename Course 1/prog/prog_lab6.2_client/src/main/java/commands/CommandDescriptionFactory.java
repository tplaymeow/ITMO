package commands;

import commands.commandDescriptions.*;
import exceptions.*;
import model.Color;
import model.Country;
import model.FormOfEducation;
import model.Semester;
import model.userInput.StudyGroupUserInput;
import utils.Converter;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.function.Consumer;

public class CommandDescriptionFactory {
    public static CommandDescription getCommandDescription(String line, InputStream inputStream, Consumer<String> printer) throws BadArgumentException, NoCommandException, AnnotationException, IOException, InstantiationException, EndOfFileException, IllegalAccessException {
        line = line.replaceAll(" +", " ").trim();
        String commandName = line.split(" ", 2)[0];
        String argument;
        try { argument = line.split(" ", 2)[1]; }
        catch (IndexOutOfBoundsException e) { argument = ""; }

        if (commandName.equals("help")) return new HelpCommandDescription(argument);
        else if (commandName.equals("info")) return new InfoCommandDescription(argument);
        else if (commandName.equals("sort")) return new SortCommandDescription(argument);
        else if (commandName.equals("show")) return new ShowCommandDescription(argument);
        else if (commandName.equals("add")) {
            AddCommandDescription description = new AddCommandDescription(argument);
            description.setStudyGroupUserInput(getStGr(inputStream, printer));
            return description;
        } else if (commandName.equals("add_if_min")) {
            AddIfMinCommandDescription description = new AddIfMinCommandDescription(argument);
            description.setStudyGroupUserInput(getStGr(inputStream, printer));
            return description;
        } else if (commandName.equals("update")) {
            UpdateCommandDescription description = new UpdateCommandDescription(argument);
            description.setStudyGroupUserInput(getStGr(inputStream, printer));
            return description;
        } else if (commandName.equals("remove_by_id")) return new RemoveByIdCommandDescription(argument);
        else if (commandName.equals("clear")) return new ClearCommandDescription(argument);
        else if (commandName.equals("remove_at")) return new RemoveAtCommandDescription(argument);
        else if (commandName.equals("count_less_than_should_be_expelled")) return new CountLessThanCommandDescription(argument);
        else if (commandName.equals("count_greater_than_students_count")) return new CountGraterThanCommandDescription(argument);
        else if (commandName.equals("filter_by_semester_enum")) return new FilterCommandDescription(argument);
        else throw new NoCommandException(commandName);
    }

    private static StudyGroupUserInput getStGr(InputStream inputStream, Consumer<String> printer) throws AnnotationException, IOException, InstantiationException, EndOfFileException, IllegalAccessException {
        Converter<StudyGroupUserInput> converter = new Converter<>(StudyGroupUserInput.class);
        Arrays.asList(new Class[]{Color.class, Country.class, FormOfEducation.class, Semester.class})
                .forEach(enumClass -> {
                    converter.addInstructionForEnum(enumClass);
                    converter.addPossibleValuesForEnum(enumClass);
                });
        converter.addInstruction(LocalDateTime.class, string -> {
            try {
                return LocalDateTime.parse(string);
            } catch (DateTimeParseException e) {
                throw new ConvertInstructionException(string);
            }
        });
        return converter.inputStreamToObject(inputStream, printer);
    }
}
