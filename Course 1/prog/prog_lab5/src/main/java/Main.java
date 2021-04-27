import app.App;
import model.*;
import utils.CSVConstructor;
import utils.Parser;
import utils.Validator;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        App app = new App("StudyGroups.csv");
//        app.interactive(System.in);
        try {
            noMain();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void noMain() throws IllegalAccessException {
        StudyGroup studyGroup = new StudyGroup("Крутая группа",
                new Coordinates(156L, 13.6),
                16,
                4,
                FormOfEducation.FULL_TIME_EDUCATION,
                Semester.FIRST,
                new Person("Тимурррррррррррр",
                        "31166703",
                        Color.BLACK,
                        Color.BLACK,
                        Country.FRANCE,
                        new Location(13.5f, 15L, 17, "Школа")));

        StudyGroup studyGroup1 = new StudyGroup("Еще одна крутая группа",
                new Coordinates(156L, 13.6),
                16,
                4,
                FormOfEducation.FULL_TIME_EDUCATION,
                Semester.FIRST,
                new Person("Лох",
                        "31166703",
                        Color.BLACK,
                        Color.RED,
                        Country.FRANCE,
                        new Location(13.5f, 15L, 17, "Школа")));

        LinkedList<StudyGroup> studyGroups = new LinkedList<>();
        studyGroups.add(studyGroup);
        studyGroups.add(studyGroup1);
        Parser parser = new Parser(StudyGroup.class);
        CSVConstructor.saveCSVFromData(parser.collectionToData(studyGroups), parser.getNames(), "StudyGroups3.csv");
    }
}
