# Capitolo 4: Design di dettaglio

## Struttura del GameModel

<p align="center">
  <img src="./images/04_DesignDiDettaglio/GameModelDiagram.png" width="500" height="300" alt="Diagramma dei Package"/>
  <p align="center"><em>Figura 4.1: Diagramma del GameModel</em></p>
</p>

Come già anticipato il GameModel si compone di tutte le entità individuate durante l'analisi del modello di dominio, ovvero: `Virus`, `Vaccine`, `PowerUp`, `DNAPoint`, `Region`, `Route` e `World`.
Come mostrato in *figura 4.1* è stato deciso di progettare vari gestori che hanno l'obiettivo di rendere la gestione del dominio applicativo più versatile ed espandibile. In particolare sono quindi presenti: `InfectionHandler`, `VaccineHandler`, `DNAPointsHandler`, `PowerUpManager` e `RouteManager`.

## GameEngine: Il motore di gioco

Il `GameEngine` rappresenta il motore di gioco che si occupa della progressione della partita. 

Nello specifico contiene al suo interno il *gameLoop()* e ogni sua iterazione rappresenta un giorno trascorso all'interno del mondo di gioco.
Per ogni giornata il `GameEngine` ha il compito di utilizzare i gestori contenuti all'interno del `GameModel` per far progredire la partita, in particolare:
- Tramite l'`InfectionHandler` fa progredire l'infezione del virus.
- Utilizzando il `VaccineHandler` viene valutata la progressione della ricerca del vaccino.
- Con il `DNAPointsHandler` vengono generati i DNAPoints.

Il `GameEngine` valuta inoltre se la partita viene vinta oppure persa dall'utente gestendo in questo modo la logica di fine partita.

## Caricamento delle configurazioni di gioco
(Schema)

## Gestione del mondo di gioco
(World con filtri, gerarchia regioni, rotte (Singleton))
(Schema)

## Gestione dell'infezione
(Schema)

## Vaccino
(Strategy)
(Schema?)

## Potenziamenti
(PowerUp, PowerUpManager)
(Schema)

## DNAPoint
(Schema)

## GameView

## MenuView

## LauncherView
