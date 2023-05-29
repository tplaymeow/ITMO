package commands;

import collectionManager.CollectionManager;
import exceptions.ArgumentsCountException;

/**
 * Класс команды help. <b>help</b>: Выводит информацию о всех командах
 */
public class HelpCommand extends Command {
    /**
     * Конструктор - создание объекта с определенными значениями
     * @param collectionManager Collection manager, с которым команда будет работать
     */
    public HelpCommand(CollectionManager collectionManager) {
        super("help", "Выводит информацию о всех командах", collectionManager);
    }

    /**
     * Метод, отвечающий за исполнение команды
     * @param arguments  аргументы команды в виде строки
     */
    @Override
    public void execute(String arguments) throws ArgumentsCountException {
        if (argumentsCountIsEqual(arguments, 0)) {
            this.getCollectionManager().help();
        } else {
            throw new ArgumentsCountException();
        }
    }
}
