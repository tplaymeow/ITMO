package commands;

import collectionManager.CollectionManager;
import model.StudyGroup;

import java.util.Comparator;

/**
 * Класс команды sort. <b>sort</b>: отсортировать коллекцию в естественном порядке
 */
public class SortCommand extends Command {
    /**
     * Конструктор - создание объекта с определенными значениями
     * @param collectionManager Collection manager, с которым команда будет работать
     */
    public SortCommand(CollectionManager collectionManager) {
        super("sort", "отсортировать коллекцию в естественном порядке", collectionManager);
    }

    /**
     * Метод, отвечающий за исполнение команды
     * @param arguments  аргументы команды в виде строки
     */
    @Override
    public void execute(String arguments) {
        if (arguments.length() == 0) {
            this.getCollectionManager().sort();
        } else {
            System.out.println("Не верное количество аргументов");
        }
    }
}
