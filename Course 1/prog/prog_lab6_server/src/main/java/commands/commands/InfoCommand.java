package commands.commands;

import commands.commandDescriptions.CommandDescription;
import managers.CollectionManager;
import response.Response;

public class InfoCommand extends Command {
    public InfoCommand(CollectionManager collectionManager) {
        super("info", "вывести в стандартный поток вывода информацию о коллекции", collectionManager);
    }

    @Override
    public Response execute(CommandDescription commandDescription) {
        return new Response(getCollectionManager().info(), true);
    }
}
