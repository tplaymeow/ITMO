package collectionManager;

import commands.Command;
import model.Semester;
import model.StudyGroup;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;

public class CollectionManager{
    private LinkedList<StudyGroup> collection;
    private Command[] commands;
    private Date date;

    {
        this.date = new Date();
    }

    public CollectionManager(LinkedList<StudyGroup> collection, Command[] commands) {
        this.collection = collection;
        this.commands = commands;
    }

    public CollectionManager() {
    }

    //Реализации команд
    public void clear() {
        this.collection.clear();
        this.date = new Date();
    }

    public void countGreaterThanStudentsCount(int inputCount) {
        int count = 0;
        for (StudyGroup group :
                collection) {
            if (group.getStudentsCount() > inputCount) count++;
        }
        System.out.println(count);
    }

    public void countLessThanShouldBeExpelled(int inputCount) {
        int count = 0;
        for (StudyGroup group :
                collection) {
            if (group.getShouldBeExpelled() < inputCount) count++;
        }
        System.out.println(count);
    }

    public void filterBySemesterEnum(Semester semester) {
        for (StudyGroup group :
                collection) {
            if (group.getSemesterEnum().equals(semester)) System.out.println(group);
        }
    }

    public void help() {
        for (Command command : commands) {
            System.out.println(command.getName() + " " + command.getDescription());
        }
    }

    public void info() {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");

        System.out.println("Тип: " + StudyGroup.class);
        System.out.println("Время создания: " + formatter.format(date));
        System.out.println("Кол-во элементов: " + collection.size());
    }

    public void removeAt(int index) {
        try {
            collection.remove(index);}
        catch (IndexOutOfBoundsException e) {
            System.out.println("Неверный индекс");
        }
    }

    public void removeById(int id) {
        collection.removeIf(studyGroup -> studyGroup.getId() == id);
    }

    public void sort() {
        //Сортировка по количеству
        collection.sort(Comparator.comparingInt(StudyGroup::getStudentsCount));
    }

    // Гетеры и сеттеры
    public LinkedList<StudyGroup> getCollection() {
        return collection;
    }

    public void setCollection(LinkedList<StudyGroup> collection) {
        this.collection = collection;
    }

    public Command[] getCommands() {
        return commands;
    }

    public void setCommands(Command[] commands) {
        this.commands = commands;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }
}
