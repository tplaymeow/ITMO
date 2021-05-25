
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(47876)) {
            Socket client = server.accept();

// после хэндшейкинга сервер ассоциирует подключающегося клиента с этим сокетом-соединением
            System.out.print("Connection accepted.");

// инициируем каналы для  общения в сокете, для сервера

// канал записи в сокет
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            System.out.println("DataOutputStream  created");

            // канал чтения из сокета
            DataInputStream in = new DataInputStream(client.getInputStream());
            System.out.println("DataInputStream created");
            while (!client.isClosed()) {

                System.out.println("Server reading from channel");

// сервер ждёт в канале чтения (inputstream) получения данных клиента
                String entry = in.readUTF();

// после получения данных считывает их
                System.out.println("READ from client message - " + entry);

// и выводит в консоль
                System.out.println("Server try writing to channel");
                out.writeUTF("Hello + " + client.getInetAddress());
            }
        }
    }
}
