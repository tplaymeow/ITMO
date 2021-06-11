package commands.commands;

import commands.commandDescriptions.CommandDescription;
import managers.CollectionManager;
import response.Response;

public class UpdateCommand extends Command {
    public UpdateCommand(CollectionManager collectionManager) {
        super("update", "обновить значение элемента коллекции, id которого равен заданному", collectionManager);
    }

    @Override
    public Response execute(CommandDescription commandDescription) {
        return new Response("Команда выполнена.", true);
    }
}
