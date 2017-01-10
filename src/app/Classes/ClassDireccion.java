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
public class ClassDireccion{
    private conection.Mysql mysql;
    private ValidData valid = new ValidData();
    private String usuario,numeroSeguro,direccion,id,id_patient="1";
    private String[] key = {"sector","province","address","when_it","id_user","id_patient"};
   
    private int lineas=10;
    private boolean agregar=true;
    private ArrayList<String> camposEdit = new ArrayList<String>();
    private ArrayList<String> valorEdit = new ArrayList<String>();
    private String NombreTabla = "address";
          private String usuarioID,nombreUsuario,nombreTituloUsuario;

    
    public ClassDireccion(){
        mysql = new Mysql();
    }
    public ClassDireccion(conection.Mysql mysql){
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
    public boolean insert(String usuario,String numeroSeguro,String direccion){
        if(valid.validEmpty(direccion, "Direccion")){
           return false; 
        }else{
           
                if(this.validarUsuario(direccion)){
                    this.usuario = usuario;
                    this.numeroSeguro = numeroSeguro;
                    this.direccion = direccion;

                    boolean respuesta = this.procesarInsert();
                    
                    return respuesta;
                }else{
                    return false;
                }
         }
        
    }
    public boolean validarUsuario(String usuario){
         int existe = this.mysql.getValues(this.NombreTabla, "where address = '"+usuario+"' ");
          if(existe < 1){
            return true;
        }else{
            JOptionPane.showMessageDialog(null, "La direccion ya existe");
            return false;
          }
    }
    public void limpiarTexto(javax.swing.JTextField texto,javax.swing.JTextField texto1,javax.swing.JTextArea texto2){
       texto.setText("");texto1.setText("");texto2.setText("");
    
    }
    public boolean update( String usuario,String numeroSeguro,String direccion, String id){
        boolean respuesta = true;
        if(valid.validEmpty(direccion)){
            if(!this.validarUsuario(direccion)){
                System.out.println("direccón existe");
                respuesta = false;
                return respuesta;
            }else{
               System.out.println("direccion existe");
                this.valorEdit.add(numeroSeguro);
                this.camposEdit.add("province");
                
                this.valorEdit.add(usuario);
                this.camposEdit.add("sector");
                
                this.valorEdit.add(direccion);
                this.camposEdit.add("address");
            }
            this.usuario = usuario;
            this.numeroSeguro = numeroSeguro;
            this.direccion = direccion;
            this.id = id;

            respuesta = this.procesarUpdate();
            
        }
            return respuesta;
       // }
    }
    public boolean procesarInsert(){
        String[] values = {this.usuario,this.numeroSeguro,this.direccion,"now()",this.usuarioID,this.id_patient};
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
        String[] datos = {"id","sector","province","address"};
        String campo = "id_patient = '"+this.id_patient+"' and address";
        Object[][] resultado = (Object[][]) this.mysql.generarSelect(this.NombreTabla, datos,palabraBuscar,campo);
        Object[][] infoTabla= (Object[][]) resultado[0][0];
        DefaultTableModel modelo = new DefaultTableModel(infoTabla,datos);
        JLabelTotal.setText(resultado[0][1]+"");
        table.setModel(modelo); 
    }
    public String[] mostrarEditarUsuario(String id){
        String[] campos = {"id","sector","province","address"};
        String[] respuesta = this.mysql.generarSelect(this.NombreTabla, id,campos);
        this.setAgregar(false);
        return respuesta;
    }
    
    public void cambiarTestoParaEditar(javax.swing.JButton b,javax.swing.JLabel l,javax.swing.JTextField jtf){
        if(this.getAgregar()){
            b.setText("Agregar");
            l.setText("Agregar Dirección");
            //jtf.enable(true);
        }else{
            b.setText("Editar");
            l.setText("Editar Dirección");
            //jtf.enable(false);
        }
    }
    
    public void mostrarDatosTabla(JTable table,JLabel JLabelTotal){
        String[] datos = {"id","sector","province","address"};
        String where = "id_patient = '"+this.id_patient+"' and ";
        Object[][] resultado = (Object[][]) this.mysql.generarSelect(this.NombreTabla, datos,where);
        //Object[][] resultado = (Object[][]) this.mysql.generarSelect("type_of_blood", datos);
        Object[][] infoTabla= (Object[][]) resultado[0][0];
        DefaultTableModel modelo = new DefaultTableModel(infoTabla,datos);
        JLabelTotal.setText(resultado[0][1]+"");
        table.setModel(modelo);
        
    }
    
}
