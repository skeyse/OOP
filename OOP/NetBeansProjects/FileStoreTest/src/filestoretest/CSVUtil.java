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
public class CSVUtil {
        
    
    public static void createFile(File file, Person person) throws IOException {
        
        FileUtils.writeStringToFile(file, person.toString());
        
    }
    
    public static void save(File file, CSV csv) throws IOException {
        
        FileUtils.writeStringToFile(file, csv.toCSV() + "\n" , "UTF-8", true);
    }
}
