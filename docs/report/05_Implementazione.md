# Capitolo 5: Implementazione

## 5.1 Sviluppo collaborativo

Nelle fasi iniziali il team si è concentrato sulla progettazione e implementazione delle basi dell'applicativo con un approccio collaborativo, procedendo alla suddivisione dei vari componenti solo successivamente. In particolare la struttura dell'architettura generale, il `GameEngine` e la parte grafica della mappa di gioco sono state progettate e implementate in collaborazione dall'intero team di sviluppo.

## 5.2 Sviluppo individuale

### Alberto Di Girolamo

Il mio contributo alla realizzazione del progetto si è concentrato principalmente sulla creazione del virus e sulla sua logica di infezione, sia all'interno di una regione sia per quanto riguarda l'espansione del virus su regioni sane tramite la logica esterna. Inoltre, ho gestito e sviluppato i PowerUp, occupandomi di tutti gli aspetti relativi alla loro struttura interna e alla logica di elaborazione.

Per quanto riguarda la parte grafica, ho progettato e implementato la griglia che visualizza i PowerUp all'interno del menu, oltre a realizzare il Launcher di gioco.

Di seguito verranno elencate le classi e i file su cui ho lavorato singolarmente, con riferimento ai package:

`infection`:
- `InfectionHandler`
- `InfectionLogic`
- `Virus`
  
`powerUp`:
- `PowerUp`
- `PowerUpInformation`
- `PowerUpLogic`
- `PowerUpType`
  
`view.menu`:
- `PowerUpGridPanel`
  
`view.launcher`:
- `LauncherView`
  
`controller`:
- `LauncherController`

Per ogni componente descritto in seguito, ho impiegato meccanismi di Scala avanzati per garantire un design efficiente e scalabile.

- `Virus`: per quanto riguarda il `Virus`, ho scelto di utilizzare gli **Aliases**, che consentono di semplificare eventuali modifiche ai tipi di dati utilizzati per rappresentare il livello di infettività del virus su regioni con specifiche caratteristiche. Attraverso gli **Aliases**, ho definito un tipo denominato `VirusConfiguration`, il quale rappresenta una tupla contenente tutti i parametri di configurazione del virus, semplificando notevolmente la creazione di nuove istanze del virus con configurazioni diverse.

- `InfectionHandler`: per quanto riguarda l'InfectionHandler, ho adottato il meccanismo delle **Given Instances**. In particolare, ho incluso una variabile immutabile di tipo `InfectionLogic` che, attraverso la keyword "**given**", permette di stabilire la logica di infezione predefinita nel caso in cui non ne venga specificata una diversa. Questa scelta è stata fatta con l'obiettivo di agevolare una possibile espansione del gioco, in modo da poter aggiungere ulteriori logiche di infezioni in futuro senza dover apportare modifiche complesse alla struttura esistente.

L'utilizzo dei meccanismi di scala avanzati per la progettazione del `virus` e dell'`InfectionHandler` ha permesso di ottenere un codice modulare, flessibile e mantenibile. Questo approccio ha contribuito a garantire maggiore robustezza al progetto, fornendo una solida base per l’eventuale aggiunta di nuove funzionalità e miglioramenti al gioco nel tempo.

Durante la fase iniziale di analisi dei requisiti del progetto, ho assunto il ruolo di Product Owner. Poiché tutti i membri del gruppo erano sviluppatori, l'importanza di questo ruolo è stata relativamente ridimensionata. Tuttavia, ho collaborato con l'Esperto di Dominio e ho preso decisioni sulle priorità di sviluppo del progetto e sulle scelte necessarie per rispettare le scadenze settimanali e la deadline finale. Inoltre, ho coordinato parzialmente il lavoro del gruppo in qualità di Product Owner.

### Nicolò Malucelli
Il mio contributo nella realizzazione del progetto ha riguardato inizialmente il caricamento dinamico delle configurazioni
di gioco (regioni, rotte e virus). 

Terminata questa parte, mi sono dedicato alla realizzazione dei DNA point e di tutto ciò 
che ne concerne: logica di spawn, collezionabilità e utilizzo. Nello specifico, durante quest'ultimo punto ho contribuito
alla realizzazione del PowerUpManager. Durante questa fase ho anche avuto modo di interagire anche con la parte grafica, 
rendendo i DNA point veri e propri pulsanti cliccabili sulla mappa di gioco. 

Infine, per quanto riguarda la parte grafica e di interazione con l'utente, ho contribuito alla realizzazione del pannello
di dettaglio delle regioni ed ho reso interagibile la mappa di gioco, permettendo così di riconoscere la regione cliccata.

Di seguito sono elencate le classi a cui ho lavorato con riferimento ai package:

- `model.configuration.builders.*`
- `model.configuration.Loader`
- `model.configuration.Parsers`
- `model.dnapoints`
- `model.powerUp.PowerUpManager`
- `view.game.WorldMapPanel`
- `view.game.RegionsView.RegionsPanel`
- `view.game.RegionsView.SingleRegionPanel`
- `utils.Iterables`

Di seguito sono elencati i meccanismi avanzati di Scala da me utilizzati:

- `ConfigurationsLoader`: per quanto riguarda l'implementazione del metodo *load* del ConfigurationLoader, ho fatto uso delle **Given Instances**. Nello specifico, ho permesso al compilatore di sapere quale Parser utilizzare a seconda del file di configurazione passato. Ad esempio, passando al metodo *load* un RegionFile, questo è convertito utilizzando un RegionParser. Ciò è stato possibile associando ciascun tipo di file di configurazione al proprio parser attraverso la keyword **given**, ed utilizzando la keyword **using** nel parametro *parser* del metodo *load*.

- `SpawnPointLogic`: come precedentemente mostrato, la logica di spawn dei DNA point è stata implementata facendo uso dei **Mixin**. Attraverso questo meccanismo è possibile definire con facilità nuove logiche di spawn senza dover stravolgere l'architettura già presente. 

- `Iterable`: Per facilitare la selezione di una regione randomica tra quelle infette, nella quale spawnare un DNA point, è stato realizzato attraverso il meccanismo degli **Extension Methods**, il metodo *getRandomElement*, che a partire da un iterabile restituisce un elemento casuale della collezione. Tale metodo è stato utilizzato anche per selezionare un pixel casuale di un determinato colore nel quale far comparire il DNA point sotto forma di pulsante.

- `BufferedImage`: il meccanismo degli **Extension Methods** è stato utilizzato anche per implementare il metodo *getHexCode* della classe BufferedImage. Questo metodo, è utilizzato per conoscere il valore esadecimale del pixel cliccato, permettendo così di riconoscere quale regione del mondo di gioco è stata selezionata.

In qualità di esperto del dominio, mi sono occupato di verificare la qualità del risultato dal punto di vista dell'utente finale, 
contribuendo ad un corretto bilanciamento del gioco.

### Filippo Venturini

Il mio contributo nella realizzazione del progetto ha riguardato principalmente la gestione del mondo di gioco, mi sono occupato quindi della progettazione e dell'implementazione di tutto ciò che riguarda le regioni, le rotte e il mondo. Mi sono inoltre occupato dello sviluppo del vaccino ed ho contribuito alla realizzazione del PowerUpManager. Infine per quanto riguarda la parte grafica sono responsabile della realizzazione del pannello di infezione generale e dei pannelli del menu riguardanti le caratteristiche del Virus e i dettagli dei PowerUp.

Di seguito verranno elencate le classi e i file su cui ho lavorato singolarmente, con riferimento ai package:

`world`:
- `RegionParameters`
- `Regions`
- `Routes`
- `World`

`vaccine`:
- `VaccineHandler`
- `VaccineResearchLogic`

`powerUp`:
- `PowerUpManager`

`view.menu`:
- `PowerUpDetailsPanel`
- `VirusPanel`

`view.game`:
- `GeneralInfectionPanel`

Ora si procede a descrivere i meccanismi di Scala avanzati utilizzati (con riferimento al componente in cui si trovano) non già approfonditi nel capitolo relativo al design di dettaglio:

- `Region`: come già anticipato nei capitoli precedenti la gerarchia delle regioni è stata implementata sfruttando i **Mixin** che la rendono estremamente versatile ed estendibile e risultano utili per eventuali espansioni del gioco in cui sono previsti nuovi metodi di collegamento tra le regioni, come ad esempio le linee ferroviarie. Inoltre per quanto riguarda i parametri delle regioni sono stati largamente utilizzati gli **Aliases**, questo per facilitare eventuali modifiche ai tipi di dato scelti per rappresentare parametri come "Richness", "Climate" ecc., non vincolando i concetti alle scelte implementative. Inoltre sempre tramite gli **Aliases** è stato definito il tipo `RegionConfigurations` che corrisponde ad una tupla contenente tutti i parametri di configurazione della regione, questa strategia facilita e alleggerisce la creazione della stessa.

- `World`: in questa entità è stato utilizzato il meccanismo delle **Given Instances**, in particolare nel parametro del metodo per ottenere le regioni si fa uso della keyword **using** e tra i filtri forniti è presente un filtro di default fornito con la keyword **given**. Questo meccanismo ha lo scopo di permettere l'invocazione del metodo senza passare alcun filtro come parametro, in modo da ottenere la lista completa di tutte le regioni (tramite il filtro di default).
Anche in questa parte è stato utilizzato il meccanismo degli **Aliases** per definire un alias `RegionFilter` corrispondente al tipo `Region`=>`Boolean` e renderne più intuitivo e comodo l'utilizzo.

- `PowerUpManager`: questa entità è stata implementata in collaborazione con Nicolò Malucelli. Al suo interno è stato utilizzato il meccanismo del **for yield** per creare correttamente la lista dei PowerUp presenti all'interno del gioco.

- `PowerUpDetailsPanel`: all'interno di questo pannello è stato utilizzato un `GridBagLayout` per disporre tutti i componenti. Questo layout fa uso di un oggetto di tipo `GridBagConstraint` per specificare i vincoli di ogni componente. Per facilitarne la configurazione e renderla più leggibile, è stata utilizzata la tecnica **Extension Methods** di Scala per aggiungere un nuovo metodo di configurazione alla classe `GridBagConstraint`.

## 5.3 Testing

Il testing è stato una fase fondamentale nello sviluppo di PlagueDotScala. In questo capitolo, descriveremo l'approccio utilizzato per il testing, compresi gli strumenti e le tecnologie adottate per garantire la qualità e la robustezza del nostro progetto.

### 5.3.1 Testing tramite TDD (Test-Driven Development)

Per assicurare che il nostro codice funzioni correttamente fin dalle prime fasi di sviluppo, abbiamo seguito l'approccio del Test-Driven Development (TDD). Questo metodo prevede di scrivere i test prima di sviluppare il codice effettivo. In altre parole, abbiamo definito i requisiti e le specifiche del nostro gioco in termini di test automatizzati, e solo successivamente abbiamo implementato le funzionalità per far sì che i test passassero. Questo processo iterativo ci ha aiutato a individuare bug e problemi fin dalla fase iniziale, permettendoci di mantenere un alto grado di affidabilità nel codice prodotto.


### 5.3.2 Utilizzo di GitHub Actions per il Continuous Integration
Per automatizzare il processo di testing e garantire che ogni modifica apportata al codice sia soggetta a una serie di test, abbiamo sfruttato GitHub Actions. Tramite questa piattaforma, abbiamo configurato una serie di workflow che vengono eseguiti automaticamente ogni volta che viene effettuata una push o viene aperta una pull request nel nostro repository GitHub. Questi workflow includono i test implementati con JUnit (vedi sezione successiva), e la loro esecuzione ci permette di individuare tempestivamente eventuali errori introdotti da nuove modifiche.


### 5.3.3 Utilizzo di JUnit per i test unitari
JUnit è stato lo strumento principale utilizzato per scrivere e gestire i test unitari nel nostro progetto. Abbiamo suddiviso le diverse funzionalità del gioco in unità separate e per ciascuna di esse abbiamo scritto una serie di test specifici. I test unitari coprono le seguenti sezioni del nostro gioco:

- Configurazione dell'ambiente di gioco: Abbiamo testato il corretto caricamento delle informazioni relative alle regioni, alle rotte e la configurazione iniziale del gioco per verificare che tutto funzioni secondo le aspettative.

- Utilizzo dei DNAPoints: Abbiamo implementato test per assicurarci che i DNAPoints vengano utilizzati correttamente nel gioco e che le operazioni legate a essi siano accurate e prive di errori.

- Logica di infezione: Abbiamo testato sia l'infezione interna ad una regione che l'infezione esterna attraverso le rotte, per garantire che il processo di diffusione del virus funzioni in modo corretto e realistico.

- Gestione del virus: Abbiamo verificato che la configurazione e la modifica delle specifiche del virus siano gestite adeguatamente e rispecchino le scelte del giocatore.

- PowerUp: Abbiamo testato la logica di acquisto, utilizzo e funzionamento dei PowerUp per garantire che conferiscano vantaggi al giocatore come previsto.

- Logica del vaccino: Abbiamo implementato test per verificare che la logica del vaccino funzioni correttamente, permettendo al giocatore di contrastare l’infezione.

- Gestione delle regioni, delle rotte e del mondo: Abbiamo testato la gestione delle regioni implementato test dettagliati per verificare che le regioni siano create e configurate correttamente. Per quanto riguarda le rotte sono state testate tutte le operazioni necessarie per il loro utilizzo, mentre per la parte del mondo abbiamo testato tutte le operazioni riguardanti la gestione delle regioni nel loro complesso: come l’ottenimento delle regioni non completamente infette, di quelle totalmente infette, ecc..

[Vai a Design di Dettaglio](./04_DesignDiDettaglio.md) | [Torna alla Home](../index.md) | [Vai a Retrospettiva](./06_Retrospettiva.md)
