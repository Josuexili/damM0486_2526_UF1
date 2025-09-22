
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class FileInOutputStream {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            String file = "usertext.txt";
            try {
                FileOutputStream fos = new FileOutputStream(file);
                while (true) {
                    System.out.print("Entra un text: ");
                    String line = sc.nextLine();
                    if (line.equals("quit")) {
                        
                        break;
                    }

                    fos.write(line.getBytes());
                    fos.write("\n".getBytes());
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                try (FileInputStream fis = new FileInputStream(file)) {
                    int b = 0;
                    while (b != -1) {
                        b = fis.read();
                        if (b == -1) {
                            break;
                        
                        }System.out.print((char) b);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
