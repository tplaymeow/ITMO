package commands;

import collectionManager.CollectionManager;
import exceptions.ArgumentsCountException;

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
    public void execute(String arguments) throws ArgumentsCountException, NumberFormatException {
        if (argumentsCountIsEqual(arguments, 1)) {
            int id = Integer.parseInt(arguments);
            this.getCollectionManager().removeById(id);
        } else {
            throw new ArgumentsCountException();
        }
    }
}
