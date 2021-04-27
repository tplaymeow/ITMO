//import collectionManager.CollectionManager;
//import model.StudyGroup;
//import utils.CSVConstructor;
//import utils.Parser;
//import utils.Validator;
//
//import java.lang.reflect.InvocationTargetException;
//import java.util.Scanner;
//
//public class App {
//    CollectionManager collectionManager;
//
//    public App() {
//        this.collectionManager = new CollectionManager();
//        Parser parser = new Parser(StudyGroup.class);
//        Validator validator = new Validator(StudyGroup.class);
//        try {
//            //this.collectionManager.setCollection(parser.collectionFromData(CSVConstructor.loadFromData("StudyGroups.csv")));
//            if (!validator.validate(this.collectionManager.getCollection())) {
//                System.out.println("Данные не удовлетворяют правилам");
//                System.exit(0);
//            }
//        } catch (InvocationTargetException | IllegalAccessException | InstantiationException | NoSuchFieldException | NoSuchMethodException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public void interactiveMod() {
//        Scanner scanner = new Scanner(System.in);
//        CollectionManager collectionManager = new CollectionManager();
//
//    }
//}
