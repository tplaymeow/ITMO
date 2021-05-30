package managers.network;

import commands.commandDescriptions.CommandDescription;
import org.apache.logging.log4j.Logger;
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
    private static int BUF_SZ = 4096;

    private Logger logger;

    private final DatagramChannel channel = DatagramChannel.open();
    private final Selector selector = Selector.open();

    public DatagramNetworkManager(int port) throws IOException {
        this.channel.bind(new InetSocketAddress(port));
        this.channel.configureBlocking(false);
        this.channel.register(selector, SelectionKey.OP_READ, new ChannelAtt());
    }

    class ChannelAtt {
        ByteBuffer request;
        ByteBuffer response;
        SocketAddress address;

        public ChannelAtt() {
            this.request = ByteBuffer.allocate(BUF_SZ);
        }
    }

    @Override
    public void readObjectWithAction(Function<CommandDescription, Response> action) {
        try {
            selector.selectNow();
            Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();
            while (selectedKeys.hasNext()) {
                SelectionKey key = selectedKeys.next();
                selectedKeys.remove();

                if (key.isValid()) {
                    if (key.isReadable()) {
                        read(key, action);
                    } else if (key.isWritable()) {
                        write(key);
                        key.interestOps(SelectionKey.OP_READ);
                    }
                }

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException ignored) { }
    }

    @Override
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    private void read(SelectionKey key, Function<CommandDescription, Response> action) throws IOException, ClassNotFoundException {
        DatagramChannel channel = (DatagramChannel) key.channel();
        ChannelAtt att = (ChannelAtt) key.attachment();
        att.address = channel.receive(att.request);

        ByteArrayInputStream bais = new ByteArrayInputStream(att.request.array());
        ObjectInputStream ois = new ObjectInputStream(bais);

        CommandDescription object = (CommandDescription) ois.readObject();
        logger.info("Получена команда " + object.getName());

        Response response = action.apply(object);

        if (Objects.nonNull(response)) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);

            oos.writeObject(response);

            att.response = ByteBuffer.wrap(baos.toByteArray());
            key.interestOps(SelectionKey.OP_WRITE);
        } else {
            att.request.clear();
            att.response.clear();
        }
    }

    private void write(SelectionKey key) throws IOException {
        DatagramChannel channel = (DatagramChannel) key.channel();
        ChannelAtt att = (ChannelAtt) key.attachment();

        channel.send(att.response, att.address);
        logger.info("Отправлен ответ");

        att.response.clear();
        att.request.clear();
    }
}
