package ticket;

import exceptions.TicketExausted;
import java.util.Calendar;

/**
 *
 * @author Manuele
 */
public class MultiTicket extends Ticket{
    private Calendar[] validationDate; //può essere timbrato più volte
    private int validityTime, maxTranspNum, transpNum;
    
    public MultiTicket(/*String code,*/ double cost, int validityTime, int maxTranspNum) {
        
//        this.code = code;
        this.cost = cost;
        this.validityTime = validityTime;
        this.maxTranspNum = maxTranspNum;
        validationDate = new Calendar[maxTranspNum];
        transpNum = -1;
    }
    
    @Override
    public void validate() throws TicketExausted{
        
        if(hasTravelsLeft()) {
            
            transpNum++;
            validationDate[transpNum] = Calendar.getInstance();
        }
        
        else throw new TicketExausted("You cannot use this ticket more than " + maxTranspNum + " times");
    }
    
    @Override
    public boolean isValid() {
        
        if(validated()) {
            
            Calendar now = Calendar.getInstance(); //ora attuale
            Calendar val = (Calendar)validationDate[transpNum].clone(); //ora della convalida
            val.add(Calendar.SECOND, validityTime);          //più il tempo per cui vale il biglietto
            //Aggiungere tempo con la classe Date è una rottura. Molto più semplice con calendar
        
            return now.getTime().before(val.getTime());
        }
        
        else return false;
    }
    
    private boolean hasTravelsLeft() {
        
        return transpNum < maxTranspNum - 1;
        //Uso transpNum come puntatore per accedere al vettore di Calendar.
        //Se transpNum < maxTranspNum -1, ho ancora almeno un viaggio disponibile
        //con il biglietto
    }
    
    private boolean validated() {
        
        return transpNum > -1 && transpNum < maxTranspNum && validationDate[transpNum] != null;
    }
}
