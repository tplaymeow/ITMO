package commands.commands;

import commands.commandDescriptions.CommandDescription;
import managers.CollectionManager;
import response.Response;

public class CountLessThanCommand extends Command {
    public CountLessThanCommand(CollectionManager collectionManager) {
        super("count_less_than_should_be_expelled", "вывести количество элементов, значение поля shouldBeExpelled которых меньше заданного", collectionManager);
    }

    @Override
    public Response execute(CommandDescription commandDescription) {
        if (getCollectionManager().containsUser(commandDescription.getUser())) {
            return new Response(getCollectionManager().countLessThan(commandDescription.getValue()), true);
        }
        return new Response("Команда не выполнена. Логин или пароль не верен. Используйте команду login", false);
    }
}
