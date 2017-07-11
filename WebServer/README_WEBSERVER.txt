Procedura per avviare il WebServer:

1) Aprire eclipse (usare eclipse JEE Neon) e creare una workspace (da ora indicata con "workspace").

2) File -> Import -> Existing Maven Projects. Selezionare la cartella WebServer/ticket scaricata dal git. Controllare la presenza del file "pom.xml" nella schermata Eclipse e premere "Finish".

3) A questo punto Maven preparera' tutto il progetto, dovrebbero esserci alcuni Warnings ma nessun Error.

4) Creazione del Server: File -> New -> Server. Selezionare "Tomcat v7.0 Server" ed impostare "Server's host name" = localhost. D'ora in poi "Server name" sara' ProgettoLServer.

5) Cliccare "Download and Install" e scegliere una cartella a piacimento. ****NOTA****

6) A questo punto non resta che cliccare con il tasto destro sul progetto "ticket" nel project explorer di Eclipse e premere "Run as.." -> "Run on Server". 

7) Selezionare il server precedentemente creato e lanciare. Di default il server viene lanciato su localhost:8080, se la porta fosse occupata e' comunque possibile scegliere un'altra porta modificando il file "server.xml" dentro la directory di installazione del Server Tomcat.

N.B: Quando viene lanciato il WebServer esso tenta di collegarsi al sistema centrale, che pertanto deve essere gia' attivato. L'indirizzo IP e la PORTA di questa connessione sono modificabili nella classe "Start.java" presente nel package "avvio". ("src/main/java/avvio")

N.B: Tutta la documentazione relativa alle API create e al relativo funzionamento e' presente nella pagina "API Web Services" della wiki del progetto su github.


****NOTA**** : dopo aver selezionato la cartella spesso succede che il tasto "Finish" non diventi disponibile. Per risolvere il problema bisogna ripremere "Download and Install" e riselezionare la stessa cartella di prima.
A questo punto premere "Finish". Possono uscire degli Alerts di errore, chiuderli e continuare.
