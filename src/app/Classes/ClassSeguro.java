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
public class ClassSeguro{
    private conection.Mysql mysql;
    private ValidData valid = new ValidData();
    private String usuario,numeroSeguro,type_user,id,id_patient="1";
    private String[] key = {"name_insurance_patient","insurance_number","when_it","id_user","id_patient"};
 
    private int lineas=10;
    private boolean agregar=true;
    private ArrayList<String> camposEdit = new ArrayList<String>();
    private ArrayList<String> valorEdit = new ArrayList<String>();
    private String NombreTabla = "insurance_patient";        
          private String usuarioID,nombreUsuario,nombreTituloUsuario;

    
    public ClassSeguro(){
        mysql = new Mysql();
    }
    public ClassSeguro(conection.Mysql mysql){
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
        boolean respuesta = this.mysql.deleteRecord(this.NombreTabla, id);
        return respuesta;
    }
    public void setDatosUsuario(String usuarioID, String nombreUsuario,String nombreTituloUsuario){
        this.usuarioID = usuarioID;
        this.nombreUsuario = nombreUsuario;
        this.nombreTituloUsuario = nombreTituloUsuario;
        //JOptionPane.showMessageDialog(null, "Usuario "+this.usuarioID+" "+this.nombreUsuario+" "+this.nombreTituloUsuario);
        
    }
    public boolean insert(String usuario,String numeroSeguro){
        if(valid.validEmpty(numeroSeguro, "Numero de seguro")){
           return false; 
        }else{
           
                if(this.validarUsuario(numeroSeguro)){
                    this.usuario = usuario;
                    this.numeroSeguro = numeroSeguro;
                    boolean respuesta = this.procesarInsert();
                    
                    return respuesta;
                }else{
                    return false;
                }
         }
        
    }
    public boolean validarUsuario(String usuario){
         int existe = this.mysql.getValues(this.NombreTabla, "where insurance_number = '"+usuario+"' ");
          if(existe < 1){
            return true;
        }else{
            JOptionPane.showMessageDialog(null, "El numero de seguro ya existe");
            return false;
          }
    }
    public void limpiarTexto(javax.swing.JTextField texto,javax.swing.JTextField texto1){
       texto.setText("");texto1.setText("");
    
    }
    public boolean update( String usuario,String numeroSeguro, String id){
        boolean respuesta = true;
        if(valid.validEmpty(numeroSeguro)){
            if(!this.validarUsuario(numeroSeguro)){
                System.out.println("numero Seguro existe");
                respuesta = false;
                return respuesta;
            }else{
               System.out.println("numero Seguro existe");
                this.valorEdit.add(numeroSeguro);
                this.camposEdit.add("insurance_number");
                
                this.valorEdit.add(usuario);
                this.camposEdit.add("name_insurance_patient");
            }
            this.usuario = usuario;
            this.numeroSeguro = numeroSeguro;
            this.id = id;

            respuesta = this.procesarUpdate();
            
        }
            return respuesta;
       // }
    }
    public boolean procesarInsert(){
        String[] values = {this.usuario,this.numeroSeguro,"now()",this.usuarioID,this.id_patient};
        System.out.println(" key "+this.key+" Values "+values+" total index "+values.length);
        boolean respuesta = mysql.generarInsert(this.key, values, this.NombreTabla);
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
        boolean respuesta = mysql.updateRecord(this.NombreTabla,this.id,key, values);
        return respuesta;
    }
    
    public void mostrarDatosTabla(JTable table,JLabel JLabelTotal,String palabraBuscar){
        String[] datos = {"id","name_insurance_patient","insurance_number"};
        String campo = "id_patient = '"+this.id_patient+"' and name_insurance_patient";
        Object[][] resultado = (Object[][]) this.mysql.generarSelect(this.NombreTabla, datos,palabraBuscar,campo);
        Object[][] infoTabla= (Object[][]) resultado[0][0];
        DefaultTableModel modelo = new DefaultTableModel(infoTabla,datos);
        JLabelTotal.setText(resultado[0][1]+"");
        table.setModel(modelo); 
    }
    public String[] mostrarEditarUsuario(String id){
        String[] campos = {"id","name_insurance_patient","insurance_number"};
        String[] respuesta = this.mysql.generarSelect(this.NombreTabla, id,campos);
        this.setAgregar(false);
        return respuesta;
    }
    
    public void cambiarTestoParaEditar(javax.swing.JButton b,javax.swing.JLabel l,javax.swing.JTextField jtf){
        if(this.getAgregar()){
            b.setText("Agregar");
            l.setText("Agregar Seguro");
            //jtf.enable(true);
        }else{
            b.setText("Editar");
            l.setText("Editar Seguro");
            //jtf.enable(false);
        }
    }
    
    public void mostrarDatosTabla(JTable table,JLabel JLabelTotal){
        String[] datos = {"id","name_insurance_patient","insurance_number"};
        String where = "id_patient = '"+this.id_patient+"' and ";
        Object[][] resultado = (Object[][]) this.mysql.generarSelect(this.NombreTabla, datos,where);
        //Object[][] resultado = (Object[][]) this.mysql.generarSelect("type_of_blood", datos);
        Object[][] infoTabla= (Object[][]) resultado[0][0];
        DefaultTableModel modelo = new DefaultTableModel(infoTabla,datos);
        JLabelTotal.setText(resultado[0][1]+"");
        table.setModel(modelo);
        
    }
    
}
