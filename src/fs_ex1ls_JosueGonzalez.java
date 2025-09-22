
import java.io.File;

public class fs_ex1ls_JosueGonzalez {

    public static void main(String[] args) {

        String ruta = args[0];
        File path = new File(ruta);

        for (File file : path.listFiles()) {
            String permisos = (file.isDirectory() ? "d" : "-") + (file.canRead() ? "r" : "-") + (file.canWrite() ? "w" : "-") + (file.canExecute() ? "x" : "-");
            System.out.println(permisos + " " + file.getName());

        }

    }
}
