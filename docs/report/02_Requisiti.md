# Capitolo 2: Requisiti

## 2.1 Business

Il progetto ha l'obiettivo di rappresentare un videogioco, perciò il target di utenza è costituito da persone appassionate al mondo videoludico. Le aspettative di questo tipo di utenza sono tipicamente concentrate sull' usabilità e la coerenza delle dinamiche di gioco. Perciò gli obiettivi degli sviluppatori saranno orientati ad ottenere una solida e chiara dinamica di evoluzione e progressione del gioco e il progetto potrà essere giudicato terminato con esito positivo, quando presenterà le funzionalità identificate durante l'analisi dei requisiti, risulterà facilmente configurabile e le sue dinamiche saranno consistenti. 

## 2.2 Modello di dominio

Per quanto riguarda il dominio applicativo è stato preso come riferimento il noto gioco "Plague.Inc" a cui sono state applicate modifiche e riadattamenti. Di seguito si riporta una descrizione dettagliata dei requisiti del progetto.

### Gestione partita

Il gioco ha come obiettivo la diffusione di un virus con la conseguente infezione della popolazione mondiale. Il giocatore deve cercare di completare l'infezione prima che venga prodotto un vaccino.

### Gestione regioni

Il mondo di gioco è composto da varie regioni, ognuna presenta varie caratteristiche che rispecchiano la realtà e influenzano l'espansione del virus. Inoltre ogni regione può possedere o meno un aeroporto e/o un porto, che lo collega ad altri stati anche non direttamente confinanti.

### DNA Points

Durante una partita, all'interno delle regioni, vengono generati dei punti collezionabili chiamati "DNA Points". Una volta accumulati potranno essere utilizzati per potenziare il virus.

### Potenziamenti virus

Il virus possiede varie caratteristiche che ne determinano la velocità di diffusione, che può essere differente in base alle caratteristiche dello stato che sta cercando di infettare. Queste caratteristiche possono essere migliorate acquistando dei potenziamenti attraverso i "DNA Points".

### Diffusione virus

Il virus inizia l'infezione da uno stato scelto dall'utente. Inizialmente esso si diffonde internamente più o meno rapidamente a seconda delle caratteristiche del virus e dello stato infettato. Successivamente il virus può infettare altri stati, direttamente confinanti oppure tramite un porto o un aeroporto.

### Sviluppo del vaccino

Superata una determinata soglia di infezione, l'umanità inizia la ricerca di un vaccino. La velocità con cui viene sviluppata è influenzata dalle caratteristiche del virus. Come già anticipato, se il vaccino viene completato prima dell'infezione della popolazione mondiale la partita viene considerata persa.

## 2.3 Funzionali

### 2.3.1 Utente

- Ad inizio partita l'utente deve inserire il nome del virus e selezionare una regione da cui far partire l'infezione.

- L'utente visualizza una mappa del mondo in cui sono rappresentate le regioni e per ognuna è indicato se possiede un porto e/o un aeroporto.

- L'utente può visualizzare le rotte portuali e/o aeroportuali se abilitate.

- L'utente può raccogliere "DNA Points" che vengono generati casualmente nelle regioni infette della mappa.

- L'utente può selezionare una regione per visualizzarne le informazioni di dettaglio come: caratteristiche, numero di infetti e popolazione totale.

- L'utente visualizza l'elenco di tutti gli stati e per ognuno la progressione dell'infezione.

- Può essere visualizzato lo stato di avanzamento della ricerca del vaccino.
  
- Può essere visualizzata la progressione globale dell'infezione.

- L'utente ha la possibilità di aprire un menu per l'acquisto dei potenziamenti del virus tramite i "DNA Points" collezionati.

- L'utente può visualizzare quali potenziamenti sono acquistabili e quali no in base ai "DNA Points" collezionati e alle dipendenze tra potenziamenti.

- L'utente può visualizzare i dettagli relativi ad ogni singolo potenziamento (nome, descrizione, effetto)

- L'utente può visualizzare le carattteristiche del virus durante la partita

- L'utente può visualizzare l'avanzamento dei giorni durante l'evoluzione della partita.

- L'utente può visualizzare il numero di infetti globali rispetto alla popolazione mondiale.

### 2.3.1 Sistema

#### Gestione regioni

- Ogni regione ha una determinata popolazione e un certo numero di infetti che inizialmente è pari a zero.
- Ogni regione possiede una serie di caratteristiche:
  -  **Richness** [1-5]: indica il livello di ricchezza di una regione.
  -  **Climate** [1-3]: indica se il clima di una regione è freddo, mite o caldo.
  -  **Population density** [1-5]: indica il livello della densità di popolazione della regione.
  -  **Borders control** [1-5]: riporta il controllo sui confini di una determinata regione, se alto risulta più difficile che avvenga l'infezione da parte di un altra regione.
  -  **Globalization** [1-5]: riporta il livello di tendenza agli spostamenti della popolazione della regione, se alto è più probabile ne infetti altre.
 
-  Ogni caratteristica ha un valore compreso tra 1 e 5 (eccetto Climate che varia tra 1 e 3).
- Ogni stato può confinare con altri stati.
- Uno stato può possedere un porto e/o un aeroporto che permettono la trasmissione del virus a stati non direttamente confinanti.
- Ogni porto/aeroporto possiede delle tratte prestabilite che realizzano collegamenti con altri stati.

#### Comportamento virus

- Il virus comincia la sua espansione da uno stato scelto dall'utente.

- L'infettività del virus all'interno di uno stato dipende da una serie di caratteristiche proprie, da quelle dello stato che sta infettando e del numero di infetti attualmente raggiunto.

- Durante l'infezione interna ad uno stato, il virus incrementa il numero di infetti proporzionalmente alla sua infettività.

- Il virus a partire da uno stato già infettato, può infettare gli stati confinanti con una certa probabilità. Essa viene calcolata a partire da una serie di caratteristiche dello stato di partenza (globalizzazione e numero di infetti) e dal livello di controllo dei confini dello stato di arrivo.

- Il virus può inoltre infettare uno stato non direttamente confinante con la stessa dinamica descritta precedentemente, purchè esista una tratta che collega i due stati (via porto/aeroporto) e che sia stato sbloccato il potenziamento corrispondente.

- I calcoli sopra descritti vengono eseguiti periodicamente per mantenere aggiornato lo stato di gioco.

- Il virus possiede le seguenti caratteristiche: 
  - **Cold regions infectivity**: determina la velocità di infezione del virus nelle regioni con clima freddo.
  - **Hot regions infectivity**: determina la velocità di infezione del virus nelle regioni con clima caldo.
  - **Low density regions infectivity**: determina la velocità di infezione del virus nelle regioni con bassa densità di popolazione.
  - **High density regions infectivity**: determina la velocità di infezione del virus nelle regioni con alta densità di popolazione.
  - **Rich regions infectivity**: determina la velocità di infezione del virus nelle regioni ricche.
  - **Poor regions infectivity**: determina la velocità di infezione del virus nelle regioni povere.
  - **Vaccine resistance**: un alto valore di questa caratteristica determina un maggior rallentamento della ricerca del vaccino.
  - **Airport enabled**: se presente, permette al virus di infettare altre regioni tramite aeroporti.
  - **Port enabled**: se presente, permette al virus di infettare altre regioni tramite porti.

#### Gestione potenziamenti

- I potenziamenti acquistabili tramite "DNA Points" agiscono sulle caratteristiche del virus elencate precedentemente migliorandole.

- I potenziamenti sono organizzati in una gerarchia, alcuni sono acquistabili solamente dopo aver già acquisito quelli soprastanti.

- Di seguito si elencano i potenziamenti disponibili:
  - **Cold Resistance I**: incrementa la **Cold regions infectivity** del virus.
  - **Cold Resistance II**: incrementa la **Cold regions infectivity** del virus. Acquistabile solamente dopo **Cold Resistance I**
  - **Hot Resistance I**: incrementa la **Hot regions infectivity** del virus.
  - **Hot Resistance II**: incrementa la **Hot regions infectivity** del virus. Acquistabile solamente dopo **Hot Resistance I**
  - **Bacterial Resistance**: incrementa la **Cold regions infectivity** e la **Hot regions infectivity** del virus. Acquistabile solamente dopo **Cold Resistance II** e **Hot Resistance II**
  - **Port Enabled**: permette al virus di contagiare altri stati utilizzando i porti.
  - **Airport Enabled**: permette al virus di contagiare altri stati utilizzando gli aeroporti.
  - **Medicines Resistance**: incrementa la **Rich regions infectivity** del virus.
  - **Infection through respiratory tract**: incrementa la **High density regions infectivity** del virus.
  - **Infected Drinking Water**: incrementa la **Poor regions infectivity** del virus.
  - **Infection throungh animals**: incrementa la **Low density regions infectivity** del virus. Acquistabile solamente dopo **Infected Drinking Water**
  - **Alpha Mutations**: incrementa la **Vaccine Resistance** del virus.
  - **Beta Mutations**: incrementa la **Vaccine Resistance** del virus. Acquistabile solamente dopo **Alpha Mutations**.
  - **Gamma Mutations**: incrementa la **Vaccine Resistance** del virus. Acquistabile solamente dopo **Beta Mutations**.
  - **Omega Mutations**: incrementa la **Vaccine Resistance** del virus. Acquistabile solamente dopo **Gamma Mutations**.


#### Sviluppo del vaccino

- Lo sviluppo del vaccino ha inizio quando viene raggiunta una determinata soglia di persone infette, rispetto alla popolazione globale.

- La ricerca del vaccino procede in modo costante ma può essere rallentata acquistando potenziamenti del virus.

- Se la ricerca del vaccino termina prima che l'intera popolazione sia infettata, la partita viene considerata persa.

#### Configurazione di gioco

- Nel sistema sono presenti diverse caratteristiche che necessitano di una configurazione iniziale:
  
  - Caratteristiche delle regioni
  - Parametri iniziali del virus
  - Rotte portuali e aeroportuali disponibili
    
- Esse devono poter essere configurabili e modificabili in maniera agevole e consistente, mantenendo quindi separati gli aspetti implementativi da quelli di configurazione.

## 2.4 Non funzionali

- L'avanzamento della simulazione deve essere regolare.

- L'infezione interna ad uno stato deve essere coerente con le caratteristiche dello stato e del virus.

- L'infezione di stati confinanti deve essere coerente con le caratteristiche dello stato e del virus.

- I parametri di gioco (virus, stati ecc.) devono essere correttamente bilanciati.

- L'esecuzione del gioco deve risultare fluida e senza situazioni di blocco.

## 2.5 Di implementazione

- A fronte dei requisiti precedentemente analizzati si rende necessaria l'implementazione di un motore di gioco che si occupa della progressione ed evoluzione dello stesso.

- Si richiede inoltre la realizzazione di un'entità che si occupa del caricamento e gestione delle configurazioni iniziali.
  
- Infine sarà necessaria una struttura che modelli il mondo di gioco.
