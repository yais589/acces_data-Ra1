import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LlegirXmlZoo {
    public static void main(String[] args) {
        try {
            File file = new File("zoo.xml");
            if (!file.exists()) {
                System.out.println("El fitxer zoo.xml no existeix.");
                return;
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("animal");
            int totalAnimals = nList.getLength();

            System.out.println("--- LLISTAT D'ANIMALS ---");
            for (int i = 0; i < totalAnimals; i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    System.out.println("Animal #" + eElement.getAttribute("id"));
                    System.out.println("Nom: " + eElement.getElementsByTagName("nom").item(0).getTextContent());
                    System.out.println("Espècie: " + eElement.getElementsByTagName("especie").item(0).getTextContent());
                    System.out.println("Aliment: " + eElement.getElementsByTagName("aliment").item(0).getTextContent());
                    System.out.println("Edat: " + eElement.getElementsByTagName("edat").item(0).getTextContent());
                    System.out.println();
                }
            }

            System.out.println("Total d'animals: " + totalAnimals);
            System.out.println("Animals que mengen carn:");
            for (int i = 0; i < totalAnimals; i++) {
                Element eElement = (Element) nList.item(i);
                String aliment = eElement.getElementsByTagName("aliment").item(0).getTextContent();
                if (aliment.equalsIgnoreCase("Carn")) {
                    String nom = eElement.getElementsByTagName("nom").item(0).getTextContent();
                    System.out.println("- " + nom);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}