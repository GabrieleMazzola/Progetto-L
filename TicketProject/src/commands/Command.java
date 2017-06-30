/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import centralsystem.CSystem;
import org.json.simple.JSONObject;

/**
 *
 * @author Francesco
 */
public abstract class Command {
    
    CSystem centralSystem;
    
    public Command(CSystem centralSystem){
        this.centralSystem = centralSystem;
    }
    
    public abstract String execute(JSONObject data);
    
}
