package commands.commands;

import commands.commandDescriptions.CommandDescription;
import managers.CollectionManager;
import response.Response;

public class CountGreaterThanCommand extends Command {
    public CountGreaterThanCommand(CollectionManager collectionManager) {
        super("count_greater_than_students_count", "вывести количество элементов, значение поля studentsCount которых больше заданного", collectionManager);
    }

    @Override
    public Response execute(CommandDescription commandDescription) {
        return new Response(getCollectionManager().countGreaterThan(commandDescription.getValue()), true);
    }
}
