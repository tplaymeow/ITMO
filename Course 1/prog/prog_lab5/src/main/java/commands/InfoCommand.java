package commands;

import collectionManager.CollectionManager;
import model.StudyGroup;

import java.text.SimpleDateFormat;

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
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");

            System.out.println("Тип: " + StudyGroup.class);
            System.out.println("Время создания: " + formatter.format(this.getCollectionManager().getDate()));
            System.out.println("Кол-во элементов: " + this.getCollectionManager().getCollection().size());
        } else {
            System.out.println("Не верное количество аргументов");
        }
    }
}
