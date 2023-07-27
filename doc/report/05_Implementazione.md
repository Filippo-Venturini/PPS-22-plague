# Capitolo 5: Implementazione

## Sviluppo collaborativo

Nelle fasi iniziali il team si è concentrato sulla progettazione e implementazione delle basi del applicativo con un approccio collaborativo, procedendo alla suddivisione dei vari componenti solo successivamente. In particolare la struttura dell'architettura generale, il `GameEngine` e la parte grafica della mappa di gioco sono state progetttate e implementate in collaborazione dall'intero team di sviluppo.

## Alberto Di Girolamo
(Infezione, PowerUp, GrigliaMenu, Launcher, Product-Owner)

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
