package commands;

import collectionManager.CollectionManager;

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
    public void execute(String arguments) {
        if (arguments.length() == 0) {
            System.out.println("Тип: " + this.getCollectionManager().getClass());
            System.out.println("Время создания: " + this.getCollectionManager().getDate());
            System.out.println("Кол-во элементов: " + this.getCollectionManager().getCollection().size());
        } else {
            System.out.println("Не верное количество аргументов");
        }
    }
}
