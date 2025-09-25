import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Exercici21 {

    public static void main(String[] args) {
        String file = "usertext.txt";

        try (Scanner sc = new Scanner(System.in);
                FileOutputStream fos = new FileOutputStream(file)) {

            while (true) {
                System.out.print("Entra un text: ");
                String line = sc.nextLine();
                if (line.equalsIgnoreCase("quit")) {
                    break;
                }
                fos.write(line.getBytes());
                fos.write(System.lineSeparator().getBytes());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileInputStream fis = new FileInputStream(file)) {
            int b;
            while ((b = fis.read()) != -1) {
                System.out.print((char) b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
