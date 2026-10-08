# Problemes de la serialització i l'ús d'ObjectOutputStream

Utilitzar fitxers binaris a Java per guardar objectes té alguns inconvenients importants:

* **El problema de l'append (afegir dades):** Si intentes obrir un fitxer binari que ja existeix per afegir-hi un objecte al final, Java escriu una capçalera nova al mig del fitxer. Quan el vulguis llegir, el programa fallarà amb l'error `StreamCorruptedException`. Per evitar-ho, normalment toca carregar tota la llista a memòria i sobreescriure el fitxer sencer cada cop.

* **Incompatibilitat si canvies el codi:** Si guardes objectes al fitxer i l'endemà decideixes afegir un atribut nou a la classe `Videojoc`, no podràs llegir el fitxer antic. Java detecta que l'estructura de la classe ha canviat i llança una excepció (`InvalidClassException`).

* **Són opacs per als humans:** A diferència d'un arxiu de text normal, no pots obrir un fitxer binari `.dat` amb el bloc de notes per comprovar si les dades s'han guardat bé. Només hi veuràs símbols incomprensibles.

* **Problemes de seguretat:** Reconstruir objectes a partir d'un fitxer (deserialitzar) és arriscat si no controles d'on ve aquell fitxer, ja que es poden arribar a instanciar classes malicioses al teu programa.