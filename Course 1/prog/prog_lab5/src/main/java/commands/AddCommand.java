package commands;

import collectionManager.CollectionManager;
import model.Coordinates;
import model.StudyGroup;
import utils.CustomBufferReader;
import utils.Validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Класс команды add.
 * <b>add {element}</b>: добавить новый элемент в коллекцию
 */
public class AddCommand extends Command{
    /**
     * Конструктор - создание объекта с определенными значениями
     * @param collectionManager Collection manager, с которым команда будет работать
     */
    public AddCommand(CollectionManager collectionManager) {
        super("add", "Добавляет новый элемент", collectionManager);
    }

    /**
     * Метод, отвечающий за исполнение команды
     * @param arguments  аргументы команды в виде строки
     */
    @Override
    public void execute(String arguments) {
        // TODO: dfqwdwq
        if (arguments.length() == 0) {
            try {
                CustomBufferReader reader = new CustomBufferReader(new InputStreamReader(System.in));
                String request;

                String name;
                Long coordinateX;
                double coordinateY;

                // name
                while (true) {
                    System.out.print("Введите имя: ");
                    request = reader.readLine();
                    if (Validator.validateField(StudyGroup.class.getDeclaredField("name"), request)) {
                        name = request;
                        break;
                    } else {
                        System.out.println("Произошла ошибка при вводе поля. Ожидался не пустой String");
                    }
                }

                // coordX
                while (true) {
                    System.out.print("\tВведите координату x: ");
                    request = reader.readLine();
                    if (Validator.validateField(Coordinates.class.getDeclaredField("x"), request)) {
                        coordinateX = Long.parseLong(request);
                        break;
                    } else {
                        System.out.println("Произошла ошибка при вводе поля. Ожидался не пустой String");
                    }
                }
            } catch (IOException | NoSuchFieldException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Не верное количество аргументов");
        }
    }

}
