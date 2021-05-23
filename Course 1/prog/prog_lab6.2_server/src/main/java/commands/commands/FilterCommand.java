package commands.commands;

import commands.commandDescriptions.CommandDescription;
import commands.commandDescriptions.FilterCommandDescription;
import managers.CollectionManager;
import response.Response;

public class FilterCommand extends Command {
    public FilterCommand(CollectionManager collectionManager) {
        super("filter_by_semester_enum", "вывести элементы, значение поля semesterEnum которых равно заданному", collectionManager);
    }

    @Override
    public Response execute(CommandDescription commandDescription) {
        FilterCommandDescription filter = (FilterCommandDescription) commandDescription;
        return new Response(getCollectionManager().fillter(filter.getSemester()), true);
    }
}
