package commands.commands;

import commands.commandDescriptions.CommandDescription;
import managers.CollectionManager;
import model.Semester;
import response.Response;

public class FilterCommand extends Command {
    public FilterCommand(CollectionManager collectionManager) {
        super("filter_by_semester_enum", "вывести элементы, значение поля semesterEnum которых равно заданному", collectionManager);
    }

    @Override
    public Response execute(CommandDescription commandDescription) {
        return new Response(getCollectionManager().filter((Semester) commandDescription.getObject()), true);
    }
}
