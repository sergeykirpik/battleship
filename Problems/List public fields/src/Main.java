import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 Get list of public fields the object declares (inherited fields should be skipped).
 */
class FieldGetter {

    public String[] getPublicFields(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        List<String> publicFields = new ArrayList<>();
        for (Field field: fields) {
            int modifiers = field.getModifiers();
            if (Modifier.isPublic(modifiers)) {
                publicFields.add(field.getName());
            }
        }
        return publicFields.toArray(new String[0]);
    }

}