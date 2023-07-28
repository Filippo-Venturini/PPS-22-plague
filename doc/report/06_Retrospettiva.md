# Capitolo 6: Retrospettiva

In questo capitolo si procede a descrivere ed analizzare il processo di sviluppo adottato per la realizzazione di PlagueDotScala, esaminando quanto svolto nelle varie iterazioni grazie al backlog prodotto.

## 6.1 Analisi del processo di sviluppo

Come già descritto nel capitolo relativo al processo di sviluppo, il team ha adottato un approccio **SCRUM-Inspired** e in seguito ad un meeting di organizzazione iniziale è stato suddiviso l'intero processo in 4 sprint da circa 15 ore ciascuno, in modo da rientrare nella stima totale di 60-70 ore di lavoro. 

Inoltre come già citato il team ha inoltre adottato il **Test-Driven-Development (TDD)** specialmente per quanto riguarda lo sviluppo del core del software e il modello del gioco.

Di seguito si procede ad effettuare un'analisi dell'andamento dello sviluppo durante i vari sprint riportati nel backlog.

## Sprint 1 : Infezione, Regioni e Configurazioni di base
Il primo sprint ha avuto come obbiettivo quello di realizzare una base solida riferendosi agli aspetti core del gioco, in modo da otttenere al termine dell'iterazione un prototipo dell'applicativo già funzionante e con gli aspetti principali realizzati, anche se in versione semplificata. Si noti infatti che durante questa iterazione il team di sviluppo si è suddiviso la realizzazione della logica di infezione di base, la rappresentazione delle regioni di gioco e il caricamento delle configurazioni di entrambi. Infatti abbinando a questi tre aspetti cardine, la progettazione di una prima versione del motore di gioco si è ottenuto un prototipo fuunzionante. 

## Sprint 2 : PowerUp, Rotte e Configurazioni avanzate
Nel secondo sprint, a partire dalle basi realizzate in quello precedente, si è reso necessario rendere più solida e coerente la logica di infezione rafforzandone i meccanismi ed è stato completato totalmente il meccanismo di caricamento delle configurazioni. Inoltre sono state aggiunte mano a mano altre funzionalità andando ad arricchire il gioco, in modo da ottenere al termine dell'iterazione un applicativo con basi più strutturate e nuove funzionalità utilizzabili. Si noti quindi l'introduzione della logica e gestione dei PowerUp, l'introduzione delle rotte tra regioni e l'introduzione della possibilità di interagire con la mappa di gioco.

## Sprint 3 : Infezione Avanzata, Menu e DNA Points
Durante il terzo sprint si è consolidata la logica di infezione finale, rendendola effettivamente coerente e adatta allo svolgimento di una partita. Inoltre partendo dal prototipo precedente è stato concepito un menu di gioco che abilita l'utente ad interagire con il meccanismo dei PowerUp precedentemente sviluppato. Si è reso inoltre necessario aggiungere meccanismi di interazione e visualizzazione per rendere l'utente maggiormente partecipe di quanto accade durante una partita. Infine è stata integrata nel gioco la logia dei DNAPoints, aggiungendola come nuova funzionalità al prototipo che risulta già giocabile.

## Sprint 4 : Launcher, Vaccino e Bilanciamento
Infine al quarto e ultimo sprint il team si è trovato a dover gestire tutto ciò che riguarda l'inizio e il termine di una partita, essendo tutta la parte centrale del gioco solidamente strutturata e funzionante. Si è resa indispensabile la progettazione di un Menu iniziale che permetta di iniziare correttamente una nuova partita, allo stesso modo sono state gestite le logiche finali per determinare la vittoria o la sconfitta dell'utente. Inoltre è stato aggiunto l'ultimo tassello fondamentale appartenente al dominio applicativo, ovvero il vaccino. Terminati questi task il gioco risulta in una versione completa, possiede tutte le funzionalità richieste in fase di analisi dei requisiti e risulta perfettamente utilizzabile. L'ultimo passo necessario è stato un bilanciamento dei parametri di simulazione, per renderlo quanto più realistico possibile.
