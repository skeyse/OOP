package dbtest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Person {
    private UUID id;  //because Integer can be null, but int cannot be null
    private String firstName;
    private String lastName;

    public Person(String firstName, String lastName) {
        id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public Person() {
        
    }
    
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void save(Statement statement) throws SQLException {
        
        if(id == null || firstName == null || lastName == null 
                      || firstName.equals("") || lastName.equals("")) {
            throw new RuntimeException("Invalid (ID, First Name, Last Name)");
        }
        
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO person (id, first_name, last_name) VALUES (")
                .append("'").append(id).append("','")
                .append(firstName).append("','")
                .append(lastName).append("');");
        
        statement.executeUpdate(stringBuilder.toString());
    }
    
    
    public static void delete(UUID id, Statement statement) throws SQLException {
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append("DELETE FROM person WHERE id='")
                .append(id.toString()).append("';");
        
        System.out.println("Deleted Person");
        statement.execute(stringBuilder.toString());
    }
    
    public static List<Person> getAllPeople(Statement statement) throws SQLException {
        List<Person> people = new LinkedList();
        
        ResultSet resultSet = statement.executeQuery("SELECT * FROM person");
        
        while(resultSet.next()) {
            Person person = new Person();
            person.setId(UUID.fromString(resultSet.getString("id")));
            person.setFirstName(resultSet.getString("first_name"));
            person.setLastName(resultSet.getString("last_name"));
            
            people.add(person);
        }
        
        return people;
    }
    
}
