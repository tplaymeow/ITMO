package managers.network;

import commands.commandDescriptions.CommandDescription;
import managers.network.NetworkManagerInterface;
import response.Response;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Function;

public class DatagramNetworkManager implements NetworkManagerInterface<CommandDescription, Response> {
    private DatagramChannel channel = DatagramChannel.open();
    private Selector selector = Selector.open();
    private ByteBuffer buffer;

    public DatagramNetworkManager(int port, int bufferSize) throws IOException {
        this.channel.bind(new InetSocketAddress(port));
        this.channel.configureBlocking(false);
        this.channel.register(selector, SelectionKey.OP_READ);
        this.buffer = ByteBuffer.allocate(bufferSize);
    }

    @Override
    public void readObjectWithAction(Function<CommandDescription, Response> action) {
        buffer.clear();
        try {
            selector.select();

            Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();
            while (selectedKeys.hasNext()) {
                SelectionKey key = selectedKeys.next();

                if (key.isValid()) {
                    if (key.isReadable()) {
                        SocketAddress clientAddress = channel.receive(this.buffer);
                        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(buffer.array()));
                        CommandDescription object = (CommandDescription) ois.readObject();

                        System.out.println(object.getName());

                        Response response = action.apply(object);
                        if (Objects.nonNull(response)) {
                            ByteArrayOutputStream baos = new ByteArrayOutputStream(buffer.capacity());
                            ObjectOutputStream oos = new ObjectOutputStream(baos);
                            oos.writeObject(response);
                            channel.send(ByteBuffer.wrap(baos.toByteArray()), clientAddress);
                        }
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

//        buffer.clear();
//        try {
//            SocketAddress clientAddress = channel.receive(this.buffer);
//            if (Objects.nonNull(clientAddress)) {
//                ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(buffer.array()));
//                CommandDescription object = (CommandDescription) ois.readObject();
//                Response response = action.apply(object);
//                if (Objects.nonNull(response)) {
//                    ByteArrayOutputStream baos = new ByteArrayOutputStream(buffer.capacity());
//                    ObjectOutputStream oos = new ObjectOutputStream(baos);
//                    oos.writeObject(response);
//                    channel.send(ByteBuffer.wrap(baos.toByteArray()), clientAddress);
//                }
//            }
//        } catch (IOException | ClassNotFoundException ignored) {
//            ignored.printStackTrace();
//        }
    }
}
