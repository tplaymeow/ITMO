package commands;

import collectionManager.CollectionManager;
import model.Semester;

/**
 * Класс команды filter_by_semester_enum. <b>filter_by_semester_enum semesterEnum</b>: вывести элементы, значение поля semesterEnum которых равно заданному
 */
public class FilterBySemesterEnumCommand extends Command {
    /**
     * Конструктор - создание объекта с определенными значениями
     *
     * @param collectionManager Collection manager, с которым команда будет работать
     */
    public FilterBySemesterEnumCommand(CollectionManager collectionManager) {
        super("filter_by_semester_enum", "вывести элементы, значение поля semesterEnum которых равно заданному", collectionManager);
    }

    /**
     * Метод, отвечающий за исполнение команды
     *
     * @param arguments аргументы команды в виде строки
     */
    @Override
    public void execute(String arguments) {
        if (arguments.split(" ").length == 1) {
            try {
                Semester semester = Semester.valueOf(arguments);
                this.getCollectionManager().filterBySemesterEnum(semester);
            } catch (IllegalArgumentException e) {
                System.out.println("Не верное значение аргумента");
            }
        } else System.out.println("Не верное количество аргументов");
    }
}
