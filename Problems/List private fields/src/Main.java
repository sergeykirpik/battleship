import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 Get sorted list of private fields the object declares (inherited fields should be skipped).
 */
class FieldGetter {

    public List<String> getPrivateFields(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        List<String> privateFields = new ArrayList<>();
        for (Field field: fields) {
            int modifiers = field.getModifiers();
            if (Modifier.isPrivate(modifiers)) {
                privateFields.add(field.getName());
            }
        }
        privateFields.sort(String::compareTo);
        return privateFields;
    }

//    public static void main(String[] args) {
//        List<String> listOfFields = new FieldGetter().getPrivateFields(new ClassForTest());
//        System.out.println(listOfFields);
//    }
//
//    static class ClassForTest {
//        private int privateField2;
//        private int privateField1;
//        public int publicField1;
//    }

}