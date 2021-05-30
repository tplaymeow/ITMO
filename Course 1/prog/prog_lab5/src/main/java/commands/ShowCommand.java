package commands;


import collectionManager.CollectionManager;
import exceptions.ArgumentsCountException;

/**
 * Класс команды add.
 * <b>show</b>: добавить новый элемент в коллекцию
 */
public class ShowCommand extends Command{
    /**
     * Конструктор - создание объекта с определенными значениями
     * @param collectionManager Collection manager, с которым команда будет работать
     */
    public ShowCommand(CollectionManager collectionManager) {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении", collectionManager);
    }

    /**
     * Метод, отвечающий за исполнение команды
     * @param arguments  аргументы команды в виде строки
     */
    @Override
    public void execute(String arguments) throws ArgumentsCountException {
        if (argumentsCountIsEqual(arguments, 0)) {
            getCollectionManager().show();
        } else {
            throw new ArgumentsCountException();
        }
    }

}
