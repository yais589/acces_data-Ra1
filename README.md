# Pràctica: Xifrat i desxifrat amb inversió de línia

## Objectiu
Aprendre a treballar amb fitxers de text utilitzant decoradors (BufferedReader, BufferedWriter), aplicar transformacions de text i xifrat simple (Cèsar).

## Requeriments

**1. Xifrat**
* Llegir el fitxer `entrada.txt` amb BufferedReader.
* Invertir cada línia (exemple: "Hola món" → "nóm aloH").
* Aplicar xifrat Cèsar: desplaçar cada caràcter N posicions en Unicode.
* Escriure el resultat a `xifrat.txt` amb BufferedWriter.

**2. Desxifrat**
* Llegir el fitxer `xifrat.txt`.
* Aplicar desplaçament invers de la clau.
* Tornar a invertir cada línia per recuperar el missatge original.
* Escriure el resultat a `desxifrat.txt`.

**3. General**
* Mostrar per consola missatges de progrés i errors.
* *Opcional:* Permetre que l'usuari introdueixi la clau per consola.
