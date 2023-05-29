package commands.commands;

import commands.commandDescriptions.CommandDescription;
import managers.CollectionManager;
import response.Response;

public class SortCommand extends Command {
    public SortCommand(CollectionManager collectionManager) {
        super("sort", "отсортировать коллекцию в естественном порядке", collectionManager);
    }

    @Override
    public Response execute(CommandDescription commandDescription) {
        getCollectionManager().sort();
        return new Response("Команда выполнена.", true);
    }
}
