package lab2;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;

public class AnnotatedMethodsCaller {
    private static <T> T getDefaultValue(Class<T> tClass) {
        return (T) Array.get(Array.newInstance(tClass, 1), 0);
    }

    public static void callRepeatCallMethods(Object targetClass) {
        Method[] methods = targetClass.getClass().getDeclaredMethods();
        Arrays.sort(methods, Comparator.comparing(Method::getName));

        for (Method method : methods) {
            RepeatCall annotation = method.getAnnotation(RepeatCall.class);
            if (annotation != null) {
                if (!method.canAccess(targetClass))
                    method.setAccessible(true);

                Class<?>[] paramTypes = method.getParameterTypes();

                Object[] params = new Object[paramTypes.length];
                for (int i = 0; i < paramTypes.length; ++i) {
                    if (paramTypes[i].isPrimitive())
                        params[i] = getDefaultValue(paramTypes[i]);
                    else
                        params[i] = null;
                }

                try {
                    for (int i = 0; i < annotation.value(); ++i)
                        method.invoke(targetClass, params);
                    System.out.println();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}