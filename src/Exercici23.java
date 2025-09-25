import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class Exercici23 {
    public static void main(String[] args) throws IOException {
        Random rd = new Random();

        String lletres = "abcdefghijklmnopqrstuvwxyz";
        int numAnterior = 0;

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("data.bin"))) {
            for (int i = 0; i < 10; i++) {
                int num = rd.nextInt(3) + 1;
                int numActual = numAnterior + num;
                numAnterior = numActual;

                StringBuilder secret = new StringBuilder();
                for (int j = 0; j < 3; j++) {
                    secret.append(lletres.charAt(rd.nextInt(lletres.length())));
                }

                dos.writeInt(numActual);
                dos.writeChars(secret.toString());
            }
        }

        try (DataInputStream dis = new DataInputStream(new FileInputStream("data.bin"))) {
            for (int i = 0; i < 10; i++) {
                int n = dis.readInt();
                char c1 = dis.readChar();
                char c2 = dis.readChar();
                char c3 = dis.readChar();

                System.out.println(n + " : " + c1 + c2 + c3);
            }
        }
    }
}
