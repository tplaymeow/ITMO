package commands.commands;

import commands.commandDescriptions.CommandDescription;
import commands.commandDescriptions.RemoveAtCommandDescription;
import commands.commandDescriptions.RemoveByIdCommandDescription;
import managers.CollectionManager;
import response.Response;

public class RemoveByIdCommand extends Command {
    public RemoveByIdCommand(CollectionManager collectionManager) {
        super("remove_by_id", "удалить элемент из коллекции по его id", collectionManager);
    }

    @Override
    public Response execute(CommandDescription commandDescription) {
        RemoveByIdCommandDescription remove = (RemoveByIdCommandDescription) commandDescription;
        getCollectionManager().removeById(remove.getId());
        return new Response("Команда выполнена.", true);
    }
}
