package commands.commands;

import commands.commandDescriptions.CommandDescription;
import managers.CollectionManager;
import response.Response;

public class ShowCommand extends Command {
    public ShowCommand(CollectionManager collectionManager) {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении", collectionManager);
    }

    @Override
    public Response execute(CommandDescription commandDescription) {
        if (getCollectionManager().containsUser(commandDescription.getUser())) {
            return new Response(getCollectionManager().show(), true);
        }
        return new Response("Команда не выполнена. Логин или пароль не верен. Используйте команду login", false);

    }
}
