package commands.commands;

import commands.commandDescriptions.AddCommandDescription;
import commands.commandDescriptions.AddIfMinCommandDescription;
import commands.commandDescriptions.CommandDescription;
import managers.CollectionManager;
import model.StudyGroup;
import response.Response;

public class AddIfMinCommand extends Command {
    public AddIfMinCommand(CollectionManager collectionManager) {
        super("add_if_min", "добавить новый элемент в коллекцию", collectionManager);
    }

    @Override
    public Response execute(CommandDescription commandDescription) {
        getCollectionManager().addIfMin(new StudyGroup(((AddIfMinCommandDescription) commandDescription).getStudyGroupUserInput()));
        return new Response("Команда выполнена.", true);
    }
}
