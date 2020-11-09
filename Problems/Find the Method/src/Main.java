import java.lang.reflect.Method;

class MethodFinder {

    public static String findMethod(String methodName, String[] classNames) throws ClassNotFoundException {
        for (String className: classNames) {
            Class clazz = Class.forName(className);
            for (Method method: clazz.getMethods()) {
                if (method.getName().equals(methodName)) {
                    return className;
                }
            }
        }
        return "";
    }

    public static void main(String[] args) throws ClassNotFoundException {
        String[] classNames = {
            "java.lang.String",
            "java.lang.StringBuffer",
            "java.lang.Math"
        };
        String method = findMethod("abs", classNames);
        System.out.println(method);
    }
}