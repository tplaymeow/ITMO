package managers.network;

import commands.commandDescriptions.CommandDescription;
import response.Response;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Objects;

public class DatagramNetworkManager implements NetworkManagerInterface<CommandDescription, Response> {
    private final DatagramChannel channel;
    private final SocketAddress serverAddress;
    private final int bufferSize;
    private final ByteBuffer buffer;

    public DatagramNetworkManager(String host, int port, int bufferSize) throws IOException {
        this.serverAddress = new InetSocketAddress(host, port);
        this.channel = DatagramChannel.open();
        this.channel.configureBlocking(false);
        this.bufferSize = bufferSize;
        this.buffer = ByteBuffer.allocate(bufferSize);
    }

    @Override
    public void send(CommandDescription commandDescription) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream(bufferSize);
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(commandDescription);
            channel.send(ByteBuffer.wrap(baos.toByteArray()), serverAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Response receive() {
        buffer.clear();
        try {
            if (Objects.nonNull(channel.receive(buffer))) {
                ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(buffer.array()));
                return (Response) ois.readObject();
            } else return null;
        } catch (IOException | ClassNotFoundException ignored) { return null; }
    }
}
