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
public class ClassCrearPaciente{
    private conection.Mysql mysql;
    private ValidData valid = new ValidData();
    private String nombre,telefono,email, rnc, paginaWeb, direccion,id;
    private String[] key = {"id","name_hospital","rnc","telephone","email","web_page","address"};
    private int id_user;
    private int lineas=10;
    private boolean agregar=true;
    private ArrayList<String> camposEdit = new ArrayList<String>();
    private ArrayList<String> valorEdit = new ArrayList<String>();
            
    public ClassCrearPaciente(){
        mysql = new Mysql();
    }
    public ClassCrearPaciente(conection.Mysql mysql){
       this.mysql =mysql;
    }
    public boolean getAgregar(){
        return agregar;
    }
    
    public void setAgregar(boolean estado){
        this.agregar = estado;
    }
    public boolean elimnar(String id){
        boolean respuesta = this.mysql.deleteRecord("hospital", id);
        return respuesta;
    }
    public boolean insert(String nombre,String telefono,String email,String rnc,String paginaWeb,String direccion){
        if(valid.validEmpty(nombre, "Nombre de la empresa")){
           return false; 
        }else{
                if(this.validarUsuario(nombre)){
                    this.nombre = nombre;
                    this.telefono = telefono;
                    this.email = email;
                    this.rnc = rnc;
                    this.paginaWeb = paginaWeb;
                    this.direccion = direccion;
                    boolean respuesta = this.procesarInsert();
                    return respuesta;
                }else{
                    return false;
                }
         }
        
    }
    public boolean validarUsuario(String usuario){
         int existe = this.mysql.getValues("hospital", "where name_hospital = '"+usuario+"' ");
          if(existe < 1){
            return true;
        }else{
            JOptionPane.showMessageDialog(null, "El hospital ya existe");
            return false;
          }
    }
    public void limpiarTexto(javax.swing.JTextField texto, javax.swing.JTextField texto1, javax.swing.JTextField texto2, javax.swing.JTextField texto3, javax.swing.JTextField texto4, javax.swing.JTextArea texto5){
       texto.setText("");texto1.setText("");texto2.setText("");texto3.setText("");texto4.setText("");texto5.setText("");
    }
    public boolean update( String nombre,String telefono,String email,String rnc,String paginaWeb,String direccion,String id){
        
        if(valid.validEmpty(nombre)){
          this.valorEdit.add(nombre);
          this.camposEdit.add("name_hospital");
        }
        if(valid.validEmpty(telefono)){
          this.valorEdit.add(telefono);
          this.camposEdit.add("telephone");
        }
        if(valid.validEmpty(email)){
          this.valorEdit.add(email);
          this.camposEdit.add("email");
        }
        if(valid.validEmpty(rnc)){
          this.valorEdit.add(rnc);
          this.camposEdit.add("rnc");
        }
        if(valid.validEmpty(paginaWeb)){
          this.valorEdit.add(paginaWeb);
          this.camposEdit.add("web_page");
        }
        if(valid.validEmpty(direccion)){
          this.valorEdit.add(direccion);
          this.camposEdit.add("address");
        }
            this.direccion = direccion;
            this.paginaWeb = paginaWeb;
            this.rnc = rnc;
            this.email = email;
            this.telefono = telefono;
            this.nombre = nombre;
            
            this.id = id;

            boolean respuesta = this.procesarUpdate();
            return respuesta;
       // }
    }
    public boolean procesarInsert(){
        String[] key = {"name_hospital","telephone","email","address","web_page","when_it","rnc","id_user"};
        String[] values = {this.nombre,this.telefono,this.email,this.direccion,this.paginaWeb,"now()",this.rnc,this.id_user+"1"};
        //System.out.println(" key "+this.key+" Values "+values+" total index "+values.length);
        boolean respuesta = mysql.generarInsert(key, values, "hospital");
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
        boolean respuesta = mysql.updateRecord("hospital",this.id,key, values);
        return respuesta;
    }
    
    public void mostrarDatosTabla(JTable table,JLabel JLabelTotal,String palabraBuscar){
        String[] datos = {"id","name_patient","last_patient","sex","document_id","date_of_birth","when_it"};
        String campo = "name_patient";
        System.out.println(palabraBuscar);
        Object[][] resultado = (Object[][]) this.mysql.generarSelect("patient", datos,palabraBuscar,campo);
        Object[][] infoTabla= (Object[][]) resultado[0][0];
        DefaultTableModel modelo = new DefaultTableModel(infoTabla,datos);
        JLabelTotal.setText(resultado[0][1]+"");
        table.setModel(modelo); 
    }
    public String[] mostrarEditarUsuario(String id){
        String[] campos = {"id","name_hospital","rnc","telephone","email","web_page","address"};
        String[] respuesta = this.mysql.generarSelect("hospital", id,campos);
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
        
        String[] datos = {"id","name_patient","last_patient","sex","document_id","date_of_birth","when_it"};

        Object[][] resultado = (Object[][]) this.mysql.generarSelect("patient", datos);
        Object[][] infoTabla= (Object[][]) resultado[0][0];
        DefaultTableModel modelo = new DefaultTableModel(infoTabla,datos);
        JLabelTotal.setText(resultado[0][1]+"");
        table.setModel(modelo);
    }  
}
