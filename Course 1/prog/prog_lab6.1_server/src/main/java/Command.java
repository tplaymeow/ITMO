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

    public Command(String name, String description, CollectionManager collectionManager) {
        this.name = name;
        this.description = description;
        this.collectionManager = collectionManager;
    }
    abstract public void execute();
}