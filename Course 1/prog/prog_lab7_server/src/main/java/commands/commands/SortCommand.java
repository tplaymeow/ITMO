package commands.commands;

import commands.commandDescriptions.CommandDescription;
import managers.CollectionManager;
import response.Response;

public class SortCommand extends Command {
    public SortCommand(CollectionManager collectionManager) {
        super("sort", "отсортировать коллекцию в естественном порядке", collectionManager);
    }

    @Override
    public Response execute(CommandDescription commandDescription) {
        if (getCollectionManager().containsUser(commandDescription.getUser())) {
            getCollectionManager().sort();
            return new Response("Команда выполнена.", true);
        }
        return new Response("Команда не выполнена. Логин или пароль не верен. Используйте команду login", false);

    }
}
