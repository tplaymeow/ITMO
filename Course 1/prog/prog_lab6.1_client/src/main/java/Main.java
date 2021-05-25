import java.io.IOException;
import java.io.ObjectOutput;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost",43706);
            App app = new App(socket);
            app.run();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
