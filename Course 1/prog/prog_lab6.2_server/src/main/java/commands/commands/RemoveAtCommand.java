package commands.commands;

import commands.commandDescriptions.CommandDescription;
import commands.commandDescriptions.RemoveAtCommandDescription;
import commands.commandDescriptions.RemoveByIdCommandDescription;
import managers.CollectionManager;
import response.Response;

public class RemoveAtCommand extends Command {
    public RemoveAtCommand(CollectionManager collectionManager) {
        super("remove_at", "удалить элемент из коллекции по его id", collectionManager);
    }

    @Override
    public Response execute(CommandDescription commandDescription) {
        RemoveAtCommandDescription remove = (RemoveAtCommandDescription) commandDescription;
        try {
            getCollectionManager().remove(remove.getIndex());
            return null;
        } catch (IndexOutOfBoundsException e) {
            return new Response("Не верный индекс", false);
        }
    }
}
