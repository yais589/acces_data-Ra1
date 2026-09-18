import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class PT1_accesData {
    public static void main(String[] args) throws IOException {
        try {

        FileReader fr= new FileReader("text.txt");


        int  c;
        int caracteres = 0;
        int linias = 0;
        int paraules = 0;
        int[] palabrasRepetidas = new int[65536];

        while((c =  fr.read()) != -1){
            System.out.print((char)c);
            caracteres++;

            if(c == '\n'){
                linias++;
            }
            if(c == ' '|| c == '\n' || c == '\t'|| c == '\r'){
                paraules++;
            }
            palabrasRepetidas[c]++;
        }
        
        fr.close();
        System.out.println("Número de caracteres: " + caracteres);
        System.out.println("Número de líneas: " + linias);
        System.out.println("Número de palabras: " + paraules);
        System.out.println("Número de veces que se repite cada carácter:");
        for(int i = 0; i < palabrasRepetidas.length; i++){
            if(palabrasRepetidas[i] > 0){
                System.out.println((char)i + ": " + palabrasRepetidas[i]);
            }
        }

                } catch (FileNotFoundException e) {

            System.out.println("El fitxer no existeix.");

        } catch (IOException e) {

            System.out.println("S'ha produït un error de lectura.");

        } catch (SecurityException e) {

            System.out.println("No tens permisos per accedir al fitxer.");
        }
    }
}
