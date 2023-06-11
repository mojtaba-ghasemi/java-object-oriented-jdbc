package MyDBManager;

import MyHelper.ReflectionHelper;
import javafx.util.Pair;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static MyHelper.ReflectionHelper.fillObject;

public class JDBCHelper {

    private static Connection connection;

    private static Connection getMySQLConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            String jdbcUrl = "jdbc:mysql://localhost/humanresource?user=root&password=sa@123";
            try {
                connection = DriverManager.getConnection(jdbcUrl);
                System.out.println("Connected!");
            } catch (SQLException ex) {
                throw ex;
            }

        }
        return connection;
    }

    //region insert

    private static Pair<String, String> getFields4Insert(Object object) throws InvocationTargetException, IllegalAccessException {
        StringBuilder fieldsName = new StringBuilder();
        StringBuilder fieldsValue = new StringBuilder();

        for (Method method : object.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(DBField.class)) {
                DBField dbField = method.getAnnotation(DBField.class);
                if (dbField.methodType() == ModelMethodType.GETTER && dbField.isIdentity() == false) {
                    fieldsName.append(dbField.dbFieldName()).append(",");
                    fieldsValue.append("\"").append(method.invoke(object).toString()).append("\"").append(",");
                }
            }
        }
        if (fieldsName.length() > 0) {
            fieldsName.deleteCharAt(fieldsName.length() - 1);
            fieldsValue.deleteCharAt(fieldsValue.length() - 1);
        }

        return new Pair(fieldsName.toString(), fieldsValue.toString());
    }

    private static String getInsertQuery(Object object) throws InvocationTargetException, IllegalAccessException {
        StringBuilder stringBuilder = new StringBuilder();
        if (object.getClass().isAnnotationPresent(DBTable.class)) {
            Pair<String, String> fields = getFields4Insert(object);

            DBTable dbTable = object.getClass().getDeclaredAnnotation(DBTable.class);
            stringBuilder.append("insert into ").append(dbTable.dbTableName());
            stringBuilder.append("(").append(fields.getKey()).append(")");
            stringBuilder.append("values").append("(").append(fields.getValue());
            stringBuilder.append(")");
        }

        return stringBuilder.toString();
    }

    public static void insert(Object object) throws RuntimeException {

        try {
            Statement stmt = getMySQLConnection().createStatement();
            stmt.execute(getInsertQuery(object));

            stmt.close();
        } catch (SQLException | InvocationTargetException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }

    //endregion

    //region getObject
    public static Object getObject(Object object, String criteria) throws RuntimeException {

        Object resultObject = null;
        try {
            if (object.getClass().isAnnotationPresent(DBTable.class)) {
                String tableName = object.getClass().getAnnotation(DBTable.class).dbTableName();
                StringBuilder query = new StringBuilder();

                query.append("select * from ").append(tableName).append(" where ").append(criteria);

                Statement stmt = getMySQLConnection().createStatement();
                ResultSet resultSet = stmt.executeQuery(query.toString());

                if (resultSet.next())
                    resultObject = fillObject(object, resultSet);

                resultSet.close();
                stmt.close();

            }
        } catch (SQLException | InvocationTargetException | IllegalAccessException | InstantiationException ex) {
            throw new RuntimeException(ex);
        }

        return resultObject;
    }

    public static Object getObject(Object object, UUID uuid) throws RuntimeException {
        return getObject(object, "GUID = \"".concat(uuid.toString()).concat("\""));
    }

    public static Object getObject(Object object, int id) throws RuntimeException {
        return getObject(object, "ID = " + id);
    }

    public static List<Object> getObjectList(Object object, String criteria) throws RuntimeException {

        List<Object> resultObjectList = new ArrayList<>();
        try {
            if (object.getClass().isAnnotationPresent(DBTable.class)) {
                String tableName = object.getClass().getAnnotation(DBTable.class).dbTableName();
                StringBuilder query = new StringBuilder();

                query.append("select * from ").append(tableName).append(" where ").append(criteria);

                Statement stmt = getMySQLConnection().createStatement();
                ResultSet resultSet = stmt.executeQuery(query.toString());

                resultObjectList = ReflectionHelper.fillObjectList(object, resultSet);

                resultSet.close();
                stmt.close();

            }
        } catch (SQLException | InvocationTargetException | IllegalAccessException | InstantiationException ex) {
            throw new RuntimeException(ex);
        }

        return resultObjectList;
    }

    //endregion

    //region delete
    public static void delete(Object object, String criteria) throws RuntimeException {

        try {
            if (object.getClass().isAnnotationPresent(DBTable.class)) {
                String tableName = object.getClass().getAnnotation(DBTable.class).dbTableName();
                StringBuilder query = new StringBuilder();

                query.append("delete from ").append(tableName).append(" where ").append(criteria);

                Statement stmt = getMySQLConnection().createStatement();
                stmt.execute(query.toString());

                stmt.close();

            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void delete(Object object, int id) throws RuntimeException {
        delete(object, "ID = " + id);
    }

    public static void delete(Object object, UUID uuid) throws RuntimeException {
        delete(object, "GUID = \"" + uuid.toString() + "\"");
    }
    //endregion
}

