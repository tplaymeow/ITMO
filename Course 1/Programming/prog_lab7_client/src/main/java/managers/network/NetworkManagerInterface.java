package managers.network;

import java.io.Serializable;

public interface NetworkManagerInterface<T extends Serializable, S extends Serializable> {
    void send(T t);
    S receive();
}
