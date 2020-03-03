package dbtest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;
import org.flywaydb.core.Flyway;

public class DBTest {
    
    private static final String DB_NAME = "jdbc:sqlite:seankeyse.db";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        
        Flyway flyway = new Flyway();
        
        flyway.setDataSource(DB_NAME, "", "");
        flyway.setBaselineOnMigrate(true);
        flyway.setLocations("dbtest.db.migration");
        flyway.migrate();
        
        Person person = new Person();
        person.setId(UUID.randomUUID());
        person.setFirstName("Sean");
        person.setLastName("Keyse");
        System.out.println(person.getId().toString());
        
        Address address = new Address();
        address.setId(UUID.randomUUID());
        address.setUserId(person.getId());
        address.setStreetAddress("5415 Cloisters Drive");
        address.setZipCode("44406");
        
        System.out.println(address.getUserId().toString());
        
        Connection connection = DriverManager.getConnection(DB_NAME);
        Statement statement = connection.createStatement();
        //statement.executeUpdate(person.getInsertSql());
        //System.out.println("Done");
        
        person.save(statement);
        address.save(statement);
        
        
        List<Person> people = Person.getAllPeople(statement);
        
        System.out.print(people.toString());
        
        
        
        
        ResultSet resultSet = statement.executeQuery(
                "SELECT * FROM person "
                        +"INNER JOIN address ON address.user_id = person.id"
        );
        
        /*while(resultSet.next()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ID: ").append(resultSet.getString("id"))
                    .append("\nFirst Name: ").append(resultSet.getString("first_name"))
                    .append("\nLast Name: ").append(resultSet.getString("last_name"));
            System.out.println(stringBuilder.toString());
        }
        */
    }
    
    public static String getDBName() {
        return DB_NAME;
    }
}
