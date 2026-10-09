# Pràctica: El zoo virtual

Petita aplicació en Java per gestionar informació d'un zoo mitjançant fitxers XML fent servir l'API DOM.

## Estructura
- `CrearXmlZoo.java`: Genera el fitxer `zoo.xml` amb l'estructura bàsica dels animals.
- `LlegirXmlZoo.java`: Llegeix el fitxer XML, mostra per pantalla els registres amb format net i inclou el recompte total i el filtre dels que mengen carn.

## Com executar-ho
1. Compila i executa primer la classe encarregada de generar el fitxer:
   ```bash
   javac CrearXmlZoo.java
   java CrearXmlZoo