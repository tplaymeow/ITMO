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
        if (getCollectionManager().containsUser(commandDescription.getUser())) {
            return new Response(getCollectionManager().filter((Semester) commandDescription.getObject()), true);
        }
        return new Response("Команда не выполнена. Логин или пароль не верен. Используйте команду login", false);
    }
}
