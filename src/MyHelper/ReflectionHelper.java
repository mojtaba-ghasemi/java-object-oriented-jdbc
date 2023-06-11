package MyHelper;

import MyDBManager.DBField;
import MyDBManager.ModelMethodType;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ReflectionHelper {

    private static Object getFiledValue(Object dbValue, DBField field) {
        Object resultValue;
        switch (field.dataType()) {
            case "UUID":
                resultValue = UUID.fromString(dbValue.toString());
                break;
            case "int":
                resultValue = Integer.valueOf(dbValue.toString());
                break;
            case "LocalDate":
                resultValue = LocalDate.parse(dbValue.toString().substring(0, 10));
                break;
            default:
                resultValue = dbValue.toString();
        }
        return resultValue;
    }

    public static Object fillObject(Object object, ResultSet resultSet) throws SQLException, InvocationTargetException, IllegalAccessException, InstantiationException {

        Object resultObject = copyObject(object);

        for (Method method : object.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(DBField.class)) {
                DBField dbField = method.getAnnotation(DBField.class);
                if (dbField.methodType() == ModelMethodType.SETTER) {
                    method.invoke(resultObject, getFiledValue(resultSet.getObject(dbField.dbFieldName()), dbField));
                }
            }
        }


        return resultObject;
    }

    public static List<Object> fillObjectList(Object object, ResultSet resultSet) throws SQLException, InvocationTargetException, IllegalAccessException, InstantiationException {

        List<Object> resultObjectList = new ArrayList<>();
        while (resultSet.next()) {
            resultObjectList.add(fillObject(object, resultSet));
        }
        return resultObjectList;
    }

    public static <T> T copyObject(T entity) throws IllegalAccessException, InstantiationException {
        Class<?> clazz = entity.getClass();
        T newEntity = (T) entity.getClass().newInstance();

        while (clazz != null) {
            copyFields(entity, newEntity, clazz);
            clazz = clazz.getSuperclass();
        }

        return newEntity;
    }

    private static <T> T copyFields(T entity, T newEntity, Class<?> clazz) throws IllegalAccessException {
        List<Field> fields = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            fields.add(field);
        }
        for (Field field : fields) {
            field.setAccessible(true);
            field.set(newEntity, field.get(entity));
        }
        return newEntity;
    }
}
