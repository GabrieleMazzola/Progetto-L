/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import centralsystem.CSystem;
import centralsystem.LogCS;

/**
 *
 * @author Gabriele
 */
public class TestSystem {
    
    public static void main(String[] args) {
        CSystem sys = new CSystem();
        LogCS.getInstance().abilita();
    //    sys.printTickets();
     //   sys.printUsers();
    }
}
