package collectionManager;

import app.App;
import commands.Command;
import exceptions.AnnotationException;
import exceptions.EndOfFileException;
import model.Semester;
import model.StudyGroup;
import utils.Converter;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;

/**
 * Класс предназначенный для работы с коллекцией
 */
public class CollectionManager {
    private LinkedList<StudyGroup> collection;
    private Command[] commands;
    private Date date;
    private App app;

    private static ArrayList<String> openScripts = new ArrayList<>();
    private static int recursionsCount = 1;

    public CollectionManager(App app) {
        this.app = app;
        this.collection = new LinkedList<>();
        this.date = new Date();
    }

    // Реализация команд

    /**
     * Реализация команды {@link commands.ClearCommand}
     */
    public void clear() {
        this.collection.clear();
        this.date = new Date();
    }

    /**
     * Реализация команды {@link commands.CountGreaterThanStudentsCountCommand}
     */
    public void countGreaterThanStudentsCount(int inputCount) {
        int count = 0;
        for (StudyGroup group :
                collection) {
            if (group.getStudentsCount() > inputCount) count++;
        }
        app.println(String.valueOf(count));
    }

    /**
     * Реализация команды {@link commands.CountLessThanShouldBeExpelledCommand}
     */
    public void countLessThanShouldBeExpelled(int inputCount) {
        int count = 0;
        for (StudyGroup group :
                collection) {
            if (group.getShouldBeExpelled() < inputCount) count++;
        }
        app.println(String.valueOf(count));
    }

    /**
     * Реализация команды {@link commands.FilterBySemesterEnumCommand}
     */
    public void filterBySemesterEnum(Semester semester) {
        for (StudyGroup group :
                collection) {
            if (group.getSemesterEnum().equals(semester))
                app.println(group.toString());
        }
    }

    /**
     * Реализация команды {@link commands.HelpCommand}
     */
    public void help() {
        for (Command command : commands) {
            app.println(command.getName() + " " + command.getDescription());
        }
    }

    /**
     * Реализация команды {@link commands.InfoCommand}
     */
    public void info() {
        app.setFileMod(false);
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
        app.println("Тип: " + StudyGroup.class);
        app.println("Время создания: " + formatter.format(date));
        app.println("Кол-во элементов: " + collection.size());
        app.setFileMod(true);
    }

    /**
     * Реализация команды {@link commands.RemoveAtCommand}
     */
    public void removeAt(int index) {
        try {
            collection.remove(index);}
        catch (IndexOutOfBoundsException e) {
            // TODO: изменить вывод
            System.out.println("Неверный индекс");
        }
    }

    /**
     * Реализация команды {@link commands.RemoveByIdCommand}
     */
    public void removeById(int id) {
        collection.removeIf(studyGroup -> studyGroup.getId() == id);
    }

    /**
     * Реализация команды {@link commands.SortCommand}
     */
    public void sort() {
        Collections.sort(collection);
    }

    /**
     * Реализация команды {@link commands.ExecuteScriptCommand}
     */
//    public void executeScript(String fileName) {
//        try {
//            if (findCycles(fileName)) {
//                app.setFileMod(true);
//                app.interactive(new FileInputStream(fileName));
//                app.setFileMod(false);
//            } else {
//                app.println("В скрипте присутствуют циклы. Выполнение не возможно");
//            }
//        } catch (FileNotFoundException e) {
//            app.setFileMod(false);
//            app.println("Файл " + fileName + " не найден");
//        } catch (IOException e) {
//            app.println("Ошибка ввода " + e.getMessage());
//        } finally {
//            openScripts.clear();
//        }
//    }

    public void executeScript(String fileName) {
        try {
            if (openScripts.isEmpty()) {
                openScripts.add(fileName);
            } else if (openScripts.contains(fileName) && recursionsCount == 0) {
                openScripts.clear();
                openScripts.add(fileName);
                recursionsCount++;
            } else if (openScripts.get(0).equals(fileName)) {
                openScripts.clear();
                openScripts.add(fileName);
                recursionsCount++;
            }

            if (recursionsCount > 10) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                while (true) {
                    System.out.print("В программе присутствует рекрсия. Хотите выполнить еще 10 циклов? [Y/N] ");
                    String ans = reader.readLine();
                    if (ans.equals("Y")) {
                        recursionsCount = 1;
                        openScripts.clear();
                        openScripts.add(fileName);
                        break;
                    } else if (ans.equals("N")) {
                        openScripts.clear();
                        recursionsCount = 1;
                        return;
                    } else {
                        System.out.println("Не коректный ответ");
                    }
                }
            }

            app.setFileMod(true);
            app.interactive(new FileInputStream(fileName));
            app.setFileMod(false);

        } catch (IOException e) {
            app.println("Ошибка ввода " + e.getMessage());
        }
    }

    /**
     * Реализация команды {@link commands.AddCommand}
     */
    public void add() {
        Converter<StudyGroup> converter = new Converter<>(StudyGroup.class);
        try {
            collection.add(converter.inputStreamToObject(app.getStreamsStack().peek(), app::println));
        } catch (IllegalAccessException | InstantiationException | AnnotationException | IOException ignored) { }
        catch (EndOfFileException e) {
            app.setFileMod(false);
            app.println("Обнаружен конец файла. Команда add не выполнена");
        }
    }

    /**
     * Дополнительная реализация команды {@link commands.SaveCommand}
     */
    public void save(String fileName) {
        app.setOutputFileName(fileName);
        app.save();
    }

    /**
     * Реализация команды {@link commands.SaveCommand}
     */
    public void save() {
        app.save();
    }

    /**
     * Реализация команды {@link commands.UpdateCommand}
     */
    public void update(int index) {
        if (index >= collection.size()) {
            app.println("Индекс выходит за размеры коллекции");
            return;
        }
        Converter<StudyGroup> converter = new Converter<>(StudyGroup.class);
        try {
            collection.set(index, converter.inputStreamToObject(app.getStreamsStack().peek(), app::println));
        } catch (IllegalAccessException | InstantiationException | AnnotationException | IOException ignored) {
            ignored.printStackTrace();
        } catch (EndOfFileException e) {
            app.setFileMod(true);
            app.println("Обнаружен конец файла. Команда add не выполнена");
        }
    }

    /**
     * Реализация команды {@link commands.AddIfMinCommand}
     */
    public void addIfMin() {
        Converter<StudyGroup> converter = new Converter<>(StudyGroup.class);
        try {
            StudyGroup group = converter.inputStreamToObject(app.getStreamsStack().peek(), app::println);
            if (collection.size() == 0 || group.compareTo(Collections.min(collection)) < 0) {
                collection.add(group);
                app.println("Элемент добавлен в коллекцию");
            } else {
                app.println("Элемент не добавлен в коллекцию. Он не является наименьшим");
            }
        } catch (IllegalAccessException | InstantiationException | AnnotationException | IOException ignored) { }
        catch (EndOfFileException e) {
            app.setFileMod(true);
            app.println("Обнаружен конец файла. Команда add_if_min не выполнена");
        }
    }

    /**
     * Реализация команды {@link commands.ShowCommand}
     */
    public void show() {
        collection.forEach(studyGroup -> app.println(studyGroup.toString()));
    }

    /**
     * Проверяет есть ли в скрипте циклы
     * @param argument имя файла
     */
    private boolean findCycles(String argument) throws IOException {
        if (openScripts.contains(argument)) {
            openScripts.add(argument);
            return false;
        }
        BufferedReader reader = new BufferedReader(new FileReader(argument));
        openScripts.add(argument);
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.split(" ", 2)[0].equals("execute_script")) {
                if (!findCycles(line.split(" ", 2)[1])) {
                    reader.close();
                    return false;
                }
            } else if (line.equals("exit")) {
                reader.close();
                return true;
            }
        }
        reader.close();
        return true;
    }

    // Гетеры и сетеры

    /**
     * getter для поля {@link CollectionManager#collection}
     */
    public LinkedList<StudyGroup> getCollection() {
        return collection;
    }

    /**
     * setter для поля {@link CollectionManager#collection}
     */
    public void setCollection(LinkedList<StudyGroup> collection) {
        this.collection = collection;
    }

    /**
     * getter для поля {@link CollectionManager#commands}
     */
    public Command[] getCommands() {
        return commands;
    }

    /**
     * setter для поля {@link CollectionManager#commands}
     */
    public void setCommands(Command[] commands) {
        this.commands = commands;
    }
}
