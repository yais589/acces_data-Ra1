import java.io.*;
import java.util.Scanner;

public class Pt2_accesData {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Introdueix la clau de xifrat/desxifrat: ");
        int clau = 0;
        try {
            clau = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Clau invàlida. S'utilitzarà la clau per defecte: 3.");
            clau = 3;
        }

        String fitxerEntrada = "RA 1/PT2/entrada.txt";
        String fitxerXifrat = "RA 1/PT2/xifrat.txt";
        String fitxerDesxifrat = "RA 1/PT2/desxifrat.txt";

        System.out.println("\n--- Iniciant processament ---");
        xifrarFitxer(fitxerEntrada, fitxerXifrat, clau);
        desxifrarFitxer(fitxerXifrat, fitxerDesxifrat, clau);
        
        scanner.close();
    } 

    public static void xifrarFitxer(String entrada, String sortida, int clau) {
        try (
            BufferedReader br = new BufferedReader(new FileReader(entrada));
            BufferedWriter bw = new BufferedWriter(new FileWriter(sortida))
        ) {
            String linia;
            while ((linia = br.readLine()) != null) {
                String liniaInvertida = new StringBuilder(linia).reverse().toString();

                StringBuilder liniaXifrada = new StringBuilder();
                for (char c : liniaInvertida.toCharArray()) {
                    liniaXifrada.append((char) (c + clau));
                }

                bw.write(liniaXifrada.toString());
                bw.newLine();
            }
            System.out.println("OK: Fitxer xifrat creat correctament a '" + sortida + "'.");
            
        } catch (FileNotFoundException e) {
            System.err.println("Error: No s'ha trobat el fitxer d'entrada '" + entrada + "'. Crea'l abans d'executar.");
        } catch (IOException e) {
            System.err.println("Error E/S durant el xifrat: " + e.getMessage());
        }
    }

    public static void desxifrarFitxer(String entrada, String sortida, int clau) {
        try (
            BufferedReader br = new BufferedReader(new FileReader(entrada));
            BufferedWriter bw = new BufferedWriter(new FileWriter(sortida))
        ) {
            String linia;
            while ((linia = br.readLine()) != null) {
                StringBuilder liniaDesxifrada = new StringBuilder();
                for (char c : linia.toCharArray()) {
                    liniaDesxifrada.append((char) (c - clau));
                }

                String textOriginal = liniaDesxifrada.reverse().toString();

                bw.write(textOriginal);
                bw.newLine();
            }
            System.out.println("OK: Fitxer desxifrat creat correctament a '" + sortida + "'.");
            
        } catch (FileNotFoundException e) {
            System.err.println("Error: No s'ha trobat el fitxer per desxifrar '" + entrada + "'.");
        } catch (IOException e) {
            System.err.println("Error E/S durant el desxifrat: " + e.getMessage());
        }
    }
}
    