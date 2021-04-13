package commands;

import collectionManager.CollectionManager;
import exceptions.ArgumentsCountException;

/**
 * Класс команды save. <b>save</b>: сохранить коллекцию в файл
 */
public class SaveCommand extends Command{
    /**
     * Конструктор - создание объекта с определенными значениями
     * @param collectionManager Collection manager, с которым команда будет работать
     */
    public SaveCommand(CollectionManager collectionManager) {
        super("save", "сохранить коллекцию в файл", collectionManager);
    }

    /**
     * Метод, отвечающий за исполнение команды
     * @param arguments  аргументы команды в виде строки
     */
    @Override
    public void execute(String arguments) throws ArgumentsCountException {
        if (argumentsCountIsEqual(arguments, 0)) {
            getCollectionManager().save();
        } else if (argumentIsFilename(arguments)) {
            arguments = arguments.startsWith("'") && arguments.endsWith("'")
                    ? arguments.substring(1,arguments.length()-1)
                    : arguments;
            getCollectionManager().save(arguments);
        } else {
            throw new ArgumentsCountException();
        }
    }
}
