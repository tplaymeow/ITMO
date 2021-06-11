package managers;

import app.App;
import commands.commands.Command;
import model.Semester;
import model.StudyGroup;
import orm.ORM;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class CollectionManager {
    private final List<StudyGroup> collection;

    private final Date date;
    private final App app;
    private ORM<StudyGroup> groupORM;

    public CollectionManager(App app) {
        this.app = app;
        this.collection = Collections.synchronizedList(new LinkedList<StudyGroup>());
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
        Optional<StudyGroup> gr = collection.stream().filter(group -> group.getId() == id).findFirst();
        if (gr.isPresent()) {
            try {
                groupORM.remove(gr.get());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        collection.removeIf(group -> group.getId() == id);
    }

    public String show() {
        return collection.stream()
                .map(Objects::toString)
                .collect(Collectors.joining("\n"));
    }

    public void addIfMin(StudyGroup group) {
        if (collection.size() == 0 || group.compareTo(Collections.min(collection)) < 0) {
            try {
                groupORM.save(group, group.getId());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            collection.add(group);
        }
    }

    public void sort() {
        Collections.sort(collection);
    }

    public String filter(Semester semester) {
        return collection.stream()
                .filter(group -> group.getSemesterEnum().equals(semester))
                .map(StudyGroup::toString)
                .collect(Collectors.joining("\n"));
    }

    // Delegate methods
    public boolean add(StudyGroup studyGroup) {
        try {
            groupORM.save(studyGroup, studyGroup.getId());
            System.out.println("dfsfdfdsfsd");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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

    public List<StudyGroup> getCollection() {
        return collection;
    }

    public App getApp() {
        return app;
    }

    public void setGroupORM(ORM<StudyGroup> groupORM) {
        this.groupORM = groupORM;
    }
}
