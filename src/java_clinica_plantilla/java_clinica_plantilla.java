/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_clinica_plantilla;
import splash.splash;
/**
 *
 * @author Eudy
 */
public class java_clinica_plantilla {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
            conection.Mysql mysql = new conection.Mysql();
            new Thread(new splash(mysql)).start();
    }
    
}
