package ticket;

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
    public void validate() {
        
        if(transpNum < maxTranspNum-1) {
            
            transpNum++;
            validationDate[transpNum] = Calendar.getInstance();
        }
    }
    
    @Override
    public boolean isValid() {
        
        if(hasTravelsLeft() && validationDate[transpNum] != null) {
            
            Calendar now = Calendar.getInstance(); //ora attuale
            Calendar val = (Calendar)validationDate[transpNum].clone(); //ora della convalida
            val.add(Calendar.MINUTE, validityTime);          //più il tempo per cui vale il biglietto
            //Aggiungere tempo con la classe Date è una rottura. Molto più semplice con calendar
        
            return now.getTime().before(val.getTime());
        }
        
        else return false;
    }
    
    private boolean hasTravelsLeft() {
        
        return transpNum < maxTranspNum && transpNum > -1;
    }
    
    //Da rivedere. Così funziona ma è brutto
}
