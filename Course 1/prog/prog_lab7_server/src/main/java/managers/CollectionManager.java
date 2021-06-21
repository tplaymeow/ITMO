package managers;

import app.App;
import commands.commands.Command;
import model.Owner;
import model.Semester;
import model.StudyGroup;
import model.User;
import orm.ORM;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class CollectionManager {
    private final List<StudyGroup> collection;
    private final List<Owner> owners;

    private final Date date;
    private final App app;
    private ORM<StudyGroup> groupORM;
    private ORM<Owner> ownerORM;

    public CollectionManager(App app, DataSource dataSource) throws SQLException {
        this.app = app;
        this.collection = Collections.synchronizedList(new LinkedList<StudyGroup>());
        this.owners = Collections.synchronizedList(new LinkedList<Owner>());
        this.date = new Date();

        ownerORM = new ORM<>(dataSource, Owner.class);
        ownerORM.prepare();
        ownerORM.createTables();
        groupORM = ownerORM.getORMForClass(StudyGroup.class);

        owners.addAll(ownerORM.getObjects());
        owners.forEach(owner -> collection.addAll(owner.getStudyGroups()));
    }

    // Commands realization
    public boolean containsUser(User user) {
        return owners.stream().anyMatch(owner -> owner.equalsUser(user));
    }

    public int register(User user) {
        if (owners.stream().anyMatch(owner -> Objects.equals(owner.getLogin(), user.getLogin()))) return 1;
        else {
            Owner owner = new Owner(user.getLogin(), user.getPassword());
            try {
                ownerORM.save(owner, null);
                owners.add(owner);
                return 2;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return 0;
            }
        }
    }

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

    public int update(int id, StudyGroup studyGroup, User user) {
        Optional<Owner> owner = owners.stream().filter(owner1 -> owner1.equalsUser(user)).findFirst();
        if (owner.isPresent()) {
            Optional<StudyGroup> gr = owner.get().getStudyGroups().stream().filter(group -> group.getId() == id).findFirst();
            if (gr.isPresent()) {
                try {
                    studyGroup.setId(id);

                    groupORM.update(studyGroup);
                    owner.get().getStudyGroups().remove(gr.get());
                    collection.remove(gr.get());
                    owner.get().getStudyGroups().add(studyGroup);
                    collection.add(studyGroup);
                    return 1;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    return 2;
                }
            } else {
                return 3;
            }
        } else {
            return 0;
        }
    }

    public int removeById(int id, User user) {
        Optional<Owner> owner = owners.stream().filter(owner1 -> owner1.equalsUser(user)).findFirst();
        if (owner.isPresent()) {
            Optional<StudyGroup> gr = owner.get().getStudyGroups().stream().filter(group -> group.getId() == id).findFirst();
            if (gr.isPresent()) {
                try {
                    groupORM.remove(gr.get());
                    owner.get().getStudyGroups().remove(gr.get());
                    collection.remove(gr.get());
                    return 1;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    return 2;
                }
            } else {
                return 1;
            }
        } else {
            return 0;
        }
    }

    public String show() {
        return collection.stream()
                .map(Objects::toString)
                .collect(Collectors.joining("\n"));
    }

    public int addIfMin(StudyGroup group, User user) {
        if (collection.size() == 0 || group.compareTo(Collections.min(collection)) < 0) {
            return add(group, user);
        }
        return 4;
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
    public int add(StudyGroup studyGroup, User user) {
        Optional<Owner> owner = owners.stream().filter(owner1 -> owner1.equalsUser(user)).findFirst();
        if (owner.isPresent()) {
            try {
                groupORM.save(studyGroup, owner.get().getId());
                owner.get().getStudyGroups().add(studyGroup);
                collection.add(studyGroup);
                return 2;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return 0;
            }
        }
        return 1;
    }

    public boolean addAll(Collection<? extends StudyGroup> c) {
        return collection.addAll(c);
    }

    public int size() {
        return collection.size();
    }

    public void clear(User user) {
        Owner owner = owners.stream()
                .filter(owner1 -> owner1.equalsUser(user))
                .findFirst().get();
        for (StudyGroup sg:
                owner.getStudyGroups()) {
            try {
                groupORM.remove(sg);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        collection.removeAll(owner.getStudyGroups());
        owner.getStudyGroups().clear();
    }

    public StudyGroup remove(int index, User user) {
        Optional<Owner> owner = owners.stream()
                .filter(owner1 -> owner1.equalsUser(user))
                .findFirst();

        if (owner.map(owner1 -> owner1.getStudyGroups().contains(collection.get(index)))
                .orElse(false)) {
            try {
                groupORM.remove(collection.get(index));
                owner.get().getStudyGroups().remove(collection.get(index));
                return collection.remove(index);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
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
