package managers.network;

import commands.commandDescriptions.CommandDescription;
import response.Response;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.function.Function;

public class NetworkManager {
    private Selector selector;
    private static int BUF_SZ = 4096;

    public NetworkManager(int port) throws IOException {
        DatagramChannel channel = DatagramChannel.open();
        channel.bind(new InetSocketAddress(port));
        channel.configureBlocking(false);

        selector = Selector.open();
        channel.register(selector, SelectionKey.OP_READ);
    }

    public void interactive(Function<CommandDescription, Response> action) {
        while (true) {
            try {
                selector.select();
                Iterator<SelectionKey> selectedKeys = selector.selectedKeys().stream().filter(SelectionKey::isValid).iterator();
                while (selectedKeys.hasNext()) {
                    try {
                        SelectionKey key = selectedKeys.next();
                        selectedKeys.remove();

                        if (key.isReadable()) {

                            key.interestOps(SelectionKey.OP_WRITE);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void read(SelectionKey key, Function<CommandDescription, Response> action) {

    }
}
