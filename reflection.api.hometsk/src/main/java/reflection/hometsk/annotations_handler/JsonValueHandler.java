package reflection.hometsk.annotations_handler;

import reflection.hometsk.annotations.JsonValue;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class JsonValueHandler {

    public static String toHandle(Field field){
        for (Annotation annotation : field.getAnnotations()){
            if (annotation.annotationType().equals(JsonValue.class)){
                JsonValue jsonValue = field.getAnnotation(JsonValue.class);
                String annotatedName = jsonValue.name();
                if (annotatedName.length() != 0 ){
                    return annotatedName;
                }
            }
        }
        return field.getName();
    }

}
