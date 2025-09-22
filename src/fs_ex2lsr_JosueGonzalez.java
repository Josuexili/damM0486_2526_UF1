
import java.io.File;
import java.util.*;

public class fs_ex2lsr_JosueGonzalez {

    public static void main(String[] args) {

        String ruta = args[0];
        File arrel = new File(ruta);

        Stack<File> pendents = new Stack<>();
        pendents.push(arrel);

        while (!pendents.isEmpty()) {
            File actual = pendents.pop();
            File[] elements = actual.listFiles();

            System.out.println("\ndirectori: " + actual.getName());

            for (File file : elements) {
                String perms = (file.isDirectory() ? "d" : "-") + (file.canRead() ? "r" : "-") + (file.canWrite() ? "w" : "-") + (file.canExecute() ? "x" : "-");
                System.out.println(perms + " " + file.getName());

                if (file.isDirectory()) {
                    pendents.push(file);
                }
            }
        }
    }
}
