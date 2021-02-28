package commands;

import collectionManager.CollectionManager;

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
    public void execute(String arguments) {
        //TODO: сделать что-то
    }
}
