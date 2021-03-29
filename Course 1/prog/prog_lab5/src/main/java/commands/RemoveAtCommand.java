package commands;

import collectionManager.CollectionManager;
import model.Semester;
import model.StudyGroup;

/**
 * Класс команды remove_at. <b>remove_at index</b>: удалить элемент, находящийся в заданной позиции коллекции (index)
 */
public class RemoveAtCommand extends Command{
    /**
     * Конструктор - создание объекта с определенными значениями
     * @param collectionManager Collection manager, с которым команда будет работать
     */
    public RemoveAtCommand(CollectionManager collectionManager) {
        super("remove_at", "удалить элемент, находящийся в заданной позиции коллекции (index)", collectionManager);
    }

    /**
     * Метод, отвечающий за исполнение команды
     * @param arguments  аргументы команды в виде строки
     */
    @Override
    public void execute(String arguments) {
        if (arguments.split(" ").length == 1) {
            try {
                int index = Integer.parseInt(arguments);
                getCollectionManager().removeAt(index);
            } catch (NumberFormatException e) {
                System.out.println("Не верный формат числа");
            }
        } else {
            System.out.println("Не верное количество аргументов");
        }
    }
}
