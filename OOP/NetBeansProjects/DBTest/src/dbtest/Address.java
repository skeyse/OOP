/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbtest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Sean-
 */
public class Address {
    private UUID id;
    private UUID userId;
    
    private String streetAddress;
    private String zipCode;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID user_id) {
        this.userId = user_id;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    
    public void save(Statement statement) throws SQLException {
        
        if(id == null || streetAddress == null || zipCode == null 
                      || streetAddress.equals("") || zipCode.equals("")) {
            throw new RuntimeException("Invalid (ID, User_ID, Address, or Zip Code)");
        }
        
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO address (id, user_id, street, zip_code) VALUES (")
                .append("'").append(id).append("','")
                .append(userId).append("','")
                .append(streetAddress).append("','")
                .append(zipCode).append("');");
        
        statement.executeUpdate(stringBuilder.toString());
    }
    
    public static void delete(UUID id, Statement statement) throws SQLException {
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append("DELETE FROM address WHERE user_id='")
                .append(id.toString()).append("';");
        
        
        statement.execute(stringBuilder.toString());
        System.out.println("Deleted Address");
    }
    
    public static List<Address> getAllAddresses(Statement statement) throws SQLException {
        List<Address> addresses = new LinkedList();
        
        ResultSet resultSet = statement.executeQuery("SELECT * FROM address");
        
        while(resultSet.next()) {
            Address address = new Address();
            address.setId(UUID.fromString(resultSet.getString("id")));
            address.setUserId(UUID.fromString(resultSet.getString("user_id")));
            address.setStreetAddress(resultSet.getString("street"));
            address.setZipCode(resultSet.getString("zip_code"));
            
            addresses.add(address);
        }
        
        return addresses;
    }
}
