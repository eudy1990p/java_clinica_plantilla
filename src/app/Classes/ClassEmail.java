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
public class ClassEmail{
    private conection.Mysql mysql;
    private ValidData valid = new ValidData();
    private String usuario,clave,type_user,id,id_patient="1";
    private String[] key = {"email","when_it","id_user","id_patient"};

    private int lineas=10;
    private boolean agregar=true;
    private ArrayList<String> camposEdit = new ArrayList<String>();
    private ArrayList<String> valorEdit = new ArrayList<String>();
          private String usuarioID,nombreUsuario,nombreTituloUsuario;

            
    public ClassEmail(){
        mysql = new Mysql();
    }
    public ClassEmail(conection.Mysql mysql){
       this.mysql =mysql;
    }
    public boolean getAgregar(){
        return agregar;
    }
    
    public void setAgregar(boolean estado){
        this.agregar = estado;
    }
    public void setId_patient(String id_patient){
        this.id_patient = id_patient;
    }
    public boolean elimnar(String id){
        boolean respuesta = this.mysql.deleteRecord("email", id);
        return respuesta;
    }
    public void setDatosUsuario(String usuarioID, String nombreUsuario,String nombreTituloUsuario){
        this.usuarioID = usuarioID;
        this.nombreUsuario = nombreUsuario;
        this.nombreTituloUsuario = nombreTituloUsuario;
        //JOptionPane.showMessageDialog(null, "Usuario "+this.usuarioID+" "+this.nombreUsuario+" "+this.nombreTituloUsuario);
        
    }
    public boolean insert(String usuario){
        if(valid.validEmpty(usuario, "Email")){
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
         int existe = this.mysql.getValues("email", "where email = '"+usuario+"' ");
          if(existe < 1){
            return true;
        }else{
            JOptionPane.showMessageDialog(null, "El tipo de email ya existe");
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
                System.out.println("Email existe");
                respuesta = false;
                return respuesta;
            }else{
               System.out.println("Email existe");

                this.valorEdit.add(usuario);
                this.camposEdit.add("email");
            }
            this.usuario = usuario;
            this.id = id;

            respuesta = this.procesarUpdate();
            
        }
            return respuesta;
       // }
    }
    public boolean procesarInsert(){
        String[] values = {this.usuario,"now()",this.usuarioID,this.id_patient};
        System.out.println(" key "+this.key+" Values "+values+" total index "+values.length);
        boolean respuesta = mysql.generarInsert(this.key, values, "email");
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
        boolean respuesta = mysql.updateRecord("email",this.id,key, values);
        return respuesta;
    }
    
    public void mostrarDatosTabla(JTable table,JLabel JLabelTotal,String palabraBuscar){
        String[] datos = {"id","email"};
        String campo = "id_patient = '"+this.id_patient+"' and email";
        Object[][] resultado = (Object[][]) this.mysql.generarSelect("email", datos,palabraBuscar,campo);
        Object[][] infoTabla= (Object[][]) resultado[0][0];
        DefaultTableModel modelo = new DefaultTableModel(infoTabla,datos);
        JLabelTotal.setText(resultado[0][1]+"");
        table.setModel(modelo); 
    }
    public String[] mostrarEditarUsuario(String id){
        String[] campos = {"id","email"};
        String[] respuesta = this.mysql.generarSelect("email", id,campos);
        this.setAgregar(false);
        return respuesta;
    }
    
    public void cambiarTestoParaEditar(javax.swing.JButton b,javax.swing.JLabel l,javax.swing.JTextField jtf){
        if(this.getAgregar()){
            b.setText("Agregar");
            l.setText("Agregar Email");
            //jtf.enable(true);
        }else{
            b.setText("Editar");
            l.setText("Editar Email");
            //jtf.enable(false);
        }
    }
    
    public void mostrarDatosTabla(JTable table,JLabel JLabelTotal){
        String[] datos = {"id","email"};
        String where = "id_patient = '"+this.id_patient+"' and ";
        Object[][] resultado = (Object[][]) this.mysql.generarSelect("email", datos,where);
        //Object[][] resultado = (Object[][]) this.mysql.generarSelect("type_of_blood", datos);
        Object[][] infoTabla= (Object[][]) resultado[0][0];
        DefaultTableModel modelo = new DefaultTableModel(infoTabla,datos);
        JLabelTotal.setText(resultado[0][1]+"");
        table.setModel(modelo);
        
    }
    
}
