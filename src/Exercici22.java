import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Exercici22 {
    public static void main(String[] args) {
        // Programa que demana un nom de fitxer i mostra quantes lletres 'a' conté

        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Digues el fitxer que vols contar les 'a': ");
            String fitxer = sc.nextLine();

            int numA = 0;

            try (FileInputStream fis = new FileInputStream(fitxer)) {
                int b;
                while ((b = fis.read()) != -1) {
                    if ((char) b == 'a') {
                        numA++;
                    }
                }
            }

            System.out.println("El fitxer " + fitxer + " té " + numA + " 'a'");
        } catch (IOException e) {
            System.out.println("Error en llegir el fitxer: " + e.getMessage());
        }
    }
}
