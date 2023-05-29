package commands;

import collectionManager.CollectionManager;
import exceptions.ArgumentsCountException;
import model.Semester;

/**
 * Класс команды execute_script. <b>execute_script file_name</b>: считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.
 */
public class ExecuteScriptCommand extends Command{
    /**
     * Конструктор - создание объекта с определенными значениями
     * @param collectionManager Collection manager, с которым команда будет работать
     */
    public ExecuteScriptCommand(CollectionManager collectionManager) {
        super("execute_script",
                "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.",
                collectionManager);
    }

    /**
     * Метод, отвечающий за исполнение команды
     * @param arguments  аргументы команды в виде строки
     */
    @Override
    public void execute(String arguments) throws ArgumentsCountException {
        if (argumentIsFilename(arguments)) {
            this.getCollectionManager().executeScript(arguments);
        } else
            throw new ArgumentsCountException();
    }
}
