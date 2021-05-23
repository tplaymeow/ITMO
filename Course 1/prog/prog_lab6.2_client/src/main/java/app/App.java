package app;

import commands.CommandDescriptionFactory;
import commands.commandDescriptions.CommandDescription;
import exceptions.AnnotationException;
import exceptions.BadArgumentException;
import exceptions.EndOfFileException;
import exceptions.NoCommandException;
import managers.network.NetworkManagerInterface;
import response.Response;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class App {
    private final NetworkManagerInterface<CommandDescription, Response> network;
    private final InputStream inputStream;

    private static ArrayList<String> openedScripts = new ArrayList<>();
    private int recCount = 1;

    public App(NetworkManagerInterface<CommandDescription, Response> network, InputStream inputStream) {
        this.network = network;
        this.inputStream = inputStream;
    }

    public void interactive(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        while (true) {
            // Read command
            try {
                if (inputStream.available() > 0) {
                    String line = reader.readLine();

                    String commandName = line.split(" ", 2)[0];
                    String argument;
                    try { argument = line.split(" ", 2)[1]; }
                    catch (IndexOutOfBoundsException e) { argument = ""; }

                    if (commandName.equals("execute_script")) {
                        openedScripts.clear();
                        openedScripts.add(argument);
                        interactiveFile(new FileInputStream(argument));
                        continue;
                    }

                    CommandDescription commandDescription = CommandDescriptionFactory.getCommandDescription(line, inputStream, this::println);
                    network.send(commandDescription);
                }
            } catch (IOException ignored) {
            } catch (NoCommandException | BadArgumentException | AnnotationException | InstantiationException | EndOfFileException | IllegalAccessException e) {
                println(e.getMessage());
            }

            Response response;
            if (Objects.nonNull(response = network.receive())) {
                println(response.getMessage());
            }
        }
    }

    private void interactiveFile(InputStream inputStream) throws IOException, EndOfFileException, AnnotationException, NoCommandException, InstantiationException, IllegalAccessException, BadArgumentException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;

        while ((line = reader.readLine()) != null) {

            String commandName = line.split(" ", 2)[0];
            String argument;
            try { argument = line.split(" ", 2)[1]; }
            catch (IndexOutOfBoundsException e) { argument = ""; }

//            System.out.println(commandName);
//            System.out.println(recCount);

            if (commandName.equals("execute_script")) {
                if (recCount > 20) {
                    System.out.println("Выполнить ешё 10 раз? [Y/N]");
                    BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
                    String str = reader1.readLine();
                    if (str.equals("Y")) {
                        recCount = 0;
                        recCount++;
                        interactiveFile(new FileInputStream(argument));
                    } else recCount = 0;
                    reader1.close();
                } else {
                    recCount++;
                    interactiveFile(new FileInputStream(argument));
                }
                continue;
            }

            CommandDescription commandDescription = CommandDescriptionFactory.getCommandDescription(line, inputStream, this::println);
            network.send(commandDescription);

            Response response;
            if (Objects.nonNull(response = network.receive())) {
                println(response.getMessage());
            }
        }
    }

    public void println(String string) {
        System.out.println(string);
    }
}
