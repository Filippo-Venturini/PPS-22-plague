# Capitolo 2: Requisiti

## 2.1 Business

Il progetto ha l'obiettivo di rappresentare un videogioco, perciò il target di utenza è costituito da persone appassionate al mondo videoludico. Le aspettative di questo tipo di utenza sono tipicamente concentrate sull' usabilità e la coerenza delle dinamiche di gioco. Perciò gli obiettivi degli sviluppatori saranno orientati ad ottenere una solida e chiara dinamica di evoluzione e progressione del gioco e il progetto potrà essere giudicato terminato con esito positivo, quando presenterà le funzionalità identificate durante l'analisi dei requisiti e le sue dinamiche risulteranno consistenti. 

## 2.2 Modello di dominio

Per quanto riguarda il dominio applicativo è stato preso come riferimento il noto gioco "Plague.Inc" a cui sono state applicate modifiche e riadattamenti. Di seguito si riportano una descrizione dettagliata dei requisiti del progetto.

### Gestione partita

Il gioco ha come obiettivo la diffusione di un virus con la conseguente infezione della popolazione mondiale. Il giocatore deve cercare di completare l'infezione prima che venga prodotto un vaccino.

### Gestione stati

Ogni stato presenta varie caratteristiche che rispecchiano il mondo reale e influenzano l'espansione del virus. Inoltre ogni stato può possedere o meno un aeroporto e/o un porto, che lo collega ad altri stati anche non direttamente confinanti.

### Potenziamenti virus

Il virus possiede varie caratteristiche che ne determinano la velocità di diffusione, che può essere differente in base alle caratteristiche dello stato che sta cercando di infettare. Queste caratteristiche possono essere migliorate acquistando dei potenziamenti attraverso "Punti Evoluzione" collezionabili.

### Diffusione virus

Il virus inizia l'infezione da uno stato scelto dall'utente. Inizialmente esso si diffonde internamente più o meno rapidamente a seconda delle caratteristiche del virus e dello stato infettato. Successivamente il virus può infettare altri stati, direttamente confinanti oppure tramite un porto o un aeroporto.

### Sviluppo del vaccino

Superata una determinata soglia di infezione, l'umanità inizia la ricerca di un vaccino. La velocità con cui viene sviluppata è influenzata dalle caratteristiche del virus. Come già anticipato, se il vaccino viene completato prima dell'infezione della popolazione mondiale la partita viene considerata persa.

## 2.3 Funzionali

### 2.3.1 Utente

- Ad inizio partita l'utente deve inserire il nome del virus e selezionare uno stato da cui far partire l'infezione.

- L'utente visualizza una mappa del mondo in cui sono rappresentati gli stati e per ogni stato è indicato se possiede un porto e/o un aeroporto.

- L'utente può raccogliere "Punti Evoluzione" che vengono generati casualmente negli stati della mappa.

- L'utente può selezionare uno stato per visualizzarne le informazioni di dettaglio come: caratteristiche, numero di infetti e popolazione totale.

- L'utente visualizza un pannello che riporta l'elenco di tutti gli stati e per ognuno il numero di infetti.

- Può essere visualizzato lo stato di avanzamento della ricerca del vaccino.

- L'utente ha la possibilità di aprire un menu per l'acquisto dei potenziamenti del virus tramite i "Punti Evoluzione" collezionati. 

- L'utente può visualizzare l'avanzamento dei giorni dell'anno durante l'evoluzione della partita.

### 2.3.1 Sistema

#### Gestione stati

- Ogni stato ha una determinata popolazione e un certo numero di infetti che inizialmente è pari a zero.
- Ogni stato possiede una serie di caratteristiche:
  -  Ricchezza (1-10)
  -  Clima (Freddo, Mite, Caldo)
  -  Densità di popolazione (1-10)
  -  Controllo confini (Alto controllo = difficile infettare da fuori)
  -  Globalizzazione (Alta globalizzazione = popolazione che viaggia molto)
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
  - Diffusione negli stati freddi
  - Diffusione negli stati caldi
  - Capacità di infezione sfruttando aeroporti
  - Capacità di infezione sfruttando porti
  - Diffusione negli stati poco densamente popolati
  - Diffusione negli stati densamente popolati
  - Diffusione negli stati ricchi
  - Diffusione negli stati poveri
  - Resistenza al vaccino

#### Gestione potenziamenti

- I potenziamenti acquistabili tramite "Punti Evoluzione" agiscono sulle caratteristiche del virus elencate precedentemente migliorandole.

- I potenziamenti sono organizzati in una gerarchia, alcuni sono acquistabili solamente dopo aver già acquisito quelli soprastanti.

- Di seguito si elencano i potenziamenti disponibili:
  - Resistenza al freddo I e II
  - Resistenza al caldo I e II
  - Resistenza batterica (contro tutti i climi)
  - Abilitazione aeroporti
  - Abilitazione porti
  - Infezione attraverso animali (poco popolate)
  - Infezione delle vie respiratorie (molto popolate)
  - Resistenza ai medicinali (zone ricche)
  - Infezione dell'acqua potabile (zone povere)
  - Mutazioni spontanee (vaccino)


#### Sviluppo del vaccino

- Lo sviluppo del vaccino ha inizio quando viene raggiunto una determinata soglia di persone infette, rispetto alla popolazione globale.

- La ricerca del vaccino procede in modo costante ma può essere rallentata acquistando potenziamenti del virus.

- Se la ricerca del vaccino termina prima che l'intera popolazione sia infettata, la partita viene considerata persa.

## 2.4 Non funzionali

- L'avanzamento della simulazione deve essere regolare.

- L'infezione interna ad uno stato deve essere coerente con le caratteristiche dello stato e del virus.

- L'infezione di stati confinanti deve essere coerente con le caratteristiche dello stato e del virus.

- I parametri di gioco (virus, stati ecc.) devono essere correttamente bilanciati.

- L'esecuzione del gioco deve risultare fluida e senza situazioni di blocco.

## 2.5 Di implementazione
