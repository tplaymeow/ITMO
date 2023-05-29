import app.App;
import exceptions.BadArgumentException;
import managers.network.DatagramNetworkManager;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("src/main/resources/client.properties"));
        int port = Integer.parseInt(properties.getProperty("serverPort"));
        DatagramNetworkManager networkManager = new DatagramNetworkManager("localhost", port, 4096);
        App app = new App(networkManager);
        app.interactive(System.in);
    }
}
