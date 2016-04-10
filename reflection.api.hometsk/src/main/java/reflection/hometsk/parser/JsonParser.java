package reflection.hometsk.parser;

import reflection.hometsk.annotations_handler.CustomDateFormatHandler;
import reflection.hometsk.annotations_handler.JsonValueHandler;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class JsonParser {

    public String toJson(Object obj) {
        StringBuilder json = new StringBuilder();
        Class clazz = obj.getClass();
        Field[] allFields = clazz.getDeclaredFields();

        json.append("{\n");
        for (int i = 0; i < allFields.length; i++) {
            Boolean accessTrigger = false;
            if (!Modifier.isPublic(allFields[i].getModifiers())) {
                accessTrigger = true;
                allFields[i].setAccessible(true);
            }
            try {
                if (allFields[i].get(obj) != null) {
                    json.append("\"" + JsonValueHandler.toHandle(allFields[i]) + "\":\"" + CustomDateFormatHandler.toHandle(obj, allFields[i]) + "\",\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (accessTrigger) {
                allFields[i].setAccessible(false);
            }
        }
        json.append("}");
        return json.toString().replace(",\n}", "\n}");
    }

}




