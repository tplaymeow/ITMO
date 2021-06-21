import app.App;
import managers.network.DatagramNetworkManager;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import managers.network.RequestWaiter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.postgresql.ds.PGSimpleDataSource;
import utils.Hasher;

public class Main {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        try (FileReader fileReader = new FileReader("src/main/resources/config.properties")) {
            properties.load(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        properties.load(new FileReader("src/main/resources/server.properties"));
        int port = Integer.parseInt(properties.getProperty("serverPort"));

        Logger logger = LogManager.getLogger();

        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL(properties.getProperty("db_url"));
        dataSource.setUser(properties.getProperty("db_user"));
        dataSource.setPassword(properties.getProperty("db_password"));


        RequestWaiter requestWaiter = new RequestWaiter(port, logger);
        App app = new App(requestWaiter, System.in, logger, dataSource);
        app.interactiveMode();
    }
}
