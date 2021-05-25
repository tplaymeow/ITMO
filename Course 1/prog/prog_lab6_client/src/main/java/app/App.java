package app;

import java.io.*;
import java.net.Socket;

/**
 * Класс приложения
 */
public class App {
    //    private CollectionManager collectionManager;
    private final Socket socket;

    public App(Socket socket) throws IOException, InterruptedException {
        this.socket = socket;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        DataOutputStream clientOut = new DataOutputStream(socket.getOutputStream());
        DataInputStream clientIn = new DataInputStream(socket.getInputStream());
        System.out.println("Client connected to socket.");
        System.out.println();
        System.out.println("Client writing channel = oos & reading channel = ois initialized.");
        while (!socket.isOutputShutdown()) {
            if (br.ready()) {

// данные появились - работаем
                System.out.println("Client start writing in channel...");
                Thread.sleep(1000);
                String clientCommand = br.readLine();

// пишем данные с консоли в канал сокета для сервера
                clientOut.writeUTF(clientCommand);
                clientOut.flush();
                System.out.println("Clien sent message " + clientCommand + " to server.");
                Thread.sleep(1000);
                System.out.println(clientIn.readUTF());

            }
        }
    }


}
