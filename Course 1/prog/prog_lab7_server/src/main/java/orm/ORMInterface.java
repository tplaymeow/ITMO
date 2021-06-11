package orm;

import java.sql.SQLException;
import java.util.List;

public interface ORMInterface<T> {
    boolean save(T object) throws SQLException;

    boolean save(T object, Integer id) throws SQLException;

    List<T> getObjects() throws SQLException;
    List<T> getObjects(String ... fetch) throws SQLException;
    boolean remove(T object) throws SQLException;
    boolean update(T object) throws SQLException;
}
