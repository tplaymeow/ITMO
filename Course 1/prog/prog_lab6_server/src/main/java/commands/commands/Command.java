package commands.commands;

import commands.commandDescriptions.CommandDescription;
import managers.CollectionManager;
import response.Response;

import java.util.Objects;

public abstract class Command {
    private final String name;
    private final String description;
    private final CollectionManager collectionManager;

    public Command(String name, String description, CollectionManager collectionManager) {
        this.name = name;
        this.description = description;
        this.collectionManager = collectionManager;
    }

    abstract public Response execute(CommandDescription commandDescription);

    // Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public CollectionManager getCollectionManager() {
        return collectionManager;
    }

    // Equals, HashCode, ToString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Command command = (Command) o;
        return Objects.equals(getName(), command.getName()) &&
                Objects.equals(getDescription(), command.getDescription()) &&
                Objects.equals(getCollectionManager(), command.getCollectionManager());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDescription(), getCollectionManager());
    }

    @Override
    public String toString() {
        return name + " - " + description;
    }
}
