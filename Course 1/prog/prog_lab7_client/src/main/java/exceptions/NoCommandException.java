package exceptions;

public class NoCommandException extends Exception {
    public NoCommandException(String commandName) {
        super("Команды '" + commandName + "' не существует.");
    }
}
