package managers;

import app.App;
import commands.commands.Command;
import model.Semester;
import model.StudyGroup;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class CollectionManager {
    private LinkedList<StudyGroup> collection;

    private Date date;
    private final App app;

    public CollectionManager(App app) {
        this.app = app;
        this.collection = new LinkedList<>();
        this.date = new Date();
    }

    // Commands realization
    public String help() {
        return app.getCommands().stream()
                .map(Command::toString)
                .collect(Collectors.joining("\n"));
    }

    public String info() {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
        return "Тип: " + StudyGroup.class + "\n" +
        "Время создания: " + formatter.format(date) + "\n" +
        "Кол-во элементов: " + collection.size();
    }

    public void update(int id, StudyGroup group) {
        for (int i = 0; i < collection.size(); i++) {
            if (collection.get(i).getId() == id)
                collection.set(i, group);
        }
    }

    public void removeById(int id) {
        collection.removeIf(group -> group.getId() == id);
    }

    public String show() {
        return collection.stream().map(Objects::toString).collect(Collectors.joining("\n"));
    }

    public void addIfMin(StudyGroup group) {
        System.out.println(group.compareTo(Collections.min(collection)));
        if (collection.size() == 0 || group.compareTo(Collections.min(collection)) < 0) {
            collection.add(group);
        }
    }

    public void sort() {
        Collections.sort(collection);
    }

    // Delegate methods
    public boolean add(StudyGroup studyGroup) {
        return collection.add(studyGroup);
    }

    public boolean addAll(Collection<? extends StudyGroup> c) {
        return collection.addAll(c);
    }

    public int size() {
        return collection.size();
    }

    public void clear() {
        collection.clear();
    }

    public StudyGroup remove(int index) {
        return collection.remove(index);
    }

    public String countLessThan(int num) {
        return String.valueOf(collection.stream().filter(group -> group.getShouldBeExpelled() < num).count());
    }

    public String countGreaterThan(int num) {
        return String.valueOf(collection.stream().filter(group -> group.getStudentsCount() > num).count());
    }

    public String fillter(Semester semester) {
        return collection.stream().filter(group -> group.getSemesterEnum().equals(semester)).map(StudyGroup::toString).collect(Collectors.joining("\n"));
    }

    public LinkedList<StudyGroup> getCollection() {
        return collection;
    }
}
