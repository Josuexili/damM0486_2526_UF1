
import java.io.IOException;
import java.nio.file.*;

public class fs_ex3cp_JosueGonzalez {

    public static void main(String[] args) throws IOException {

        FileSystem fileSys = FileSystems.getDefault();
        Path fileName = fileSys.getPath(args[0]);
        Path dirName = fileSys.getPath(args[1]);

        Path destFile = Paths.get(dirName.toString() + "/" + fileName.getFileName());

        Files.copy(fileName, destFile);
        
        
    }
    
}
