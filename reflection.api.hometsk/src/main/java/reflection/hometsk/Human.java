package reflection.hometsk;

import reflection.hometsk.annotations.CustomDateFormat;
import reflection.hometsk.annotations.JsonValue;

import java.time.LocalDateTime;

public class Human {
    @CustomDateFormat
    LocalDateTime dateTime_2 = LocalDateTime.now();
    public String null_1 = null;
    public String firstName = "Vasya";
    private String secondName = "Ivanov";
    public Integer age = 22;
    @JsonValue(name = "bablo2")
    public String null_2 = null;
    @JsonValue(name = "bablo")
    private static final Integer SALARY = 9999999;
    public String null_3 = null;
    @CustomDateFormat (dateFormatPattern = "HH:mm:ss MM/dd/yyyy")
    LocalDateTime dateTime_1 = LocalDateTime.now();
}
