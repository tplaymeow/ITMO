import app.App;

import java.io.IOException;
import java.net.Socket;

public class Main {
//    public static void main(String[] args) {
//        try {
//            Socket socket = new Socket("localhost", 47876);
//            App app = new App(socket);
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//
//
//    }
    public static void main(String[] args) {
        Ab a = new A();
        a.say();
        System.out.println(a instanceof A);
    }
}

abstract class Ab {
    abstract void say();
}

class A extends Ab {
    String s = "hello";
    @Override
    void say() {
        System.out.println(s);
    }
    void sosi() {

    }
}
