package commands.commands;

import commands.commandDescriptions.CommandDescription;
import managers.CollectionManager;
import response.Response;

public class RemoveByIdCommand extends Command {
    public RemoveByIdCommand(CollectionManager collectionManager) {
        super("remove_by_id", "удалить элемент из коллекции по его id", collectionManager);
    }

    @Override
    public Response execute(CommandDescription commandDescription) {
        getCollectionManager().removeById(commandDescription.getValue());
        return new Response("Команда выполнена.", true);
    }
}
