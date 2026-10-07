import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GestioVideojocsApp {
    private static final String FITXER = "videojocs.dat";
    private static ArrayList<Videojoc> cataleg = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        cataleg = carregarVideojocs();
        int opcio = 0;

        do {
            mostrarMenu();
            try {
                opcio = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                opcio = -1;
            }

            switch (opcio) {
                case 1:
                    afegirVideojoc();
                    break;
                case 2:
                    llistarVideojocs();
                    break;
                case 3:
                    cercarVideojoc();
                    break;
                case 4:
                    actualitzarVideojoc();
                    break;
                case 5:
                    eliminarVideojoc();
                    break;
                case 6:
                    desarVideojocs();
                    System.out.println("Canvis desats. Sortint del programa...");
                    break;
                default:
                    System.out.println("Opció incorrecta. Torna-ho a provar.");
            }
        } while (opcio != 6);

        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n=== GESTIÓ DE VIDEOJOCS ===");
        System.out.println("1. Afegir videojoc");
        System.out.println("2. Llistar tots els videojocs");
        System.out.println("3. Cercar videojocs per títol");
        System.out.println("4. Actualitzar un videojoc");
        System.out.println("5. Eliminar un videojoc");
        System.out.println("6. Sortir del programa");
        System.out.print("Tria una opció: ");
    }

    private static void afegirVideojoc() {
        System.out.print("Títol: ");
        String titol = sc.nextLine();
        System.out.print("Gènere (ex: acció, rol, esport): ");
        String genere = sc.nextLine();
        System.out.print("Any de llançament: ");
        int any = Integer.parseInt(sc.nextLine());
        System.out.print("Plataforma (ex: PC, PlayStation, Switch): ");
        String plataforma = sc.nextLine();
        System.out.print("Preu: ");
        double preu = Double.parseDouble(sc.nextLine());

        cataleg.add(new Videojoc(titol, genere, any, plataforma, preu));
        desarVideojocs();
        System.out.println("Videojoc afegit correctament.");
    }

    private static void llistarVideojocs() {
        if (cataleg.isEmpty()) {
            System.out.println("El catàleg està buit.");
            return;
        }
        System.out.println("\n--- Catàleg Complet ---");
        for (int i = 0; i < cataleg.size(); i++) {
            System.out.println("[" + i + "] " + cataleg.get(i));
        }
    }

    private static void cercarVideojoc() {
        System.out.print("Introdueix el text a cercar en el títol: ");
        String cerca = sc.nextLine().toLowerCase();
        boolean trobat = false;

        System.out.println("\n--- Resultats de la cerca ---");
        for (Videojoc v : cataleg) {
            if (v.getTitol().toLowerCase().contains(cerca)) {
                System.out.println(v);
                trobat = true;
            }
        }
        if (!trobat) {
            System.out.println("No s'ha trobat cap videojoc amb aquest text.");
        }
    }

    private static void actualitzarVideojoc() {
        llistarVideojocs();
        if (cataleg.isEmpty()) return;

        System.out.print("Introdueix el número (índex) del videojoc a actualitzar: ");
        try {
            int index = Integer.parseInt(sc.nextLine());
            if (index >= 0 && index < cataleg.size()) {
                Videojoc v = cataleg.get(index);
                System.out.println("Actualitzant: " + v.getTitol());
                
                System.out.print("Nou Títol (deixa en blanc per mantenir actual): ");
                String titol = sc.nextLine();
                if (!titol.isBlank()) v.setTitol(titol);

                System.out.print("Nou Gènere: ");
                String genere = sc.nextLine();
                if (!genere.isBlank()) v.setGenere(genere);

                System.out.print("Nou Any de llançament (0 per mantenir): ");
                int any = Integer.parseInt(sc.nextLine());
                if (any != 0) v.setAnyLlancament(any);

                System.out.print("Nova Plataforma: ");
                String plataforma = sc.nextLine();
                if (!plataforma.isBlank()) v.setPlataforma(plataforma);

                System.out.print("Nou Preu (-1 per mantenir): ");
                double preu = Double.parseDouble(sc.nextLine());
                if (preu != -1) v.setPreu(preu);

                desarVideojocs();
                System.out.println("Videojoc actualitzat correctament.");
            } else {
                System.out.println("Índex invàlid.");
            }
        } catch (NumberFormatException e) {
            System.out.println("S'esperava un número.");
        }
    }

    private static void eliminarVideojoc() {
        llistarVideojocs();
        if (cataleg.isEmpty()) return;

        System.out.print("Introdueix el número (índex) del videojoc a eliminar: ");
        try {
            int index = Integer.parseInt(sc.nextLine());
            if (index >= 0 && index < cataleg.size()) {
                Videojoc eliminat = cataleg.remove(index);
                desarVideojocs();
                System.out.println("S'ha eliminat: " + eliminat.getTitol());
            } else {
                System.out.println("Índex invàlid.");
            }
        } catch (NumberFormatException e) {
            System.out.println("S'esperava un número.");
        }
    }


    @SuppressWarnings("unchecked")
    private static ArrayList<Videojoc> carregarVideojocs() {
        File fitxer = new File(FITXER);
        if (fitxer.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fitxer))) {
                return (ArrayList<Videojoc>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error carregant el catàleg: " + e.getMessage());
            }
        }
        return new ArrayList<>();
    }

    private static void desarVideojocs() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FITXER))) {
            oos.writeObject(cataleg);
        } catch (IOException e) {
            System.out.println("Error desant el catàleg: " + e.getMessage());
        }
    }
}