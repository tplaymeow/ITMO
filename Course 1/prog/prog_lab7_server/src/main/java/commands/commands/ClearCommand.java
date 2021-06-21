package commands.commands;

import commands.commandDescriptions.CommandDescription;
import managers.CollectionManager;
import response.Response;

public class ClearCommand extends Command {
    public ClearCommand(CollectionManager collectionManager) {
        super("clear", "очистить коллекцию", collectionManager);
    }

    @Override
    public Response execute(CommandDescription commandDescription) {
        if (getCollectionManager().containsUser(commandDescription.getUser())) {
            getCollectionManager().clear(commandDescription.getUser());
            return new Response("Команда выполнена.", true);
        }
        return new Response("Команда не выполнена. Логин или пароль не верен. Используйте команду login", false);
    }
}
