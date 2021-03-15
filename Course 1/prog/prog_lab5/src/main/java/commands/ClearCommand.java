package commands;

import collectionManager.CollectionManager;

import java.util.Date;

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
    public void execute(String arguments) {
        if (arguments.length() == 0) {
            this.getCollectionManager().getCollection().clear();
            this.getCollectionManager().setDate(new Date());
        } else {
            System.out.println("Не верное количество аргументов");
        }
    }

}
