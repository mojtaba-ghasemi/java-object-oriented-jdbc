package Service;

import Model.Person;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;


public interface DBPrimaryServices<T> {

    T insert(T object) throws SQLException;

    T update(T object);

    void delete(String criteria);

    void delete(int id);

    void delete(UUID uuid);

    T getObject(UUID uuid) throws SQLException, InvocationTargetException, IllegalAccessException;

    T getObject(int id) throws SQLException, InvocationTargetException, IllegalAccessException;

    T getObject(String criteria) throws SQLException, InvocationTargetException, IllegalAccessException;

    List<T> getObjectList(String criteria);
}
