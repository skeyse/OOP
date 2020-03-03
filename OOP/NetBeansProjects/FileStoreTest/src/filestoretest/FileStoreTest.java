/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filestoretest;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Sean-
 */
public class FileStoreTest {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws IOException {
        File file = new File("./test.csv");
        List<Person> people = new LinkedList();
        people.add(new Person("Sean", "Keyse", 24));
        people.add(new Person("John", "Smith", 14));
        people.add(new Person("Jane", "Doe", 54));
        
        
        for (Person i:people) {
            CSVUtil.save(file, i);
        }
    }
    
}
