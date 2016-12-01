/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.Classes;

import conection.Mysql;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Eudy
 */
public class ClassUser{
    private ValidData valid = new ValidData();
    private Mysql mysql;
    private String usuario,clave,type_user;
    private String[] key = {"name_user","password_user","when_it","id_user","type_of_user"};
    private int id_user;

            
    public ClassUser(){
        mysql = new Mysql();
    }
    
    public boolean insert(String usuario,String clave,String RepetidaClave,String type_user){
        if(valid.validEmpty(usuario, "Nombre de usuario")){
           return false; 
        }else if(valid.validEmpty(clave, "Clave de usuario")){
            return false;
        }else if(valid.validEmpty(RepetidaClave, "Repetir clave de usuario")){
            return false;
        }else if(valid.differentPass(clave, RepetidaClave)){
            return false;
        }else{
            this.usuario = usuario;
            this.clave = clave;
            this.type_user = type_user;
            boolean respuesta = this.procesarInsert();
            return respuesta;
        }
    }
    
    public boolean procesarInsert(){
        String[] values = {this.usuario,this.clave,"now()",this.id_user+"1",this.type_user};
        System.out.println(" key "+this.key+" Values "+values+" total index "+values.length);
        boolean respuesta = mysql.generarInsert(this.key, values, "users");
        return respuesta;
    }
   
    public void mostrarDatosTabla(JTable table,JLabel JLabelTotal){
        //table.setModel(null);
        DefaultTableModel modal = (DefaultTableModel) table.getModel();
        table.setModel(new DefaultTableModel());
        table.setModel(modal);
        //DefaultTableModel modal = (DefaultTableModel) table.getModel();
        
        Object[] columnas = new Object[modal.getColumnCount()];
        //{"name_user","password_user","when_it","id_user","type_of_user"}
        String[] datos = {"id","name_user"};
        
      //  this.mysql.generarSelect("users", modal, columnas,datos);
       // JLabelTotal.setText(""+modal.getRowCount());
        
    }
    
   /* public void mostrarNuevoDatoTabla(JTable table,JLabel JLabelTotal){
        //table.setModel(this.getBasicModel());
        DefaultTableModel modal = (DefaultTableModel) table.getModel();
        DefaultTableModel t = new DefaultTableModel();
        table.setModel(t);
        table.setModel(modal);
        
        
        Object[] columnas = new Object[modal.getColumnCount()];
        //{"name_user","password_user","when_it","id_user","type_of_user"}
        String[] datos = {"id","name_user"};
        String where = "where id = (SELECT max(id) from users)";
        this.mysql.generarSelectLastRow("users",where, modal, columnas,datos);
        JLabelTotal.setText(""+modal.getRowCount());
        
    }*/
    
}
