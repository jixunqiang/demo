package basic.model;

import org.springframework.lang.Nullable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class ModelEntity {
    public ModelEntity setAttributes(@Nullable Map<String, ?> attributes) {
        if (attributes != null) {
            attributes.forEach((name, val) -> {
                String firstStr = name.substring(0, 1);
                String nameUpper =  firstStr.toUpperCase() + name.substring(1, name.length());
                System.out.println(nameUpper + "#" + val);
                Class clazz = this.getClass();
                for (Method method : clazz.getDeclaredMethods()) {
                    if (method.getName().equals("set" + nameUpper)) {
                        try {
                            method = clazz.getDeclaredMethod("get" + nameUpper);
                            System.out.println("函数" + method);
                            method.invoke(this.getClass(), val);
                        } catch (NoSuchMethodException e) {
                            throw new RuntimeException(e);
                        } catch (InvocationTargetException e) {
                            throw new RuntimeException(e);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            });
        }
        return this;
    }
}
