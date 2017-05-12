/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import bank.CheckValidityAlgorithm;
import bank.LuhnAlgorithm;


/**
 *
 * @author Andrea
 */
public class TestBankAlgorithm {
    
    public static void main(String[] args) {
        String code ="4388576018410707";//"5 3 4 2 0 7 0 0 1 3 1 9 5 8 3 8";
        
        CheckValidityAlgorithm algorithm = new LuhnAlgorithm();
        if(algorithm.check(code)) System.out.println("Yeah");
        else System.out.println("Fuuuuuuuuuuuu");      
    }
}
