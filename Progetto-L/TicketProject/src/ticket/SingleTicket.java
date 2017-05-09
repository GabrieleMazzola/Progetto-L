package ticket;

import java.util.Calendar;

/**
 *
 * @author Manuele
 */
public class SingleTicket extends Ticket{
    
//    private String code; //Il codice in genere non esiste per i biglietti singoli
//    private double cost;
    //Uso Calendar perché è più facile gestire il tempo in questo modo
    private Calendar validationDate; //quando è stato convalidato il biglietto
    private int validityTime; //per quanto è valido in minuti
    
    public SingleTicket(/*String code,*/ double cost, int validityTime) {
        
//        this.code = code;
        this.cost = cost;
        this.validityTime = validityTime;
    }
    
    //Convalida il biglietto se non è già stato convalidato (ossia se non è ancora
    //stata inserita una validationDate)
    @Override
    public void validate() {
        
        if (validationDate == null) validationDate = Calendar.getInstance();
    }
    //Verifica se il biglietto è valido. Per farlo controllo il momento in cui è stato
    //convalidato e l'ora attuale
    @Override
    public boolean isValid() {
        
        if(validationDate != null) {
            
            Calendar now = Calendar.getInstance(); //ora attuale
            Calendar val = (Calendar)validationDate.clone(); //ora della convalida
            val.add(Calendar.MINUTE, validityTime);          //più il tempo per cui vale il biglietto
            //Aggiungere tempo con la classe Date è una rottura. Molto più semplice con calendar
        
            return now.getTime().before(val.getTime());
        }
        
        else return false;
    }
    
    /*
    *Metodi getter
    */
//    public String getCode() {return code;}
    public Calendar getValidationDate() {return validationDate;}
    public int getValidityTime() {return validityTime;}
}
