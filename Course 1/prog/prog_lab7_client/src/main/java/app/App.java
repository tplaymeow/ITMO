package app;

import commands.commandDescriptions.CommandDescription;
import commands.commandDescriptions.CommandDescriptionFactory;
import exceptions.AnnotationException;
import exceptions.BadArgumentException;
import exceptions.EndOfFileException;
import exceptions.NoCommandException;
import managers.network.NetworkManagerInterface;
import model.User;
import response.Response;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public class App {
    private final NetworkManagerInterface<CommandDescription, Response> network;

    private User user;

    private final Map<String, Integer> openScripts = new HashMap<>();
    private boolean fileMode = false;

    public App(NetworkManagerInterface<CommandDescription, Response> network) {
        this.network = network;
    }

    public void interactive(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String command;
        while (true) {
            // Read command string
            if (inputStream.available() > 0) {
                command = reader.readLine();
                network.send(CommandDescriptionFactory.get(command));
            }
        }
    }

    public void println(String string) {
        System.out.println(string);
    }

    private boolean askAboutRecursion() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String userAnswer;
        while (true) {
            println("В файле присутствует рекурсия. Хотите выполнить еще 10 шагов? [Y/N] ");
            userAnswer = bufferedReader.readLine();
            if (userAnswer.equals("Y")) {
                return true;
            } else if (userAnswer.equals("N")) return false;
            else println("Не верный ответ.");
        }
    }

    private boolean askAboutResend() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String userAnswer;
        while (true) {
            println("Команда не дошла до сервера. Переотправить? [Y/N] ");
            userAnswer = bufferedReader.readLine();
            if (userAnswer.equals("Y")) {
                return true;
            } else if (userAnswer.equals("N")) return false;
            else println("Не верный ответ.");
        }
    }

    private boolean askAboutRegister() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String userAnswer;
        while (true) {
            println("Хотите зарегистрироваться? [Y/N] ");
            userAnswer = bufferedReader.readLine();
            if (userAnswer.equals("Y")) {
                return true;
            } else if (userAnswer.equals("N")) return false;
            else println("Не верный ответ.");
        }
    }
}
