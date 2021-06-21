package commands.commands;

import commands.commandDescriptions.CommandDescription;
import managers.CollectionManager;
import response.Response;

import java.util.Objects;

public class RemoveAtCommand extends Command {
    public RemoveAtCommand(CollectionManager collectionManager) {
        super("remove_at", "удалить элемент из коллекции по его id", collectionManager);
    }

    @Override
    public Response execute(CommandDescription commandDescription) {
        try {
            if (Objects.nonNull(getCollectionManager().remove(commandDescription.getValue(), commandDescription.getUser()))) {
                return new Response("Команда выполнена.", true);
            }
            return new Response("Этот элемент не принадлежит вам.", true);
        } catch (IndexOutOfBoundsException e) {
            return new Response("Не верный индекс", false);
        }
    }
}
