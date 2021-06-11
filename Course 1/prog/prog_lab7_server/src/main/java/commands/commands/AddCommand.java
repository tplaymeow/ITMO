package commands.commands;

import commands.commandDescriptions.CommandDescription;
import managers.CollectionManager;
import model.StudyGroup;
import model.userInput.StudyGroupUserInput;
import response.Response;

public class AddCommand extends Command {
    public AddCommand(CollectionManager collectionManager) {
        super("add", "добавить новый элемент в коллекцию", collectionManager);
    }

    @Override
    public Response execute(CommandDescription commandDescription) {
        getCollectionManager().add(new StudyGroup((StudyGroupUserInput) commandDescription.getObject()));
        return new Response("Команда выполнена.", true);
    }
}
