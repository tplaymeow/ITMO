package commands.commands;

import commands.commandDescriptions.CommandDescription;
import managers.CollectionManager;
import model.StudyGroup;
import model.userInput.StudyGroupUserInput;
import response.Response;

public class UpdateCommand extends Command {
    public UpdateCommand(CollectionManager collectionManager) {
        super("update", "обновить значение элемента коллекции, id которого равен заданному", collectionManager);
    }

    @Override
    public Response execute(CommandDescription commandDescription) {
        int code = getCollectionManager().update(commandDescription.getValue(),
                new StudyGroup((StudyGroupUserInput) commandDescription.getObject()),
                commandDescription.getUser());
        if (code == 1) return new Response("Команда выполнена.", true);
        if (code == 0) return new Response("Команда не выполнена. Логин или пароль не верен. Используйте команду login", false);
        if (code == 3) return new Response("У вас нет элемента с таким id.", false);
        return new Response("Что-то пошло нет так(.", false);
    }
}
