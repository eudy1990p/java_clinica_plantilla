/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import javax.swing.JOptionPane;

/**
 *
 * @author Eudy
 */
public class ClassLogin {
    private conection.Mysql mysql;
    
    public ClassLogin(conection.Mysql mysql){
        this.mysql = mysql;
    } 
    
    public boolean validarUsuario(String usuario,String clave){
        int existe = this.mysql.getValues("users", "where name_user = '"+usuario+"' and password_user = '"+clave+"' and display = '1'");
        if(existe > 0){
            return true;
        }else{
             JOptionPane.showMessageDialog(null, "Usuario o clave no coinsiden.");
             return false;
            }
    }
}
