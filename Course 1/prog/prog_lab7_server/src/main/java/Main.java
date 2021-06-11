import app.App;
import managers.network.DatagramNetworkManager;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.postgresql.ds.PGSimpleDataSource;

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


        DatagramNetworkManager networkManager = new DatagramNetworkManager(port);
        networkManager.setLogger(logger);
        App app = new App("table.csv", networkManager, System.in, logger, dataSource);
        app.interactiveMode();
    }
}
