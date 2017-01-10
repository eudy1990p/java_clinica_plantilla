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
import java.lang.Object;
import java.util.ArrayList;

/**
 *
 * @author Eudy
 */
public class ClassBlood{
    private conection.Mysql mysql;
    private ValidData valid = new ValidData();
    private String usuario,clave,type_user,id;
    private String[] key = {"name_of_blood","when_it","id_user"};
   
    private int lineas=10;
    private boolean agregar=true;
    private ArrayList<String> camposEdit = new ArrayList<String>();
    private ArrayList<String> valorEdit = new ArrayList<String>();
   
        private String usuarioID,nombreUsuario,nombreTituloUsuario;

    
    public ClassBlood(){
        mysql = new Mysql();
    }
    public ClassBlood(conection.Mysql mysql){
       this.mysql =mysql;
    }
    public boolean getAgregar(){
        return agregar;
    }
    
    public void setAgregar(boolean estado){
        this.agregar = estado;
    }
    public boolean elimnar(String id){
        boolean respuesta = this.mysql.deleteRecord("type_of_blood", id);
        return respuesta;
    }
    
    public void setDatosUsuario(String usuarioID, String nombreUsuario,String nombreTituloUsuario){
        this.usuarioID = usuarioID;
        this.nombreUsuario = nombreUsuario;
        this.nombreTituloUsuario = nombreTituloUsuario;
        //JOptionPane.showMessageDialog(null, "Usuario "+this.usuarioID+" "+this.nombreUsuario+" "+this.nombreTituloUsuario);    
    }
    
    public boolean insert(String usuario){
        if(valid.validEmpty(usuario, "Nombre de tipo de sangre")){
           return false; 
        }else{
           
                if(this.validarUsuario(usuario)){
                    this.usuario = usuario;
                    boolean respuesta = this.procesarInsert();
                    return respuesta;
                }else{
                    return false;
                }
         }
        
    }
    public boolean validarUsuario(String usuario){
         int existe = this.mysql.getValues("type_of_blood", "where name_of_blood = '"+usuario+"' ");
          if(existe < 1){
            return true;
        }else{
            JOptionPane.showMessageDialog(null, "El tipo de sangre ya existe");
            return false;
          }
    }
    public void limpiarTexto(javax.swing.JTextField texto){
       texto.setText("");
    
    }
    public boolean update( String usuario,String id){
        boolean respuesta = true;
        if(valid.validEmpty(usuario)){
            if(!this.validarUsuario(usuario)){
                System.out.println("Usuario existe");
                respuesta = false;
                return respuesta;
            }else{
               System.out.println("11111Usuario existe");

                this.valorEdit.add(usuario);
                this.camposEdit.add("name_of_blood");
            }
            this.usuario = usuario;
            this.id = id;

            respuesta = this.procesarUpdate();
            
        }
            return respuesta;
       // }
    }
    public boolean procesarInsert(){
        String[] values = {this.usuario,"now()",this.usuarioID};
        System.out.println(" key "+this.key+" Values "+values+" total index "+values.length);
        boolean respuesta = mysql.generarInsert(this.key, values, "type_of_blood");
        return respuesta;
    }
    
    public boolean procesarUpdate(){
         int total =this.camposEdit.size();
         String[] key = new String[total];
        String[] values =new String[total];

        for(int i = 0 ; i <  this.camposEdit.size() ; i++){
            key[i] =  this.camposEdit.get(i)/*{"password_user","type_of_user"}*/;
            values[i] = this.valorEdit.get(i);
        }
        System.out.println(" key "+this.key+" Values "+values+" total index "+values.length);
        boolean respuesta = mysql.updateRecord("type_of_blood",this.id,key, values);
        return respuesta;
    }
    
    public void mostrarDatosTabla(JTable table,JLabel JLabelTotal,String palabraBuscar){
        String[] datos = {"id","name_of_blood"};
        String campo = "name_of_blood";
        System.out.println(palabraBuscar);
        Object[][] resultado = (Object[][]) this.mysql.generarSelect("type_of_blood", datos,palabraBuscar,campo);
        Object[][] infoTabla= (Object[][]) resultado[0][0];
        DefaultTableModel modelo = new DefaultTableModel(infoTabla,datos);
        JLabelTotal.setText(resultado[0][1]+"");
        table.setModel(modelo); 
    }
    public String[] mostrarEditarUsuario(String id){
        String[] campos = {"id","name_of_blood"};
        String[] respuesta = this.mysql.generarSelect("type_of_blood", id,campos);
        this.setAgregar(false);
        return respuesta;
    }
    
    public void cambiarTestoParaEditar(javax.swing.JButton b,javax.swing.JLabel l,javax.swing.JTextField jtf){
        if(this.getAgregar()){
            b.setText("Agregar");
            l.setText("Agregar Usuario");
            //jtf.enable(true);
        }else{
            b.setText("Editar");
            l.setText("Editar Usuario");
            //jtf.enable(false);
        }
    }
    
    public void mostrarDatosTabla(JTable table,JLabel JLabelTotal){
        String[] datos = {"id","name_of_blood"};
        
        Object[][] resultado = (Object[][]) this.mysql.generarSelect("type_of_blood", datos);
        Object[][] infoTabla= (Object[][]) resultado[0][0];
        DefaultTableModel modelo = new DefaultTableModel(infoTabla,datos);
        JLabelTotal.setText(resultado[0][1]+"");
        table.setModel(modelo);
        
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
        String where = "where id = (SELECT max(id) from type_of_blood)";
        this.mysql.generarSelectLastRow("type_of_blood",where, modal, columnas,datos);
        JLabelTotal.setText(""+modal.getRowCount());
        
    }*/
    
}
