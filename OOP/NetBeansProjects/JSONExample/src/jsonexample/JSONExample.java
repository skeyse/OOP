package jsonexample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class JSONExample {

    public static void main(String[] args) throws JsonProcessingException, IOException {
        List<Person> people = new LinkedList<Person>();
        Person person = new Person();
        person.setId(UUID.randomUUID().toString());
        person.setFirstName("Sean");
        person.setLastName("Keyse");
        people.add(person);
        
        person = new Person();
        person.setId(UUID.randomUUID().toString());
        person.setFirstName("John");
        person.setLastName("Smith");
        people.add(person);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String json = objectMapper.writeValueAsString(people);
        System.out.println(json);
        
        /*json = "{\n" +
    "  \"id\" : \"42d5b585-e91a-424c-a2ce-524ba303bca6\",\n" +
"  \"firstName\" : \"Sean\",\n" +
"  \"lastName\" : \"Keyse\"\n" +
"}";
        
        person = objectMapper.readValue(json, Person.class);
                
        System.out.print(person);*/
    }
    
}
