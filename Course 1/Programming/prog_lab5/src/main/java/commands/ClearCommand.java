package commands;

import collectionManager.CollectionManager;
import exceptions.ArgumentsCountException;

/**
 * Класс команды clear. <b>clear</b>: очистить коллекцию
 */
public class ClearCommand extends Command{
    /**
     * Конструктор - создание объекта с определенными значениями
     * @param collectionManager Collection manager, с которым команда будет работать
     */
    public ClearCommand(CollectionManager collectionManager) {
        super("clear", "очищает коллекцию", collectionManager);
    }

    /**
     * Метод, отвечающий за исполнение команды
     * @param arguments  аргументы команды в виде строки
     */
    @Override
    public void execute(String arguments) throws ArgumentsCountException {
        if (argumentsCountIsEqual(arguments, 0)) {
            this.getCollectionManager().clear();
        } else {
            throw new ArgumentsCountException();
        }
    }
}
