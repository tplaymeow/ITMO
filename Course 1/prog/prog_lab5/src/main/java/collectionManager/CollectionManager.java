package collectionManager;

import commands.Command;
import model.StudyGroup;

import java.util.Date;
import java.util.LinkedList;

public class CollectionManager{
    private LinkedList<StudyGroup> collection;
    private Command[] commands;
    private Date date;

    public CollectionManager(LinkedList<StudyGroup> collection, Command[] commands) {
        this.collection = collection;
        this.commands = commands;
    }

    public CollectionManager() {
    }

    public LinkedList<StudyGroup> getCollection() {
        return collection;
    }

    public void setCollection(LinkedList<StudyGroup> collection) {
        this.collection = collection;
    }

    public Command[] getCommands() {
        return commands;
    }

    public Date getDate() {
        return date;
    }
}
