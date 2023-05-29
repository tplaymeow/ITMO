package managers.network;

import commands.commandDescriptions.CommandDescription;
import managers.MultithreadingManager;
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


    @Override
    public void readObjectWithAction(Function<CommandDescription, Response> action) {

    }

    @Override
    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}
