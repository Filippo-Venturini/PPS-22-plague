# Capitolo 2: Requisiti

## 2.1 Business

## 2.2 Modello di dominio

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

### 2.3.1 Sistema

#### Gestione stati

- Ogni stato possiede una serie di caratteristiche:
  -  Ricchezza (1-10)
  -  Clima (Freddo, Mite, Caldo)
  -  Densità di popolazione (1-10)
  -  Istruzione 

#### Comportamento virus

- Il virus possiede le seguenti caratteristiche: 
  - Resistenza al freddo
  - Resitenza al caldo
  - Resistenza batterica (contro tutti i climi)
  - Diffusione via aria
  - Diffusione via acqua
  - Diffusione attraverso animali
  - Resistenza ai medicinali (Contro il vaccino)
  - Mutazioni spontanee (Contro il vaccino)

## 2.4 Non funzionali

## 2.5 Di implementazione
