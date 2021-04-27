package commands;

import collectionManager.CollectionManager;

/**
 * Класс команды count_greater_than_students_count. <b>count_greater_than_students_count studentsCount</b>: вывести количество элементов, значение поля studentsCount которых больше заданного
 */
public class CountGreaterThanStudentsCountCommand extends Command {
    /**
     * Конструктор - создание объекта с определенными значениями
     *
     * @param collectionManager Collection manager, с которым команда будет работать
     */
    public CountGreaterThanStudentsCountCommand(CollectionManager collectionManager) {
        super("count_greater_than_students_count",
                "вывести количество элементов, значение поля studentsCount которых больше заданного",
                collectionManager);
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
                int inputCount = Integer.parseInt(arguments);
                this.getCollectionManager().countGreaterThanStudentsCount(inputCount);
            } catch (NumberFormatException e) {
                System.out.println("Не верный формат числа");
            }
        } else {
            System.out.println("Не верное количество аргументов");
        }
    }
}
