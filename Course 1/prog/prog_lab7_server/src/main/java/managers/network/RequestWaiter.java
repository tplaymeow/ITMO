package managers.network;

import commands.commandDescriptions.CommandDescription;
import managers.MultithreadingManager;
import org.apache.logging.log4j.Logger;
import response.Response;

import java.io.IOException;
import java.net.*;
import java.util.function.Function;

public class RequestWaiter implements Runnable {
    private DatagramSocket datagramSocket;
    private Function<CommandDescription, Response> action;
    private Logger logger;

    public RequestWaiter(int port, Logger logger) {
        try {
            SocketAddress serverAddress = new InetSocketAddress(port);
            datagramSocket = new DatagramSocket(serverAddress);
            this.logger = logger;
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                DatagramPacket datagramPacket = new DatagramPacket(new byte[2048], 2048);
                datagramSocket.receive(datagramPacket);
                MultithreadingManager.fixedThreadPoolSubmit(new DatagramPacketHandler(this.datagramSocket, datagramPacket, logger, action));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setAction(Function<CommandDescription, Response> action) {
        this.action = action;
    }
}
