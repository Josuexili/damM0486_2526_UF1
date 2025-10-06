package Exercici51Paisos;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class ModificarPaisos {

    private static final int MIDA_REGISTRE = 174;
    private static final int OFF_ID = 0;
    private static final int OFF_NOM = OFF_ID + 4;
    private static final int OFF_CODI = OFF_NOM + 80;
    private static final int OFF_CAPITAL = OFF_CODI + 6;
    private static final int OFF_POBLACIO = OFF_CAPITAL + 80;
    private static final int LEN_NOM = 40;
    private static final int LEN_CODI = 3;
    private static final int LEN_CAPITAL = 40;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introdueix número de registre : ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Quina dada vols modificar? (nom/codi/poblacio/capital): ");
        String camp = sc.nextLine().trim().toLowerCase();

        System.out.print("Introdueix el nou valor: ");
        String nouValor = sc.nextLine();

        try (RandomAccessFile fitxer = new RandomAccessFile("paisos.dat", "rw")) {

            long posRegistre = (long) (id - 1) * MIDA_REGISTRE;

            if (posRegistre < 0 || posRegistre >= fitxer.length()) {
                throw new IOException("Número de registre invàlid.");
            }

            fitxer.seek(posRegistre + OFF_ID);
            fitxer.readInt();

            String nomActual = readChars(fitxer, LEN_NOM);
            String codiActual = readChars(fitxer, LEN_CODI);
            String capitalActual = readChars(fitxer, LEN_CAPITAL);
            int poblacioActual = fitxer.readInt();

            System.out.println("Abans  → " + formatPais(nomActual, codiActual, capitalActual, poblacioActual));

            switch (camp) {
                case "nom":
                    fitxer.seek(posRegistre + OFF_NOM);
                    fitxer.writeChars(fixarLongitud(nouValor, LEN_NOM));
                    break;

                case "codi":
                    fitxer.seek(posRegistre + OFF_CODI);
                    fitxer.writeChars(fixarLongitud(nouValor.toUpperCase(), LEN_CODI));
                    break;

                case "capital":
                    fitxer.seek(posRegistre + OFF_CAPITAL);
                    fitxer.writeChars(fixarLongitud(nouValor, LEN_CAPITAL));
                    break;

                case "poblacio":
                    int novaPoblacio;
                    try {
                        novaPoblacio = Integer.parseInt(nouValor);
                    } catch (NumberFormatException nfe) {
                        System.err.println("La població ha de ser un enter.");
                        sc.close();
                        return;
                    }
                    if (novaPoblacio < 0) {
                        System.err.println("La població ha de ser positiva.");
                        sc.close();
                        return;
                    }
                    fitxer.seek(posRegistre + OFF_POBLACIO);
                    fitxer.writeInt(novaPoblacio);
                    break;

                default:
                    System.err.println("Camp no vàlid. Tria: nom / codi / poblacio / capital");
                    sc.close();
                    return;
            }

            fitxer.seek(posRegistre + OFF_ID);
            fitxer.readInt();
            String nomDespres = readChars(fitxer, LEN_NOM);
            String codiDespres = readChars(fitxer, LEN_CODI);
            String capitalDespres = readChars(fitxer, LEN_CAPITAL);
            int poblacioDespres = fitxer.readInt();

            System.out.println("Després → " + formatPais(nomDespres, codiDespres, capitalDespres, poblacioDespres));
            System.out.println("Modificació realitzada correctament!");

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

        sc.close();
    }

    private static String readChars(RandomAccessFile fitxer, int nChars) throws IOException {
        StringBuilder b = new StringBuilder(nChars);
        for (int i = 0; i < nChars; i++) {
            char ch = fitxer.readChar();
            if (ch != '\0') {
                b.append(ch);
            }
        }
        return b.toString().trim();
    }

    private static String fixarLongitud(String text, int mida) {
        StringBuilder b = new StringBuilder(text);
        b.setLength(mida);
        return b.toString();
    }

    private static String formatPais(String nom, String codi, String capital, int poblacio) {
        return "País " + nom + " (" + codi + "), capital: " + capital + ", població: " + poblacio;
    }
}
