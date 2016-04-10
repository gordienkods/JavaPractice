package reflection.hometsk;

import reflection.hometsk.parser.JsonParser;

public class App {

    public static void main(String[] args) {
       Human json = new Human();
       JsonParser parser = new JsonParser();
       System.err.println(parser.toJson(json));
    }

}
