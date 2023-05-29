package commands;

import collectionManager.CollectionManager;
import exceptions.ArgumentsCountException;

/**
 * Класс команды info. <b>info</b>: Выводит информацию о колекции
 */
public class InfoCommand extends Command {
    /**
     * Конструктор - создание объекта с определенными значениями
     * @param collectionManager Collection manager, с которым команда будет работать
     */
    public InfoCommand(CollectionManager collectionManager) {
        super("info", "Выводит информацию о колекции", collectionManager);
    }

    /**
     * Метод, отвечающий за исполнение команды
     * @param arguments  аргументы команды в виде строки
     */
    @Override
    public void execute(String arguments) throws ArgumentsCountException {
        if (argumentsCountIsEqual(arguments, 0)) {
            this.getCollectionManager().info();
        } else {
            throw new ArgumentsCountException();
        }
    }
}
