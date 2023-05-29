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
        int code =  getCollectionManager().add(new StudyGroup((StudyGroupUserInput) commandDescription.getObject()), commandDescription.getUser());
        if (code == 2) return new Response("Группа успешно добавлена", true);
        if (code == 1) return new Response("Команда не выполнена. Логин или пароль не верен. Используйте команду login", false);
        return new Response("Что-то пошло нет так(.", false);
    }
}
