import app.App;
import managers.network.DatagramNetworkManager;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        DatagramNetworkManager networkManager = new DatagramNetworkManager(4100, 4096);
        App app = new App("table.csv", networkManager, System.in);
        app.interactiveMode();
    }
}
