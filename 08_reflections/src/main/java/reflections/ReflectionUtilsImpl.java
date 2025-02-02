package reflections;

import com.nix.jtc.reflection.Ignore;
import com.nix.jtc.reflection.Info;
import com.nix.jtc.reflection.ReflectionUtil;

import java.lang.reflect.Field;

public class ReflectionUtilsImpl implements ReflectionUtil {

    @Override
    public String toString(Object object) {
        if(object == null) {
            return ELEMENT_START+ELEMENT_END;
        }

        Object obj;
        Field[] list = object.getClass().getDeclaredFields();
        StringBuilder keyValueString = new StringBuilder();

        keyValueString.append(ELEMENT_START);

        for(Field f : list) {


            try {
                f.setAccessible(true);
                if(!f.isAnnotationPresent(Info.class)) {
                    continue;
                }

                if(!isBaseElement(f.getType()))
                    obj = toString(f.get(object));
                else
                    obj = f.get(object);

                keyValueString.append(f.getName()).append(KEY_VALUE_SEPARATOR).append(obj).append(ELEMENT_SEPARATOR);
            } catch (IllegalAccessException e) {
                System.err.println("Got an error while getting field access: " + e.getMessage());
            }
        }

        keyValueString.replace(keyValueString.length()-1, keyValueString.length(), "");

        keyValueString.append(ELEMENT_END);

        return keyValueString.toString();
    }

    @Override
    public boolean isTheSame(Object object1, Object object2) {

        if(object1 == null || object2 == null) {
            throw new NullPointerException("object1 or/and object2 is/are null");
        }

        if(!object1.getClass().equals(object2.getClass())) {
            return false;
        }

        Field[] fieldsOfObj_1 = object1.getClass().getDeclaredFields();

        for(Field field : fieldsOfObj_1) {
            if(field.isAnnotationPresent(Ignore.class) || !isBaseElement(field.getType())) {
                continue;
            }

            field.setAccessible(true);
            try {
                Object fieldValue1 = field.get(object1);
                Object fieldValue2 = field.get(object2);

                if(fieldValue1 == null && fieldValue2 == null) {
                    continue;
                }

                if(fieldValue1 != null && !fieldValue1.equals(fieldValue2) || !fieldValue2.equals(fieldValue1)) {
                    return false;
                }
            }
            catch(Exception e) {
                System.err.println("Got an error while getting field access: " + e.getMessage());
            }

        }

        return true;
    }

}
