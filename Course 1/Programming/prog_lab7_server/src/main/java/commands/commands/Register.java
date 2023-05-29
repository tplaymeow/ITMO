package commands.commands;

import commands.commandDescriptions.CommandDescription;
import managers.CollectionManager;
import response.Response;

public class Register extends Command {
    public Register(CollectionManager collectionManager) {
        super("register", "=)", collectionManager);
    }

    @Override
    public Response execute(CommandDescription commandDescription) {
        if (getCollectionManager().register(commandDescription.getUser()) == 2) {
            return new Response("Вы успешно зарегистрировались.", true);
        } else if (getCollectionManager().register(commandDescription.getUser()) == 1) {
            return new Response("Данный логин занят.", false);
        } else {
            return new Response("Что-то пошло нет так(.", false);
        }
    }
}
