import app.App;
import managers.network.DatagramNetworkManager;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("src/main/resources/server.properties"));
        int port = Integer.parseInt(properties.getProperty("serverPort"));

        Logger logger = LogManager.getLogger();

        DatagramNetworkManager networkManager = new DatagramNetworkManager(port);
        networkManager.setLogger(logger);
        App app = new App(args[0], networkManager, System.in, logger);
        app.interactiveMode();
    }
}
