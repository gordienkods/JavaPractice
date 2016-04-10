package reflection.hometsk.annotations_handler;

import reflection.hometsk.annotations.CustomDateFormat;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomDateFormatHandler {

    public static String toHandle(Object obj, Field field){
        Object fieldValue = null;
        try {
            fieldValue = field.get(obj);
        } catch (Exception e){
            e.printStackTrace();
        }
        for (Annotation annotation : field.getAnnotations()){
            if (annotation.annotationType().equals(CustomDateFormat.class)){
                CustomDateFormat format = field.getAnnotation(CustomDateFormat.class);
                String dateFormatPattern = format.dateFormatPattern();
                try {
                    LocalDateTime dt = (LocalDateTime) field.get(obj);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormatPattern);
                    return dt.format(formatter);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return fieldValue.toString();
    }

}
