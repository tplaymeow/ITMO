package commands.commands;

import commands.commandDescriptions.CommandDescription;
import managers.CollectionManager;
import response.Response;

public class HelpCommand extends Command {
    public HelpCommand(CollectionManager collectionManager) {
        super("help", "вывести справку по доступным командам", collectionManager);
    }

    @Override
    public Response execute(CommandDescription commandDescription) {
        return new Response(getCollectionManager().help(), true);
    }
}
