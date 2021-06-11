package commands.commands;

import commands.commandDescriptions.CommandDescription;
import managers.CollectionManager;
import response.Response;

public class RemoveAtCommand extends Command {
    public RemoveAtCommand(CollectionManager collectionManager) {
        super("remove_at", "удалить элемент из коллекции по его id", collectionManager);
    }

    @Override
    public Response execute(CommandDescription commandDescription) {
        try {
            getCollectionManager().remove(commandDescription.getValue());
            return new Response("Команда выполнена.", true);
        } catch (IndexOutOfBoundsException e) {
            return new Response("Не верный индекс", false);
        }
    }
}
