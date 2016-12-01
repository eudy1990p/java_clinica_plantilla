/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.Classes;

import javax.swing.JOptionPane;

/**
 *
 * @author Eudy
 */
public class ValidData {
    
    
    public boolean differentPass(String pass1, String pass2){
        if((pass1.equals(pass2))){
            return false;
        }else{
            JOptionPane.showMessageDialog(null, "La claves no coinsiden");
            return true;
        }
    }
    
    public boolean validEmpty(String n,String name){
        if(n.isEmpty()){
            JOptionPane.showMessageDialog(null, "El Campo "+name+" esta vacio");
            return true;
        }else{
            return false;
        }
        
    }
}
