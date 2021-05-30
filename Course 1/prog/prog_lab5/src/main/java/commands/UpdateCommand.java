package commands;

import collectionManager.CollectionManager;
import exceptions.ArgumentsCountException;

/**
 * Класс команды update. <b>update id {element}</b>: обновить значение элемента коллекции, id которого равен заданному
 */
public class UpdateCommand extends Command {
    /**
     * Конструктор - создание объекта с определенными значениями
     * @param collectionManager Collection manager, с которым команда будет работать
     */
    public UpdateCommand(CollectionManager collectionManager) {
        super("update", "обновить значение элемента коллекции, id которого равен заданному", collectionManager);
    }

    /**
     * Метод, отвечающий за исполнение команды
     * @param arguments  аргументы команды в виде строки
     */
    @Override
    public void execute(String arguments) throws NumberFormatException, ArgumentsCountException {
        if (argumentsCountIsEqual(arguments, 1)) {
            int index = Integer.parseInt(arguments);
            getCollectionManager().update(index);
        } else {
            throw new ArgumentsCountException();
        }
    }
}
