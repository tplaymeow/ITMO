package managers.network;

import org.apache.logging.log4j.Logger;
import response.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ResponseSender implements Runnable {
    private DatagramPacket datagramPacket;
    private Response response;
    private DatagramSocket datagramSocket;
    private org.apache.logging.log4j.Logger logger;

    public ResponseSender(DatagramSocket datagramSocket, DatagramPacket packet, Response response, Logger logger) {
        this.datagramSocket = datagramSocket;
        this.datagramPacket = packet;
        this.response = response;
        this.logger = logger;
    }

    @Override
    public void run() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);

            oos.writeObject(response);

            DatagramPacket packet = new DatagramPacket(baos.toByteArray(), baos.size(), datagramPacket.getAddress(), datagramPacket.getPort());
            datagramSocket.send(packet);
            logger.info("Отправлен ответ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
