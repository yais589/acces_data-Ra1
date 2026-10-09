import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CrearXmlZoo {
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element rootElement = doc.createElement("zoo");
            doc.appendChild(rootElement);

            String[][] dadesAnimals = {
                {"1", "Pingüí", "Aptenodytes forsteri", "Peix", "5"},
                {"2", "Lleó", "Panthera leo", "Carn", "8"}
            };

            for (String[] dades : dadesAnimals) {
                Element animal = doc.createElement("animal");
                animal.setAttribute("id", dades[0]);

                Element nom = doc.createElement("nom");
                nom.appendChild(doc.createTextNode(dades[1]));
                animal.appendChild(nom);

                Element especie = doc.createElement("especie");
                especie.appendChild(doc.createTextNode(dades[2]));
                animal.appendChild(especie);

                Element aliment = doc.createElement("aliment");
                aliment.appendChild(doc.createTextNode(dades[3]));
                animal.appendChild(aliment);

                Element edat = doc.createElement("edat");
                edat.appendChild(doc.createTextNode(dades[4]));
                animal.appendChild(edat);

                rootElement.appendChild(animal);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(doc);
            File file = new File("zoo.xml");
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);

            System.out.println("Fitxer creat correctament.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}