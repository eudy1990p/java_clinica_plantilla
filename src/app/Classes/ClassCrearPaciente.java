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
    private  String nombre,apellido, telefono, email, cedula, fecha_nacimiento, sexo, tipo_sangre_id, provincia, sector, direccion,id;
    private String[] key = {"id","name_hospital","rnc","telephone","email","web_page","address"};
    private int id_user;
    private int lineas=10;
    private boolean agregar=true;
    private ArrayList<String> camposEdit = new ArrayList<String>();
    private ArrayList<String> valorEdit = new ArrayList<String>();
    private String[] idTipoSangre;
    
    public ClassCrearPaciente(){
        mysql = new Mysql();
    }
    public ClassCrearPaciente(conection.Mysql mysql){
       this.mysql =mysql;
    }
    public boolean getAgregar(){
        return agregar;
    }
    public String getIdTipoSangre(int index){
        return this.idTipoSangre[index];
    }
    public void llenarComboBox(javax.swing.JComboBox JCTipoSangre){
            String[] campos = {"id","name_of_blood"};
           Object[][] resultado = (Object[][])  this.mysql.generarSelect("type_of_blood",campos,"name_of_blood","asc","");
          Object[][] infoTabla= (Object[][]) resultado[0][0];
          System.out.println(infoTabla.length);
          System.out.println(infoTabla[1].length);
          
          this.idTipoSangre = new String[infoTabla.length];
          for(int i = 0 ; i < infoTabla.length ; i++){
                JCTipoSangre.addItem(infoTabla[i][1]);
                idTipoSangre[i]= infoTabla[i][0].toString();
                System.out.println(infoTabla[i][0]+" "+infoTabla[i][1]);
          }
    }
    
    public void setAgregar(boolean estado){
        this.agregar = estado;
    }
    public boolean elimnar(String id){
        boolean respuesta = this.mysql.deleteRecord("hospital", id);
        return respuesta;
    }
    public void setValoresAtributos(String nombre,String apellido,String telefono,String email,String cedula,String fecha_nacimiento,String sexo,String tipo_sangre_id,String provincia,String sector,String direccion){
                    this.fecha_nacimiento = fecha_nacimiento;
                     this.cedula = cedula;
                     this.nombre = nombre;
                     this.sexo = sexo;
                     this.tipo_sangre_id = tipo_sangre_id;
                     this.provincia = provincia;
                     this.sector = sector;
                     this.apellido = apellido;
                    this.telefono = telefono;
                    this.email = email;
                    this.direccion = direccion;
    }
    public void setValoresAtributos(String nombre,String apellido,String telefono,String email,String cedula,String fecha_nacimiento,String sexo,String tipo_sangre_id,String provincia,String sector,String direccion,String id){
                    this.fecha_nacimiento = fecha_nacimiento;
                     this.cedula = cedula;
                     this.nombre = nombre;
                     this.sexo = sexo;
                     this.tipo_sangre_id = tipo_sangre_id;
                     this.provincia = provincia;
                     this.sector = sector;
                     this.apellido = apellido;
                    this.telefono = telefono;
                    this.email = email;
                    this.direccion = direccion;
                    this.id = id;
    }
    public boolean insert(){
        if(this.valid.validEmpty(this.nombre, "Nombre de la empresa")){
           return false; 
        }else{
            if(this.valid.validEmpty(this.cedula)){
                if(!this.validarCedula(this.cedula)){
                    return false;
                }
            }
                    boolean respuesta = this.procesarInsert();
                    return respuesta;
               
         }
        
    }
    public boolean validarCedula(String cedula){
         int existe = this.mysql.getValues("patient", "where document_id = '"+cedula+"' ");
          if(existe < 1){
            return true;
        }else{
            JOptionPane.showMessageDialog(null, "La cedula ya existe");
            return false;
          }
    }
    public void limpiarTexto(javax.swing.JTextField texto, javax.swing.JTextField texto1, javax.swing.JTextField texto2, javax.swing.JTextField texto3, javax.swing.JTextField texto4, javax.swing.JTextField texto5, javax.swing.JTextField texto6, javax.swing.JTextField texto7, javax.swing.JTextField texto8, javax.swing.JComboBox texto9, javax.swing.JComboBox texto10){
       texto.setText("");texto1.setText("");texto2.setText("");texto3.setText("");texto4.setText("");texto5.setText("");
       texto.setText("");texto6.setText("");texto7.setText("");texto8.setText("");texto9.setSelectedIndex(0);texto10.setSelectedIndex(0);

    }
    public boolean update(){
        
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
    public String[] mostrarEditarPaciente(String id){
        String[] campos = {"id","name_patient","last_patient","document_id","date_of_birth","sex"};
        String[] respuesta = this.mysql.generarSelect("patient", id,campos);
        this.setAgregar(false);
        return respuesta;
    }
    
    public String[] mostrarEditarDireccion(String id){
        String[] campos = {"id","sector","province","address"};
        String[] respuesta = this.mysql.generarSelect("address","id_patient", id,campos,"id","desc");
        this.setAgregar(false);
        return respuesta;
    }
    public String[] mostrarEditarTelefono(String id){
        String[] campos = {"id","telephone"};
        String[] respuesta = this.mysql.generarSelect("telephone","id_patient", id,campos,"id","desc");
        this.setAgregar(false);
        return respuesta;
    }
    
    public String[] mostrarEditarEmail(String id){
        String[] campos = {"id","email"};
        String[] respuesta = this.mysql.generarSelect("email","id_patient", id,campos,"id","desc");
        this.setAgregar(false);
        return respuesta;
    }
    
    public String[] mostrarEditarSeguroPaciente(String id){
        String[] campos = {"id","name_insurance_patient","insurance_number"};
        String[] respuesta = this.mysql.generarSelect("insurance_patient","id_patient", id,campos,"id","desc");
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
