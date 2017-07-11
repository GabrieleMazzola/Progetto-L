Procedura per avviare il CSystem:

1) Aprire il progetto in Netbeans
2) Eseguire la classe CSystemLoader presente nel package main.
3) Da interfaccia grafica, scegliere se volere avviare il CSystem con una connessione ad database reale o simulato
   N.b: per avviare il database reale, è necessario settare il proprio username e password nella classe DBMapperFactory nel package database.factories.
		E' poi necessario creare un database eseguendo in mysql la query che si trova su github sotto il nome di creaDatabase.txt

A questo punto è possibile anche avviare la macchinetta e il controllore eseguendo i file rispettivamente GUITicketMachine e GUICollector, sempre nel
package main
  