import exceptions.ArgumentsCountException;
import exceptions.UnknownCommandNameException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class App {
    private final BufferedReader reader;
    private final CommandDescriptionFactory commandDescriptionFactory;
    private final Socket socket;
    private ObjectOutputStream objectOutputStream;

    public App(Socket socket) {
        this.socket = socket;
        reader = new BufferedReader(new InputStreamReader(System.in));
        commandDescriptionFactory = new CommandDescriptionFactory();
    }

    public void run() {
        while (true) {
            try {
                String argument = reader.readLine();
                String[] words = argument.trim().replaceAll(" +", " ").split(" ", 2);

                String args;
                String name = words[0];
                try {
                    args = words[1];
                } catch (ArrayIndexOutOfBoundsException e) {
                    args = "";
                }
                objectOutputStream.writeObject(commandDescriptionFactory.getCommandDescription(name, args));

            } catch (IOException ignored) {
            } catch (UnknownCommandNameException | ArgumentsCountException | NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
