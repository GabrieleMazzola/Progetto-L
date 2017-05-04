/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import centralsystem.CSystem;

/**
 *
 * @author Gabriele
 */
public class TestSystem {
    
    public static void main(String[] args) {
        CSystem sys = new CSystem();
        
        sys.printTickets();
        sys.printUsers();
    }
}
