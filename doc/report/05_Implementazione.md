# Capitolo 5: Implementazione

## Sviluppo collaborativo

Nelle fasi iniziali il team si è concentrato sulla progettazione e implementazione delle basi del applicativo con un approccio collaborativo, procedendo alla suddivisione dei vari componenti solo successivamente. In particolare la struttura dell'architettura generale, il `GameEngine` e la parte grafica della mappa di gioco sono state progetttate e implementate in collaborazione dall'intero team di sviluppo.

## Alberto Di Girolamo

Il mio contributo alla realizzazione del progetto si è concentrato principalmente sulla creazione del virus e sulla sua logica di infezione, sia all'interno di una regione sia per quanto riguarda l'espansione del virus su regioni sane tramite la logica esterna. Inoltre, ho gestito e sviluppato i PowerUp, occupandomi di tutti gli aspetti relativi alla loro struttura interna e alla logica di elaborazione.

Per quanto riguarda la parte grafica, ho progettato e implementato la griglia che visualizza i PowerUp all'interno del menu, oltre a realizzare il Launcher di gioco.

Per ogni componente descritto in seguito, ho impiegato meccanismi di scala avanzati per garantire un design efficiente e scalabile.

- `Virus`: per quanto riguarda il `Virus`, ho scelto di utilizzare gli **Aliases**, che consentono di semplificare eventuali modifiche ai tipi di dati utilizzati per rappresentare il livello di infettività del virus su regioni con specifiche caratteristiche. Attraverso gli **Aliases**, ho definito un tipo denominato `VirusConfiguration`, il quale rappresenta una tupla contenente tutti i parametri di configurazione del virus, semplificando notevolmente la creazione di nuove istanze del virus con configurazioni diverse.

- `InfectionHandler`: per quanto riguarda l'InfectionHandler, ho adottato il meccanismo delle **Given Instances**. In particolare, ho incluso una variabile immutabile di tipo `InfectionLogic` che, attraverso la keyword "**given**", permette di stabilire la logica di infezione predefinita nel caso in cui non ne venga specificata una diversa. Questa scelta è stata fatta con l'obiettivo di agevolare una possibile espansione del gioco, in modo da poter aggiungere ulteriori logiche di infezioni in futuro senza dover apportare modifiche complesse alla struttura esistente.

L'utilizzo dei meccanismi di scala avanzati per la progettazione del `virus` e dell'`InfectionHandler` ha permesso di ottenere un codice modulare, flessibile e mantenibile. Questo approccio ha contribuito a garantire maggiore robustezza al progetto, fornendo una solida base per l’eventuale aggiunta di nuove funzionalità e miglioramenti al gioco nel tempo.

Durante la fase iniziale di analisi dei requisiti del progetto, ho assunto il ruolo di Product Owner. Poiché tutti i membri del gruppo erano sviluppatori, l'importanza di questo ruolo è stata relativamente ridimensionata. Tuttavia, ho collaborato con lo Scrum Master e ho preso decisioni sulle priorità di sviluppo del progetto e sulle scelte necessarie per rispettare le scadenze settimanali e la deadline finale. Inoltre, ho coordinato parzialmente il lavoro del gruppo in qualità di Product Owner.

## Nicolò Malucelli
(Configurazione, DNAPoint, PowerUpManager, Pannello dettaglio regione, Esperto di dominio)

## Filippo Venturini

Il mio contributo nella realizzazione del progetto ha riguardato principalmente la gestione del mondo di gioco, mi sono occupato quindi della progettazione e dell'implementazione di tutto ciò che riguarda le regioni, le rotte e il mondo. Mi sono inoltre occupato dello sviluppo del vaccino ed ho contribuito alla realizzazione del `PowerUpManager`. Infine per quanto riguarda la parte grafica sono responsabile della realizzazione del pannello di infezione generale e dei pannelli del menu riguardanti le caratteristiche del Virus e i dettagli dei PowerUp.

Di seguito si procede a descrivere per ogni componente i meccanismi di Scala avanzati utilizzati, non già approfonditi nel capitolo relativo al design di dettaglio.

- `Region`: come già anticipato nei capitoli precedenti la gerarchia delle regioni è stata implementata sfruttando i **Mixin** che la rendono estremamente versatile ed estendibile e risultano utili per eventuali espansioni del gioco in cui sono previsti nuovi metodi di collegamento tra le regioni, come ad esempio le linee ferroviarie. Inoltre per quanto riguarda i parametri delle regioni sono stati largamente utilizzati gli **Aliases**, questo per facilitare eventuali modifiche ai tipi di dato scelti per rappresentare parametri come "Richness", "Climate" ecc., non vincolando i concetti alle scelte implementative. Inoltre sempre tramite gli **Aliases** è stato definito il tipo `RegionConfigurations` che corrisponde ad una tupla contenente tutti i parametri di configurazione della regione, questa strategia facilita e alleggerisce la creazione della stessa.

- `World`: in questa entità è stato utilizzato il meccanismo delle **Given Instances**, in particolare nel parametro del metodo per ottenere le regioni si fa uso della keyword **using** e tra i filtri forniti è presente un filtro di default fornito con la keyword **given**. Questo meccanismo ha lo scopo di permettere l'invocazione del metodo senza passare alcun filtro come parametro, in modo da ottenere la lista completa di tutte le regioni (tramite il filtro di default).
Anche in questa parte è stato utilizzato il meccanismo degli **Aliases** per definire un alias `RegionFilter` corrispondente al tipo `Region`=>`Boolean` e renderne più intuitivo e comodo l'utilizzo.

- `PowerUpManager`: questa entità è stata implementata in collaborazione con Nicolò Malucelli. Al suo interno è stato utilizzato il meccanismo del **for yield** per creare correttamente la lista dei PowerUp presenti all'interno del gioco.

- `PowerUpDetailsPanel`: all'interno di questo pannello è stato utilizzato un `GridBagLayout` per disporre tutti i componenti. Questo layout fa uso di un oggetto di tipo `GridBagConstraint` per specificare i vincoli di ogni componente. Per facilitarne la configurazione e renderla più leggibile, è stata utilizzata la tecnica **Extension Methods** di Scala per aggiungere un nuovo metodo di configurazione alla classe `GridBagConstraint`.
