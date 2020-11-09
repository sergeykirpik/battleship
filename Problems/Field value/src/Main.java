import java.lang.reflect.Field;
/**
 * Get value for a given public field or null if the field does not exist or is not accessible.
 */
class FieldGetter {

    public Object getFieldValue(Object object, String fieldName) throws IllegalAccessException {
        Field field;
        try {
            field = object.getClass().getField(fieldName);
        } catch (NoSuchFieldException e) {
            return null;
        }
        return field.get(object);
    }

//    public static void main(String[] args) throws IllegalAccessException {
//        Object value = new FieldGetter().getFieldValue(new Test(), "a");
//        System.out.println(value);
//    }
//
//    static class Test {
//        public String a = "Test";
//    }

}