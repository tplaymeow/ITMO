package commands;

import collectionManager.CollectionManager;
import exceptions.ArgumentsCountException;

/**
 * Класс команды count_less_than_should_be_expelled. <b>count_less_than_should_be_expelled shouldBeExpelled</b>: вывести количество элементов, значение поля shouldBeExpelled которых меньше заданного
 */
public class CountLessThanShouldBeExpelledCommand extends Command{
    /**
     * Конструктор - создание объекта с определенными значениями
     * @param collectionManager Collection manager, с которым команда будет работать
     */
    public CountLessThanShouldBeExpelledCommand(CollectionManager collectionManager) {
        super("count_less_than_should_be_expelled",
                "вывести количество элементов, значение поля shouldBeExpelled которых меньше заданного",
                collectionManager);
    }

    /**
     * Метод, отвечающий за исполнение команды
     * @param arguments  аргументы команды в виде строки
     */
    @Override
    public void execute(String arguments) throws ArgumentsCountException, NumberFormatException {
        if (argumentsCountIsEqual(arguments, 1)) {
            int inputCount = Integer.parseInt(arguments);
            this.getCollectionManager().countLessThanShouldBeExpelled(inputCount);
        } else {
            throw new ArgumentsCountException();
        }
    }
}
