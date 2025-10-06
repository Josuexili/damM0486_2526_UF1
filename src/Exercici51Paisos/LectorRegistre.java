package Exercici51Paisos;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class LectorRegistre {
    private static String readChars(RandomAccessFile fitxer, int nChars) throws IOException {
        StringBuilder b = new StringBuilder();
        char ch = ' ';
        for (int i = 0; i < nChars; i++) {
            ch = fitxer.readChar();
            if (ch != '\0')
                b.append(ch);
        }
        return b.toString();
    }

    public static void main(String[] args) {
        String nom;
        int id, poblacio;
        long pos = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introdueix número de registre: ");
        id = scanner.nextInt();
        scanner.nextLine();
        try (RandomAccessFile fitxer = new RandomAccessFile("paisos.dat", "rw");) {
            // per accedir a un registre multipliquem per la mida de
            // cada registre.
            pos = (id - 1) * 174;

            if (pos < 0 || pos >= fitxer.length())
                throw new IOException("Número de registre invàlid.");

            fitxer.seek(pos);
            fitxer.readInt(); // Saltem l'id
            nom = readChars(fitxer, 40);
            readChars(fitxer, 43); // Ens saltem el codi ISO i la capital
            poblacio = fitxer.readInt();
            System.out.println("País: " + nom + ", població actual: " + poblacio);
            System.out.println("Introdueix la nova població: ");
            poblacio = scanner.nextInt();
            scanner.nextLine();
            if (poblacio >= 0) {
                pos = fitxer.getFilePointer() - 4; // tornem enrere per sobreescriure la població
                fitxer.seek(pos);
                fitxer.writeInt(poblacio);
            } else {
                System.err.println("La població ha de ser positiva.");
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        scanner.close();
    }
}
