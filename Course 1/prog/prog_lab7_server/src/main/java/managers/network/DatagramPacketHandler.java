package managers.network;

import commands.commandDescriptions.CommandDescription;
import managers.MultithreadingManager;
import org.apache.logging.log4j.Logger;
import response.Response;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.function.Function;

public class DatagramPacketHandler implements Runnable {
    private DatagramPacket packet;
    private Logger logger;
    private Function<CommandDescription, Response> action;
    private DatagramSocket datagramSocket;

    public DatagramPacketHandler(DatagramSocket datagramSocket, DatagramPacket packet, Logger logger, Function<CommandDescription, Response> action) {
        this.packet = packet;
        this.datagramSocket = datagramSocket;
        this.logger = logger;
        this.action = action;
    }

    @Override
    public void run() {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData());
            ObjectInputStream ois = new ObjectInputStream(bais);

            CommandDescription object = (CommandDescription) ois.readObject();
            logger.info("Получена команда " + object.getName() + ". Отправитель: " + object.getUser().getLogin());

            MultithreadingManager.cachedThreadPoolSubmit(() -> {
                Response response = action.apply(object);
                MultithreadingManager.forkJoinPoolSubmit(new ResponseSender(datagramSocket, packet, response, logger));
            });
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
