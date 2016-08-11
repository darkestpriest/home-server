package home_server.structure.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import home_server.structure.database.entities.Host;

import java.lang.reflect.Type;

/**
 * Created by Manuel Perez P. (darkpriestrelative@gmail.com) on 11/08/16.
 */
public class JsonParser<T> {

    private final String jsonString;

    public JsonParser(String jsonString) {
        this.jsonString = jsonString;
    }

    public T parseToObject(Class<T> object){
        try{
            ObjectMapper mapper = new ObjectMapper();
            T parsed = mapper.readValue(jsonString, object);
            return parsed;
        } catch (Exception e){
            System.out.println("EXCEPTION "+e);
            e.printStackTrace();
            return null;
        }

    }

}
