package commands.commands;

import commands.commandDescriptions.CommandDescription;
import managers.CollectionManager;
import response.Response;

public class LogIn extends Command {
    public LogIn(CollectionManager collectionManager) {
        super("login", "", collectionManager);
    }

    @Override
    public Response execute(CommandDescription commandDescription) {
        if (getCollectionManager().containsUser(commandDescription.getUser())) {
            return new Response("Добро пожаловать, " + commandDescription.getUser().getLogin(), true);
        } else {
            return new Response("Пользователя с таким логином и паролем не существует", false);
        }
    }
}
