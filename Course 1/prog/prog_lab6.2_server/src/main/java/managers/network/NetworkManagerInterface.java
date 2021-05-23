package managers.network;

import java.io.Serializable;
import java.util.Optional;
import java.util.function.Function;

public interface NetworkManagerInterface<T extends Serializable, S extends Serializable> {
    public void readObjectWithAction(Function<T, S> action);
}
