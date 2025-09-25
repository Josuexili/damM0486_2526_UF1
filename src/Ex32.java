import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Ex32 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Entra el num del codi: ");
        int codiBuscat = sc.nextInt();

        boolean trobat = false;

        try (DataInputStream dis = new DataInputStream(new FileInputStream("data.bin"))) {

            for (int i = 0; i < 10; i++) {
                int codi = dis.readInt();
                char c1 = dis.readChar();
                char c2 = dis.readChar();
                char c3 = dis.readChar();

                if (codi == codiBuscat) {
                    System.out.println("Secret per al codi " + codi + ": " + c1 + c2 + c3);
                    trobat = true;
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

        if (!trobat) {
            System.out.println("No s'ha trobat el codi " + codiBuscat);
        }
        sc.close();
    }

}
