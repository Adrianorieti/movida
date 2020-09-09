#####Inizializzazione#####
1. Creare un instanza della classe `movidaCore`.
2. Richiamare facoltativamente `setMap()` e `setSort()`.
3. Richiamare `loadFromFile(File f)`.

-----------------

#####Note#####
* SetMap supporta `ArrayOrdinato` e `HashIndirizzamentoAperto`
* SetSort supporta `Quicksort` e `InsertionSort`


Le scelte fatte sono memorizzate attraverso la classe `Properties` e salvate automaticamente al termine del programma. Ad un successivo
avvio il programma viene inizializzato con le ultime impostazioni.
Al primo avvio sono presenti impostazioni di default.

`setMap()` non comporta alcun cambiamento all'eventuale istanza del dizionario già presente.
Il cambiamento viene effettuato una volta chiamata la funzione `loadFromFile(File f)`.
E' possibile salvare i dati nel corso dell'utilizzo del programma con la funzione `saveToFile()` e riutilizzarli in un secondo momento.

Con `loadFromFile(File f)` viene anche "popolato" il grafo.

-----------------------
#####Note sul grafo:#####
Il grafo è implementato modificando le classi `movida.commons.Person` e `movida.commons.Collaboration`.
La classe `Person` funge da nodo del grafo. Alla classe è stata aggiunto il membro `collabs`: una lista di collaborazioni che coinvolgono l'attore.
La classe `Collaboration` funge da arco creando un collegamento tra i membri `actorA` e `actorB`.

La funzione `deleteMovieByTitle(String s)` aggiorna anche il grafo.












	