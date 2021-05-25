import commandDescriptions.*;
import exceptions.ArgumentsCountException;
import exceptions.UnknownCommandNameException;
import support.CommandName;

public class CommandDescriptionFactory {
    public CommandDescription getCommandDescription(String commandName, String arguments) throws UnknownCommandNameException, ArgumentsCountException {
        CommandName commandNameEnum = CommandName.findByName(commandName);
        switch (commandNameEnum) {
            case HELP:
                return getHelpCommandDescription(arguments);
            case INFO:
                return getInfoCommandDescription(arguments);
            case SHOW:
                return getShowCommandDescription(arguments);
            case FILTERLESSTHANNUMBEROFROOMS:
                return getFilterLessThanNumberOfRoomsCommandDescription(arguments);
        }
        throw new UnknownCommandNameException();
    }

    private HelpCommandDescription getHelpCommandDescription(String arguments) throws ArgumentsCountException {
        if (argumentsCountIsEqual(arguments, 0)) return new HelpCommandDescription();
        throw new ArgumentsCountException();
    }

    private InfoCommandDescription getInfoCommandDescription(String arguments) throws ArgumentsCountException {
        if (argumentsCountIsEqual(arguments, 0)) return new InfoCommandDescription();
        throw new ArgumentsCountException();
    }

    private ShowCommandDescription getShowCommandDescription(String arguments) throws ArgumentsCountException {
        if (argumentsCountIsEqual(arguments, 0)) return new ShowCommandDescription();
        throw new ArgumentsCountException();
    }

    // TODO: не забыть отловить где-то NumberFormatException
    private FilterLessThanNumberOfRoomsCommandDescription getFilterLessThanNumberOfRoomsCommandDescription(String arguments)
            throws ArgumentsCountException {
        if (argumentsCountIsEqual(arguments, 1)) {
            return new FilterLessThanNumberOfRoomsCommandDescription(Integer.parseInt(arguments));
        }
        throw new ArgumentsCountException();
    }

    protected final boolean argumentsCountIsEqual(String arguments, int count) {
        if (arguments.equals("")) return count == 0;
        else return arguments.split(" ").length == count;
    }
}
