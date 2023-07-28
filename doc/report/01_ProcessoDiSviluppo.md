# Capitolo 1: Processo di sviluppo adottato

In questo capitolo iniziale, si illustra la metodologia di sviluppo che sarà utilizzata per la realizzazione del progetto dettagliandone le varie parti.

Nello specifico, si è scelto di adottare un processo di sviluppo SCRUM-inspired come suggerito dalle linee guida. Di conseguenza sono stati identificati i seguenti ruoli: esperto di dominio (Nicolò Malucelli) che si occuperà di garantire usabilità e qualità del risultato, product owner (Alberto Di Girolamo) che si occuperà di supervisionare l'andamento del progetto coordinando il team di sviluppo. Il processo di sviluppo sopra descritto è stato scelto in quanto ci permetterà di sperimentare l'approccio iterativo nella gestione di un progetto in team.

Di seguito si procede ad una descrizione dettagliata del processo di sviluppo adottato.

## Analisi applicativo Plague.Inc

Poichè il progetto prende ispirazione dal noto gioco "Plague.Inc", si è pensato di organizzare un meeting iniziale con lo scopo di analizzare nel dettaglio l'applicativo ed i suoi aspetti principali.

## Definizione degli obiettivi 

Sulla base dell'analisi effettuata, verranno organizzati alcuni meeting iniziali con lo scopo di definire i requisiti di massima del sistema, applicando eventuali modifiche e/o adattamenti alla logica di gioco presente in "Plague.Inc". In seguito, verrà realizzata una prima rappresentazione del modello del dominio applicativo così da poter stimare e dimensionare la mole di lavoro richiesta dal progetto. 

Uno degli obiettivi principali sarà sviluppare l'applicativo attraverso un approccio incrementale che renda possibile, al termine di ogni sprint, verificare le nuove funzionalità aggiunte.

Si stima che questa prima parte impiegherà all'incirca una settimana di lavoro, fornendo così al team di sviluppo una chiara visione degli obiettivi da raggiungere.

## Pianificazione e organizzazione del lavoro 

Per quanto riguarda l'organizzazione del lavoro nel dettaglio, il team ha previsto quattro sprint. Per ogni sprint, come previsto da SCRUM, si è deciso di mantenere un backlog contenente i vari task in ordine di priorità, con indicata la corrispondente stima in termini di complessità.

I task presenti all'interno di ogni backlog saranno distribuiti tra i membri del team su base volontaria, in modo da mantenere il carico di lavoro bilanciato e consentendo ad ognuno di poter realizzare funzionalità non banali.

Al termine di ogni sprint è previsto un meeting, durante il quale saranno analizzati i risultati raggiunti in relazione al backlog prodotto. Inoltre, verrà stilato il backlog relativo allo sprint successivo, identificando i task sulla base delle funzionalità necessarie. I meeting saranno realizzati in via telematica utilizzando Microsoft Teams.

## Strumenti di controllo della qualità 

Per verificare la correttezza delle funzionalità sviluppate si utilizzerà un approccio di tipo Test Driven Development (TDD) in modo tale da verificare la presenza di errori sui singoli componenti durante la loro implementazione e poterli quindi correggere prima di procedere con lo sviluppo di feature successive.

Si è inoltre deciso di utilizzare GitHub Actions in modo da poter verificare in automatico la correttezza dei test in seguito ad ogni push sul repository, segnalando l'eventuale presenza di test falliti attraverso una notifica via mail a tutti i membri del gruppo.
