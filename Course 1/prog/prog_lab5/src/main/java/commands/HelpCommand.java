package commands;

import collectionManager.CollectionManager;

/**
 * Класс команды help. <b>help</b>: Выводит информацию о всех командах
 */
public class HelpCommand extends Command {
    /**
     * Конструктор - создание объекта с определенными значениями
     * @param collectionManager Collection manager, с которым команда будет работать
     */
    public HelpCommand(CollectionManager collectionManager) {
        super("help", "Выводит информацию о всех командах", collectionManager);
    }

    /**
     * Метод, отвечающий за исполнение команды
     * @param arguments  аргументы команды в виде строки
     */
    @Override
    public void execute(String arguments) {
        if (arguments.length() == 0) {
            for (Command command : this.getCollectionManager().getCommands()) {
                System.out.println(command.getName() + " " + command.getDescription());
            }
        } else {
            System.out.println("Не верное количество аргументов");
        }
    }
}
