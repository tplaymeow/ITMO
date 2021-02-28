package commands;

import collectionManager.CollectionManager;
import model.StudyGroup;

import java.util.Iterator;

/**
 * Класс команды remove_by_id. <b>remove_by_id id</b>: удалить элемент из коллекции по его id
 */
public class RemoveByIdCommand extends Command {
    /**
     * Конструктор - создание объекта с определенными значениями
     * @param collectionManager Collection manager, с которым команда будет работать
     */
    public RemoveByIdCommand(CollectionManager collectionManager) {
        super("remove_by_id", "удалить элемент из коллекции по его id", collectionManager);
    }

    /**
     * Метод, отвечающий за исполнение команды
     * @param arguments  аргументы команды в виде строки
     */
    @Override
    public void execute(String arguments) {
        if (arguments.split(" ").length == 1) {
            try {
                int id = Integer.parseInt(arguments);
                getCollectionManager().getCollection().removeIf(studyGroup -> studyGroup.getId() == id);
            } catch (NumberFormatException e) {
                System.out.println("Не верный формат числа");
            }
        } else {
            System.out.println("Не верное количество аргументов");
        }
    }
}
