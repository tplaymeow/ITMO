package commands.commands;

import commands.commandDescriptions.CommandDescription;
import commands.commandDescriptions.CountLessThanCommandDescription;
import managers.CollectionManager;
import response.Response;

public class CountLessThanCommand extends Command {
    public CountLessThanCommand(CollectionManager collectionManager) {
        super("count_less_than_should_be_expelled", "вывести количество элементов, значение поля shouldBeExpelled которых меньше заданного", collectionManager);
    }

    @Override
    public Response execute(CommandDescription commandDescription) {
        CountLessThanCommandDescription count = (CountLessThanCommandDescription) commandDescription;
        return new Response(getCollectionManager().countLessThan(count.getId()), true);
    }
}
