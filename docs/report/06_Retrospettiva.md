# Capitolo 6: Retrospettiva

In questo capitolo si procede a descrivere ed analizzare il processo di sviluppo adottato per la realizzazione di PlagueDotScala, esaminando quanto svolto nelle varie iterazioni grazie al backlog prodotto.

## 6.1 Analisi del processo di sviluppo

Come già descritto nel capitolo relativo al processo di sviluppo, il team ha adottato un approccio **SCRUM-Inspired** e in seguito ad un meeting di organizzazione iniziale è stato suddiviso l'intero processo in 4 sprint da circa 15 ore ciascuno, in modo da rientrare nella stima totale di 60-70 ore di lavoro. 

Inoltre come già citato il team ha adottato il **Test-Driven-Development (TDD)** specialmente per quanto riguarda lo sviluppo del core del software e il modello di gioco.

Di seguito si procede ad effettuare un'analisi dell'andamento dello sviluppo durante i vari sprint riportati nel backlog.

### Sprint 1 : Infezione, Regioni e Configurazioni di base
Il primo sprint ha avuto come obbiettivo quello di realizzare una base solida riferendosi agli aspetti core del gioco, in modo da ottenere al termine dell'iterazione un prototipo dell'applicativo già funzionante e con gli aspetti principali realizzati, anche se in versione semplificata. Si noti infatti che durante questa iterazione il team di sviluppo si è suddiviso la realizzazione della logica di infezione di base, la rappresentazione delle regioni di gioco e il caricamento delle configurazioni di entrambi. Infatti abbinando a questi tre aspetti cardine, la progettazione di una prima versione del motore di gioco si è ottenuto un prototipo funzionante. 

### Sprint 2 : PowerUp, Rotte e Configurazioni avanzate
Nel secondo sprint, a partire dalle basi realizzate in quello precedente, si è reso necessario rendere più solida e coerente la logica di infezione rafforzandone i meccanismi ed è stato completato totalmente il meccanismo di caricamento delle configurazioni. Inoltre sono state aggiunte mano a mano altre funzionalità andando ad arricchire il gioco, in modo da ottenere al termine dell'iterazione un applicativo con basi più strutturate e nuove funzionalità utilizzabili. Si noti quindi l'introduzione della logica e gestione dei PowerUp, l'introduzione delle rotte tra regioni e l'introduzione della possibilità di interagire con la mappa di gioco.

### Sprint 3 : Infezione Avanzata, Menu e DNA Points
Durante il terzo sprint si è consolidata la logica di infezione finale, rendendola effettivamente coerente e adatta allo svolgimento di una partita. Inoltre partendo dal prototipo precedente è stato concepito un menu di gioco che abilita l'utente ad interagire con il meccanismo dei PowerUp precedentemente sviluppato. Si è reso inoltre necessario aggiungere meccanismi di interazione e visualizzazione per rendere l'utente maggiormente partecipe di quanto accade durante una partita. Infine è stata integrata nel gioco la logica dei DNAPoints, aggiungendola come nuova funzionalità al prototipo che risulta già giocabile.

### Sprint 4 : Launcher, Vaccino e Bilanciamento
Infine al quarto e ultimo sprint il team si è trovato a dover gestire tutto ciò che riguarda l'inizio e la fine di una partita, essendo tutta la parte centrale del gioco solidamente strutturata e funzionante. Si è resa indispensabile la progettazione di un Menu che permetta di iniziare correttamente una nuova partita, allo stesso modo sono state gestite le logiche finali per determinare la vittoria o la sconfitta dell'utente. Inoltre è stato aggiunto l'ultimo tassello fondamentale appartenente al dominio applicativo, ovvero il vaccino. Terminati questi task il gioco risulta in una versione completa, possiede tutte le funzionalità richieste in fase di analisi dei requisiti e risulta perfettamente utilizzabile. L'ultimo passo necessario è stato un bilanciamento dei parametri di simulazione, per renderlo quanto più realistico possibile.

Analizzando a posteriori il processo di sviluppo il team si reputa soddisfatto, in quanto nonostante fosse la prima esperienza con questo tipo di approccio, si è riusciti ad organizzare i vari sprint in modo da ottenere una versione prototipale funzionante, sempre più solida e completa. Inoltre all'interno di ogni sprint i task sono stati suddivisi equamente, in questo modo ogni membro del gruppo è riuscito ad analizzare e risolvere problemi non banali e indispensabili per la riuscita del progetto. L'approccio TDD è risultato un po' forzato nelle prime fasi dello sviluppo, ma procedendo ne sono stati notati i benefici nelle fasi più avanzate, nelle quali si è enormemente minimizzato il tempo richiesto per risolvere problematiche su codice già sviluppato.

## 6.2 Conclusioni

La realizzazione di questo progetto ha incrementato notevolmente le capacità di gestione e organizzazione del lavoro da parte dell'intero team. La scelta sul tipo di software da realizzare non è stata semplice, in quanto si richiedeva necessariamente un dominio applicativo che desse spazio all'esplorazione di meccanismi complessi, ma che allo stesso tempo non interferisse con l'obbiettivo principale aggiungendo complessità non necessaria per la realizzazione della parte grafica. Con questo progetto inoltre il team è riuscito a sperimentare concretamente meccanismi di Scala che diversamente sarebbe stato difficile apprendere completamente ed applicare. Il team ha notato inoltre un deciso e marcato miglioramento durante la fase di progettazione rispetto a progetti svolti in passato. Reputiamo quindi questo progetto come costruttivo e fondamentale per l'apprendimento dei concetti trattati durante il corso e utile per l'integrazione di essi con le nostre conoscenze pregresse.

[Vai a Implementazione](./05_Implementazione.md) | [Torna alla Home](../index.md) |

