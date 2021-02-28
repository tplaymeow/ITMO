import utils.Parser;
import utils.Validator;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
////        Integer h1 = 5;
////        ArrayList<A> list = new ArrayList<>();
////        list.add(new A());
//
//        Parser parser = new Parser(A.class);
//        ArrayList<String> names = parser.getNames();
//        names.forEach(System.out::println);
//        try {
//            ArrayList<String> values = parser.objectToData(new A());
//            values.forEach(System.out::println);
//            A a_new = (A) parser.objectFromData(names, values);
//            System.out.println("\n\n\n\n\n");
//            System.out.println(a_new);
//        } catch (IllegalAccessException | NoSuchFieldException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
//            e.printStackTrace();
//        }
//
        A a = new A();
        Validator validator = new Validator(A.class);
        System.out.println(validator.validate(a));
        // System.out.println(slova.class.isEnum());
        // System.out.println(new Integer(6) > 11);

        slova.valueOf("govno");

    }
}

enum slova {
    privet, poka
}

