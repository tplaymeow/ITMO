package managers.network;

import commands.commandDescriptions.CommandDescription;
import response.Response;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Function;

public class ASyncUDPSvr {
    static int BUF_SZ = 4096;

    class Con {
        ByteBuffer req;
        ByteBuffer resp;
        SocketAddress sa;

        public Con() {
            req = ByteBuffer.allocate(BUF_SZ);
        }
    }

    static int port = 3333;

    private void process(Function<CommandDescription, Response> action) {
        try {
            Selector selector = Selector.open();
            DatagramChannel channel = DatagramChannel.open();
            InetSocketAddress isa = new InetSocketAddress(port);
            channel.socket().bind(isa);
            channel.configureBlocking(false);
            SelectionKey clientKey = channel.register(selector, SelectionKey.OP_READ);
            clientKey.attach(new Con());
            while (true) {
                try {
                    selector.select();
                    Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();
                    while (selectedKeys.hasNext()) {
                        try {
                            SelectionKey key = selectedKeys.next();
                            selectedKeys.remove();

                            if (!key.isValid()) {
                                continue;
                            }

                            if (key.isReadable()) {
                                read(key, action);
                            } else if (key.isWritable()) {
                                write(key);
                            }
                        } catch (IOException e) {
                            System.err.println("glitch, continuing... " + (e.getMessage() != null ? e.getMessage() : ""));
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    System.err.println("glitch, continuing... " + (e.getMessage() != null ? e.getMessage() : ""));
                }
            }
        } catch (IOException e) {
            System.err.println("network error: " + (e.getMessage() != null ? e.getMessage() : ""));
        }
    }

    private void read(SelectionKey key, Function<CommandDescription, Response> action) throws IOException, ClassNotFoundException {
        DatagramChannel chan = (DatagramChannel) key.channel();
        Con con = (Con) key.attachment();
        con.sa = chan.receive(con.req);

        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(con.req.array()));
        CommandDescription commandDescription = (CommandDescription) ois.readObject();
        Response response = action.apply(commandDescription);

        if (!Objects.isNull(response)) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream(con.resp.capacity());
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(response);
            key.interestOps(SelectionKey.OP_WRITE);
        } else {
            con.req.clear();
        }
    }

    private void write(SelectionKey key) throws IOException {
        DatagramChannel chan = (DatagramChannel) key.channel();
        Con con = (Con) key.attachment();
        chan.send(con.resp, con.sa);
        key.interestOps(SelectionKey.OP_READ);
    }
}