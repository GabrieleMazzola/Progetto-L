/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import machines.StubMachine;

/**
 *
 * @author Gabriele
 */
public class TesterLogin {
    
    public static void main(String[] args) {
        StubMachine machine = new StubMachine("10.87.232.53", 5000);
        System.out.println(machine.login("ADMIN", "AcDMIN"));
    }
}
