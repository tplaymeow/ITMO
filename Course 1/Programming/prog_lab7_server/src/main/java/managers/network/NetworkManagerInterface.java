package managers.network;

import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.util.function.Function;

public interface NetworkManagerInterface<T extends Serializable, S extends Serializable> {
    void readObjectWithAction(Function<T, S> action);
    void setLogger(Logger logger);
}
