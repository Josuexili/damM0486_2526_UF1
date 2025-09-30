import java.io.*;
import java.util.Scanner;

public class Exercici41Login {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Usuari: ");
        String nom = sc.nextLine();

        System.out.print("Password: ");
        String pass = sc.nextLine();

        File file = new File(nom + ".usr");

        if (file.exists()) {
            System.out.println("Fitxer trobat. Llegint usuari...");

            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                UserLogin u = (UserLogin) ois.readObject();
                ois.close();

                if (u.password.equals(pass)) {
                    System.out.println("Accés correcte al sistema");
                } else {
                    System.out.println("Contrasenya incorrecta");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Usuari no trobat, vols registrar-te? (s/n): ");
            String opcio = sc.nextLine();

            if (opcio.equalsIgnoreCase("s")) {
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                    UserLogin nou = new UserLogin(nom, pass);
                    oos.writeObject(nou);
                    oos.close();
                    System.out.println("Usuari creat: " + nom);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Registre cancel·lat.");
            }
        }

        sc.close();
    }
}
