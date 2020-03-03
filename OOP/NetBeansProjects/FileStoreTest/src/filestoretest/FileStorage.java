package filestoretest;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public class FileStorage {
    public static void save(File file, String data) throws IOException {
        FileUtils.writeStringToFile(file, data, "UTF-8");
    }
    
    public static String load(File file) throws IOException {
        return FileUtils.readFileToString(file, "UTF-8");
    }
}
