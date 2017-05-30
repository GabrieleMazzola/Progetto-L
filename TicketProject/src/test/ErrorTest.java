/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import gui.ErrorScene;
import javafx.stage.Stage;

/**
 *
 * @author Francesco
 */
public class ErrorTest {
    public static void main(String[] args) {
        Stage error = new ErrorScene("che schifo la vita!");
        error.show();
    }
}
