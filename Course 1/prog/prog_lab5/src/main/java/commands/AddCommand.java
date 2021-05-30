package commands;


import collectionManager.CollectionManager;
import exceptions.ArgumentsCountException;

/**
 * Класс команды add.
 * <b>add {element}</b>: добавить новый элемент в коллекцию
 */
public class AddCommand extends Command{
    /**
     * Конструктор - создание объекта с определенными значениями
     * @param collectionManager Collection manager, с которым команда будет работать
     */
    public AddCommand(CollectionManager collectionManager) {
        super("add", "Добавляет новый элемент", collectionManager);
    }

    /**
     * Метод, отвечающий за исполнение команды
     * @param arguments  аргументы команды в виде строки
     */
    @Override
    // если нулл щвиряем ошибку
    public void execute(String arguments) throws ArgumentsCountException {
        if (argumentsCountIsEqual(arguments, 0)) {
            this.getCollectionManager().add();
        } else {
            throw new ArgumentsCountException();
        }
    }

}
