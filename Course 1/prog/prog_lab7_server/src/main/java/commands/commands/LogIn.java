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
        if (getCollectionManager().getApp().isUser(commandDescription.getUser())) {
            return new Response("Добро пожаловать", true);
        } else {
            return new Response("Неверный логин или пароль", false);
        }
    }
}
