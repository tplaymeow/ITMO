package commands.commands;

import commands.commandDescriptions.AddCommandDescription;
import commands.commandDescriptions.CommandDescription;
import commands.commandDescriptions.UpdateCommandDescription;
import managers.CollectionManager;
import model.StudyGroup;
import response.Response;

public class UpdateCommand extends Command {
    public UpdateCommand(CollectionManager collectionManager) {
        super("update", "обновить значение элемента коллекции, id которого равен заданному", collectionManager);
    }

    @Override
    public Response execute(CommandDescription commandDescription) {
        UpdateCommandDescription update = (UpdateCommandDescription) commandDescription;
        getCollectionManager().update(update.getId(), new StudyGroup(update.getStudyGroupUserInput()));
        return new Response("Команда выполнена.", true);
    }
}
