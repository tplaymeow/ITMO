import app.App;
import exceptions.BadArgumentException;
import managers.network.DatagramNetworkManager;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, BadArgumentException {
        DatagramNetworkManager networkManager = new DatagramNetworkManager("localhost", 4100, 4096);
        App app = new App(networkManager, System.in);
        app.interactive(System.in);
    }
}
