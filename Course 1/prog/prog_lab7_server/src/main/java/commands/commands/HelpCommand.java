package commands.commands;

import commands.commandDescriptions.CommandDescription;
import managers.CollectionManager;
import response.Response;

public class HelpCommand extends Command {
    public HelpCommand(CollectionManager collectionManager) {
        super("help", "вывести справку по доступным командам", collectionManager);
    }

    @Override
    public Response execute(CommandDescription commandDescription) {
        if (getCollectionManager().containsUser(commandDescription.getUser())) {
            return new Response(getCollectionManager().help(), true);
        }
        return new Response("Команда не выполнена. Логин или пароль не верен. Используйте команду login", false);
    }
}
