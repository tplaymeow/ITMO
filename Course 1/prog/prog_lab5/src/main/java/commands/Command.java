package commands;


import collectionManager.CollectionManager;

import java.util.Objects;


/**
 * Абстрактный класс команды.
 */
public abstract class Command {
    /**
     * Имя команды
     */
    private final String name;
    /**
     * Описание команды
     */
    private final String description;
    /**
     * Collection manager в котором будут производится изменения
     */
    private final CollectionManager collectionManager;

    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param name  имя
     * @param description  описание
     * @param collectionManager  Collection manager, с которым команда будет работать
     */
    public Command(String name, String description, CollectionManager collectionManager) {
        this.name = name;
        this.description = description;
        this.collectionManager = collectionManager;
    }

    /**
     * Абстрактный метод, отвечающий за исполнение команды
     * @param arguments  аргументы команды в виде строки
     */
    abstract public void execute(String arguments);

    /**
     * Метод, возвращающий значение {@link Command#name}
     * @return возвращает имя команды
     */
    public String getName() {
        return name;
    }

    /**
     * Метод, возвращающий значение {@link Command#description}
     * @return возвращает описание команды
     */
    public String getDescription() {
        return description;
    }

    /**
     * Метод, возвращающий значение {@link Command#collectionManager}
     * @return возвращает Collection manager в котором производятся изменения
     */
    public CollectionManager getCollectionManager() {
        return collectionManager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Command command = (Command) o;
        return Objects.equals(name, command.name) && Objects.equals(description, command.description) && Objects.equals(collectionManager, command.collectionManager);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, collectionManager);
    }

    @Override
    public String toString() {
        return name + " - " + description;
    }
}
