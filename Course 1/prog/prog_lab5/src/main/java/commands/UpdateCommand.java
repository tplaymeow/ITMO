package commands;

import collectionManager.CollectionManager;

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
    public void execute(String arguments) {
        //TODO: сделать что-то
    }
}
