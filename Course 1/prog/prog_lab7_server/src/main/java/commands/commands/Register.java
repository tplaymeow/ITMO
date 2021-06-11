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
        getCollectionManager().getApp().addUser(commandDescription.getUser());
        return new Response("успех", true);
    }
}
