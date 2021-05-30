package commands;

import collectionManager.CollectionManager;
import exceptions.ArgumentsCountException;

/**
 * Класс команды add_if_min. <b>add_if_min {element}</b>: добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции
 */
public class AddIfMinCommand extends Command{
    /**
     * Конструктор - создание объекта с определенными значениями
     * @param collectionManager Collection manager, с которым команда будет работать
     */
    public AddIfMinCommand(CollectionManager collectionManager) {
        super("add_if_min",
                "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции",
                collectionManager);
    }
    /**
     * Метод, отвечающий за исполнение команды
     * @param arguments  аргументы команды в виде строки
     */
    @Override
    public void execute(String arguments) throws ArgumentsCountException {
        if (argumentsCountIsEqual(arguments, 0)) {
            this.getCollectionManager().addIfMin();
        } else {
            throw new ArgumentsCountException();
        }
    }
}
