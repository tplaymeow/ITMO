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
        int code = getCollectionManager().removeById(commandDescription.getValue(), commandDescription.getUser());
        if (code == 1) return new Response("Команда выполнена.", true);
        if (code == 0) return new Response("Команда не выполнена. Логин или пароль не верен. Используйте команду login", false);
        return new Response("Что-то пошло нет так(.", false);
    }
}
