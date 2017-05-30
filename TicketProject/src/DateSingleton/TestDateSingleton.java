/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DateSingleton;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

public class TestDateSingleton {

    public static void main(String[] args) throws ParseException {
        Date date = new Date();
        DateOperations operator = DateOperations.getInstance();
        
        //conversione a String
        String stringa = operator.toString(date);
        
        System.out.println("stringa codificata: " + stringa);
        
        date = operator.parse(stringa);
        
        System.out.println("Data decodificata: " + date.toString());
    }
}
