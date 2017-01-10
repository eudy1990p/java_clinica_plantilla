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
public class ClassUser{
    private conection.Mysql mysql;
    private ValidData valid = new ValidData();
    private String usuario,clave,type_user,id;
    private String[] key = {"name_user","password_user","when_it","id_user","type_of_user"};
    private int lineas=10;
    private boolean agregar=true;
    private ArrayList<String> camposEdit = new ArrayList<String>();
    private ArrayList<String> valorEdit = new ArrayList<String>();
    private String usuarioID,nombreUsuario,nombreTituloUsuario;
            
    public ClassUser(){
        mysql = new Mysql();
    }
    public ClassUser(conection.Mysql mysql){
       this.mysql =mysql;
    }
    public boolean getAgregar(){
        return agregar;
    }
    
    public void setAgregar(boolean estado){
        this.agregar = estado;
    }
    public boolean elimnar(String id){
        boolean respuesta = this.mysql.deleteRecord("users", id);
        return respuesta;
    }
    
    public void setDatosUsuario(String usuarioID, String nombreUsuario,String nombreTituloUsuario){
        this.usuarioID = usuarioID;
        this.nombreUsuario = nombreUsuario;
        this.nombreTituloUsuario = nombreTituloUsuario;
        //JOptionPane.showMessageDialog(null, "Usuario "+this.usuarioID+" "+this.nombreUsuario+" "+this.nombreTituloUsuario);    
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
           
                if(this.validarUsuario(usuario)){
                    this.usuario = usuario;
                    this.clave = clave;
                    this.type_user = type_user;
                    boolean respuesta = this.procesarInsert();
                    return respuesta;
                }else{
                    return false;
                }
         }
        
    }
    public boolean validarUsuario(String usuario){
         int existe = this.mysql.getValues("users", "where name_user = '"+usuario+"' ");
          if(existe < 1){
            return true;
        }else{
            JOptionPane.showMessageDialog(null, "El usuario ya existe");
            return false;
          }
    }
    public void limpiarTexto(javax.swing.JPasswordField p1,javax.swing.JPasswordField p2,javax.swing.JTextField texto,javax.swing.JComboBox combo){
        p1.setText("");p2.setText("");texto.setText("");combo.setSelectedIndex(0);
    
    }
    public boolean update( String usuario,String clave,String RepetidaClave,String type_user,String id){
        
        if(valid.validEmpty(type_user)){
          this.valorEdit.add(type_user);
          this.camposEdit.add("type_of_user");
        }
        if(valid.validEmpty(clave)){
          this.valorEdit.add(clave);
          this.camposEdit.add("password_user");
        }
            this.usuario = usuario;
            this.clave = clave;
            this.type_user = type_user;            
            this.id = id;

            boolean respuesta = this.procesarUpdate();
            return respuesta;
       // }
    }
    public boolean procesarInsert(){
        String[] values = {this.usuario,this.clave,"now()",this.usuarioID,this.type_user};
        System.out.println(" key "+this.key+" Values "+values+" total index "+values.length);
        boolean respuesta = mysql.generarInsert(this.key, values, "users");
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
        boolean respuesta = mysql.updateRecord("users",this.id,key, values);
        return respuesta;
    }
    
    public void mostrarDatosTabla(JTable table,JLabel JLabelTotal,String palabraBuscar){
        String[] datos = {"id","name_user","type_of_user"};
        String campo = "name_user";
        System.out.println(palabraBuscar);
        Object[][] resultado = (Object[][]) this.mysql.generarSelect("users", datos,palabraBuscar,campo);
        Object[][] infoTabla= (Object[][]) resultado[0][0];
        DefaultTableModel modelo = new DefaultTableModel(infoTabla,datos);
        JLabelTotal.setText(resultado[0][1]+"");
        table.setModel(modelo); 
    }
    public String[] mostrarEditarUsuario(String id){
        String[] campos = {"id","name_user","type_of_user"};
        String[] respuesta = this.mysql.generarSelect("users", id,campos);
        this.setAgregar(false);
        return respuesta;
    }
    
    public void cambiarTestoParaEditar(javax.swing.JButton b,javax.swing.JLabel l,javax.swing.JTextField jtf){
        if(this.getAgregar()){
            b.setText("Agregar");
            l.setText("Agregar Usuario");
            jtf.enable(true);
        }else{
            b.setText("Editar");
            l.setText("Editar Usuario");
            jtf.enable(false);
        }
    }
    
    public void mostrarDatosTabla(JTable table,JLabel JLabelTotal){
        /*String[] titulos = {"id","usuario"};
          //modelo.setColumnIdentifiers(titulos);
           Object[][] fila = new Object[this.lineas][2];
           for(int c = 0 ; c < this.lineas ; c++){
               System.out.println("---- x "+ c);
                //for(int i = 0 ; i < 2 ; i++){
                    fila[c][0] = "x "+ c+" y ";
                    //System.out.println("x "+ c+" y "+i);
                    fila[c][1]  = "Eudy";
                    //modelo.addRow(fila);
                //}
           }
          DefaultTableModel modelo = new DefaultTableModel(fila,titulos);
          table.setModel(modelo);*/


        //table.setModel(null);
        //DefaultTableModel modal = (DefaultTableModel) table.getModel();
        //table.setModel(new DefaultTableModel());
        //table.setModel(modal);
        //DefaultTableModel modal = (DefaultTableModel) table.getModel();
        
        //Object[] columnas = new Object[modal.getColumnCount()];
        //{"name_user","password_user","when_it","id_user","type_of_user"}
        String[] datos = {"id","name_user","type_of_user"};
        
        Object[][] resultado = (Object[][]) this.mysql.generarSelect("users", datos);
        Object[][] infoTabla= (Object[][]) resultado[0][0];
        DefaultTableModel modelo = new DefaultTableModel(infoTabla,datos);
        JLabelTotal.setText(resultado[0][1]+"");
        table.setModel(modelo);
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
