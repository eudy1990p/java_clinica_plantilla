/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.Classes;

import app.jframes.JFrameCrearSonografia;
import conection.Mysql;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.lang.Object;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.JTabbedPane;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Eudy
 */
public class ClassGenerarReporte{
    private conection.Mysql mysql;
    private ValidData valid = new ValidData();
    private  String id_paciente,nombre,apellido, telefonoCasa, telefonoCelular, telefonoTrabajo, email1, email2, cedula, fecha_nacimiento, sexo, tipo_sangre_id, provincia, sector, direccion,id,nombreSeguro,numeroSeguro;
    private String[] key = {"id","name_patient","last_patient","document_id","date_of_birth","sex","type_of_blood_id"};

    private int lineas=10;
    private boolean agregar=true;
    private ArrayList<String> camposEdit;
    private ArrayList<String> valorEdit;
    private String[] idTipoSangre,idTipoSOnografia,idMedico,idHospital,Montos = {"","","",""};
    
    private String HospitalID,totaRegistro;
    
    private String tituloSubTotal="",tituloDescuento="",tituloImpuesto="",tituloTotal="";
    
    private List<modelo10Columna> CuerpoReporte = new LinkedList();

    public String[] getMontos() {
        return Montos;
    }
    private String usuarioID,nombreUsuario,nombreTituloUsuario;
    private String subTotal,impuesto,descuento,totalMonto;
    
    private javax.swing.JTabbedPane jTabbedPane1;

    public void setjTabbedPane1(JTabbedPane jTabbedPane1) {
        this.jTabbedPane1 = jTabbedPane1;
    }
    private JLabel total;

    public void setTotal(JLabel total) {
        this.total = total;
    }

    public void setjTable1(JTable jTable1) {
        this.jTable1 = jTable1;
    }
    private javax.swing.JTable jTable1;
    
    public ClassGenerarReporte(){
        mysql = new Mysql();
    }
    public ClassGenerarReporte(conection.Mysql mysql){
       this.mysql =mysql;
    }
    public boolean getAgregar(){
        return agregar;
    }
    public String getIdTipoSangre(int index){
        return this.idTipoSangre[index];
    }
    public String getIdTipoSonografia(int index){
        return this.idTipoSOnografia[index];
    }
    public String getIdMedico(int index){
        return this.idMedico[index];
    }
    public String getIdHospital(int index){
        return this.idHospital[index];
    }
    public void setDatosUsuario(String usuarioID, String nombreUsuario,String nombreTituloUsuario){
        this.usuarioID = usuarioID;
        this.nombreUsuario = nombreUsuario;
        this.nombreTituloUsuario = nombreTituloUsuario;
        //JOptionPane.showMessageDialog(null, "Usuario "+this.usuarioID+" "+this.nombreUsuario+" "+this.nombreTituloUsuario);
        
    }
    public void cargarVistaSonografia(String idSonografia){
    
    }
     public void llenarComboBoxSexo(javax.swing.JComboBox JCTipoSangre){
           // String[] campos = {"id","name_hospital"};
            
            //Object[][] resultado = (Object[][])  this.mysql.generarSelect("hospital",campos,"name_hospital","asc"," ");
           // Object[][] infoTabla= (Object[][]) resultado[0][0];
          //System.out.println(infoTabla.length);
          //System.out.println(infoTabla[1].length);
          
          String[] infoTabla = {"","masculino","femenino"};
          for(int i = 0 ; i < infoTabla.length ; i++){
                JCTipoSangre.addItem(infoTabla[i]);
                //this.idTipoSOnografia[i]= infoTabla[i][0].toString();
               // System.out.println(infoTabla[i][0]+" "+infoTabla[i][1]);
          }
    }
    public void llenarComboBoxEstadoSonografia(javax.swing.JComboBox JCTipoSangre){
           // String[] campos = {"id","name_hospital"};
            
            //Object[][] resultado = (Object[][])  this.mysql.generarSelect("hospital",campos,"name_hospital","asc"," ");
           // Object[][] infoTabla= (Object[][]) resultado[0][0];
          //System.out.println(infoTabla.length);
          //System.out.println(infoTabla[1].length);
          
          String[] infoTabla = {"","Pendiente_de_entrega","Entregado"};
          for(int i = 0 ; i < infoTabla.length ; i++){
                JCTipoSangre.addItem(infoTabla[i]);
                //this.idTipoSOnografia[i]= infoTabla[i][0].toString();
               // System.out.println(infoTabla[i][0]+" "+infoTabla[i][1]);
          }
    }
    public void llenarComboBoxCondicionSonografia(javax.swing.JComboBox JCTipoSangre){
           // String[] campos = {"id","name_hospital"};
            
            //Object[][] resultado = (Object[][])  this.mysql.generarSelect("hospital",campos,"name_hospital","asc"," ");
           // Object[][] infoTabla= (Object[][]) resultado[0][0];
          //System.out.println(infoTabla.length);
          //System.out.println(infoTabla[1].length);
          
          String[] infoTabla = {"","normal","cuidado","grave"};
          for(int i = 0 ; i < infoTabla.length ; i++){
                JCTipoSangre.addItem(infoTabla[i]);
                //this.idTipoSOnografia[i]= infoTabla[i][0].toString();
               // System.out.println(infoTabla[i][0]+" "+infoTabla[i][1]);
          }
    }
    public void llenarComboBoxHospital(javax.swing.JComboBox JCTipoSangre){
            String[] campos = {"id","name_hospital"};
            
            Object[][] resultado = (Object[][])  this.mysql.generarSelect("hospital",campos,"name_hospital","asc"," ");
            Object[][] infoTabla= (Object[][]) resultado[0][0];
          //System.out.println(infoTabla.length);
          //System.out.println(infoTabla[1].length);
          
          this.idHospital = new String[infoTabla.length];
          JCTipoSangre.addItem("");
          for(int i = 0 ; i < infoTabla.length ; i++){
                JCTipoSangre.addItem(infoTabla[i][1]);
                this.idHospital[i]= infoTabla[i][0].toString();
               // System.out.println(infoTabla[i][0]+" "+infoTabla[i][1]);
          }
    }
    public void llenarComboBoxMedico(javax.swing.JComboBox JCTipoSangre){
            String[] campos = {"id","nombre_titulo"};
            
            Object[][] resultado = (Object[][])  this.mysql.generarSelect("users",campos,"nombre_titulo","asc"," and (type_of_user = 'medico' ) ");
            Object[][] infoTabla= (Object[][]) resultado[0][0];
          //System.out.println(infoTabla.length);
          //System.out.println(infoTabla[1].length);
          
          this.idMedico = new String[infoTabla.length];
          JCTipoSangre.addItem("");
          for(int i = 0 ; i < infoTabla.length ; i++){
                JCTipoSangre.addItem(infoTabla[i][1]);
                this.idMedico[i]= infoTabla[i][0].toString();
               // System.out.println(infoTabla[i][0]+" "+infoTabla[i][1]);
          }
    }
    public void llenarComboBoxTipoSonografia(javax.swing.JList JCTipoSangre){
            String[] campos = {"id","name_of_type_sonography"};
            
            Object[][] resultado = (Object[][])  this.mysql.generarSelect("type_of_sonography",campos,"name_of_type_sonography","asc","");
            Object[][] infoTabla= (Object[][]) resultado[0][0];
          //System.out.println(infoTabla.length);
          //System.out.println(infoTabla[1].length);
          
          this.idTipoSOnografia = new String[infoTabla.length];
          String[] a  = new String[infoTabla.length];
          //JCTipoSangre.set;
          for(int i = 0 ; i < infoTabla.length ; i++){
                 a[i] = infoTabla[i][1].toString();
                //JCTipoSangre.addItem(infoTabla[i][1]);
                this.idTipoSOnografia[i]= infoTabla[i][0].toString();
               // System.out.println(infoTabla[i][0]+" "+infoTabla[i][1]);
          }
          JCTipoSangre.setListData(a);
    }
    public void llenarComboBoxTipoSangre(javax.swing.JComboBox JCTipoSangre){
            String[] campos = {"id","name_of_blood"};
            
            Object[][] resultado = (Object[][])  this.mysql.generarSelect("type_of_blood",campos,"name_of_blood","asc","");
            Object[][] infoTabla= (Object[][]) resultado[0][0];
          //System.out.println(infoTabla.length);
          //System.out.println(infoTabla[1].length);
          
          this.idTipoSangre = new String[infoTabla.length];
          JCTipoSangre.addItem("");
          for(int i = 0 ; i < infoTabla.length ; i++){
                JCTipoSangre.addItem(infoTabla[i][1]);
                idTipoSangre[i]= infoTabla[i][0].toString();
               // System.out.println(infoTabla[i][0]+" "+infoTabla[i][1]);
          }
    }
    public void llenarComboBox(javax.swing.JComboBox JCTipoSangre,String t){
            String[] campos = {"id","name_of_blood"};
            
            Object[][] resultado = (Object[][])  this.mysql.generarSelect("type_of_blood",campos,"name_of_blood","asc","");
            Object[][] infoTabla= (Object[][]) resultado[0][0];
          //System.out.println(infoTabla.length);
          //System.out.println(infoTabla[1].length);
          
          this.idTipoSangre = new String[infoTabla.length];
          
          JCTipoSangre.addItem(t);
          for(int i = 0 ; i < infoTabla.length ; i++){
                JCTipoSangre.addItem(infoTabla[i][1]);
                idTipoSangre[i]= infoTabla[i][0].toString();
               // System.out.println(infoTabla[i][0]+" "+infoTabla[i][1]);
          }
    }
    public void setAgregar(boolean estado){
        this.agregar = estado;
    }
    public boolean elimnar(String id){
        boolean respuesta = this.mysql.deleteRecord("patient", id);
        return respuesta;
    }
    
    public void setValoresAtributos(String nombre,String apellido,String telefonoCasa,String telefonoCelular,String telefonoTrabajo,String email1,String email2,String cedula,String fecha_nacimiento,String sexo,String tipo_sangre_id,String provincia,String sector,String direccion,String nombreSeguro,String numeroSeguro){
                    this.fecha_nacimiento = fecha_nacimiento;
                     this.cedula = cedula;
                     this.nombre = nombre;
                     this.sexo = sexo;
                     this.tipo_sangre_id = tipo_sangre_id;
                     this.provincia = provincia;
                     this.sector = sector;
                     this.apellido = apellido;
                    this.telefonoCasa = telefonoCasa;
                    this.telefonoCelular = telefonoCelular;
                    this.telefonoTrabajo = telefonoTrabajo;
                    this.nombreSeguro = nombreSeguro;
                    this.numeroSeguro = numeroSeguro;
                    this.email1 = email1;                    
                    this.email2 = email2;

                    this.direccion = direccion;
    }
    public void setValoresAtributos(String nombre,String apellido,String telefonoCasa,String telefonoCelular,String telefonoTrabajo,String email1,String email2,String cedula,String fecha_nacimiento,String sexo,String tipo_sangre_id,String provincia,String sector,String direccion,String nombreSeguro,String numeroSeguro,String id){
                    this.fecha_nacimiento = fecha_nacimiento;
                     this.cedula = cedula;
                     this.nombre = nombre;
                     this.sexo = sexo;
                     this.tipo_sangre_id = tipo_sangre_id;
                     this.provincia = provincia;
                     this.sector = sector;
                     this.apellido = apellido;
                    this.telefonoCasa = telefonoCasa;
                    this.telefonoCelular = telefonoCelular;
                    this.telefonoTrabajo = telefonoTrabajo;
                    this.nombreSeguro = nombreSeguro;
                    this.numeroSeguro = numeroSeguro;
                    this.email1 = email1;                    
                    this.email2 = email2;
                    this.id = id;
                    this.direccion = direccion;
    }
    public boolean insert(){
        this.variable();
        if(this.valid.validEmpty(this.nombre, "Nombre de la empresa")){
           return false; 
        }else{
            System.out.println(this.cedula);
            if(this.valid.validEmpty(this.cedula)){
                if(!this.validarCedula(this.cedula)){
                    return false;
                }
            }
            System.out.println(nombreSeguro+" "+numeroSeguro);
            if( this.valid.validEmpty(nombreSeguro)  || this.valid.validEmpty(numeroSeguro)  ){
                if( this.valid.validEmpty(nombreSeguro,"Nombre del seguro necesitar estar lleno para agregar el seguro pero") ){
                    return false;
                }
                if(this.valid.validEmpty(numeroSeguro,"Numero del seguro necesitar estar lleno para agregar el seguro pero")){
                    return false;
                }
            }
            if( !this.valid.validEmpty(this.fecha_nacimiento)){
                java.util.Date fecha = new java.util.Date();
                this.fecha_nacimiento = fecha.getYear()+"-"+fecha.getMonth()+"-"+fecha.getDay();
            }
                    boolean respuesta = this.procesarInsertPaciente();
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
    public void limpiarTexto(javax.swing.JTextField texto, javax.swing.JTextField texto1, javax.swing.JTextField texto2, javax.swing.JTextField texto3, javax.swing.JTextField texto4, javax.swing.JTextField texto5, javax.swing.JTextField texto6, javax.swing.JTextField texto7, javax.swing.JTextField texto8, javax.swing.JComboBox texto9, javax.swing.JComboBox texto10, javax.swing.JTextField texto11, javax.swing.JTextField texto12,com.toedter.calendar.JDateChooser texto13, javax.swing.JTextField texto14, javax.swing.JTextField texto15){
       texto.setText("");texto1.setText("");texto2.setText("");texto3.setText("");texto4.setText("");texto5.setText("");
       texto.setText("");texto6.setText("");texto7.setText("");texto8.setText("");texto9.setSelectedIndex(0);texto10.setSelectedIndex(0);

    }
    public boolean updatePaciente(){
       this.valorEdit = new ArrayList<String>();
       this.camposEdit = new ArrayList<String>();
      boolean respuesta = false;

       if( (valid.validEmpty(nombre)) || (valid.validEmpty(apellido)) || (valid.validEmpty(cedula)) || (valid.validEmpty(fecha_nacimiento))
               || (valid.validEmpty(sexo)) || (valid.validEmpty(tipo_sangre_id)) ){
        if(valid.validEmpty(nombre)){
          this.valorEdit.add(nombre);
          this.camposEdit.add("name_patient");
        }
        if(valid.validEmpty(apellido)){
          this.valorEdit.add(apellido);
          this.camposEdit.add("last_patient");
        }
         if(valid.validEmpty(cedula)){
          this.valorEdit.add(cedula);
          this.camposEdit.add("document_id");
        }
         if(valid.validEmpty(fecha_nacimiento)){
          this.valorEdit.add(fecha_nacimiento);
          this.camposEdit.add("date_of_birth");
        }
         if(valid.validEmpty(sexo)){
          this.valorEdit.add(sexo);
          this.camposEdit.add("sex");
        }
        if(valid.validEmpty(tipo_sangre_id)){
          this.valorEdit.add(tipo_sangre_id);
          this.camposEdit.add("type_of_blood_id");
        }
         this.procesarUpdatePaciente();
         respuesta = true;

       }
       return respuesta;

    }
    public boolean updatePacienteTelefono(){
        this.valorEdit = new ArrayList<String>();
       this.camposEdit = new ArrayList<String>();      
       boolean respuesta = false;
       int total = 0;
       if(valid.validEmpty(this.telefonoCasa) || valid.validEmpty(this.telefonoCelular) || valid.validEmpty(this.telefonoTrabajo)){
            if(valid.validEmpty(this.telefonoCasa)){
              this.valorEdit.add(this.telefonoCasa);
              this.camposEdit.add("telephone");
              total++;
            }
            if(valid.validEmpty(this.telefonoCelular)){
              this.valorEdit.add(this.telefonoCelular);
              this.camposEdit.add("telephone");
              total++;
            }
            if(valid.validEmpty(this.telefonoTrabajo)){
              this.valorEdit.add(this.telefonoTrabajo);
              this.camposEdit.add("telephone");
              total++;
            }
            this.procesarUpdatePacienteTelefono();
            respuesta = true;

       }  
      return respuesta;

    }
    public boolean updatePacienteEmail(){
        this.valorEdit = new ArrayList<String>();
       this.camposEdit = new ArrayList<String>();
       boolean respuesta = false;
       int total = 0;
        if(valid.validEmpty(email1) || valid.validEmpty(email2)){
            if(valid.validEmpty(email1)){
            this.valorEdit.add(email1);
            this.camposEdit.add("email");
            total++;
            }
            if(valid.validEmpty(email2)){
            this.valorEdit.add(email2);
            this.camposEdit.add("email");
            total++;
            }
            this.procesarUpdatePacienteEmail();
            respuesta = true;
        }
         return respuesta;
    }
    public boolean updatePacienteDireccion(){
        this.valorEdit = new ArrayList<String>();
        this.camposEdit = new ArrayList<String>();
        boolean respuesta = false;
        if(valid.validEmpty(provincia)){
            if(valid.validEmpty(provincia)){
              this.valorEdit.add(provincia);
              this.camposEdit.add("province");
            }  
            if(valid.validEmpty(sector)){
              this.valorEdit.add(sector);
              this.camposEdit.add("sector");
            }  
            if(valid.validEmpty(direccion)){
              this.valorEdit.add(direccion);
              this.camposEdit.add("address");
            }
            this.procesarUpdatePacienteDireccion();
            respuesta = true;
        }
        return respuesta;
    }
    public boolean insertPacienteSeguro(){
       boolean respuesta = false;
        if(( valid.validEmpty(nombreSeguro) ) || (valid.validEmpty(numeroSeguro))  ){
          if( valid.validEmpty(nombreSeguro,"Nombre del seguro") ){
              return false;
          }
          if(valid.validEmpty(numeroSeguro,"Numero del seguro")){
              return false;
          }
          this.procesarInsertPacienteSeguro();
          respuesta = true;
        }
        return respuesta;
    }
    public boolean procesarInsertPacienteSeguro(){
        String[] key = {"insurance_number","name_insurance_patient","when_it","id_user","id_patient"};
        String[] values = {this.numeroSeguro,this.nombreSeguro,"now()",this.usuarioID,this.id_paciente};
        //System.out.println(" key "+this.key+" Values "+values+" total index "+values.length);
        boolean respuesta = mysql.generarInsert(key, values, "insurance_patient");
        return respuesta;
    }
    public boolean insertPacienteDireccion(){
        boolean respuesta = false;
        if(valid.validEmpty(provincia) || valid.validEmpty(sector) || valid.validEmpty(direccion)){
            
            this.procesarInsertPacienteDireccion();
            respuesta = true;
        }
        return respuesta;
    }
    public boolean procesarInsertPacienteDireccion(){
        String[] key = {"sector","province","address","when_it","id_user","id_patient"};
        String[] values = {this.sector,this.provincia,this.direccion,"now()",this.usuarioID,this.id_paciente};
        //System.out.println(" key "+this.key+" Values "+values+" total index "+values.length);
        boolean respuesta = mysql.generarInsert(key, values, "address");
        return respuesta;
    }
    public boolean updatePacienteSeguro(){
        this.valorEdit = new ArrayList<String>();
       this.camposEdit = new ArrayList<String>();
       boolean respuesta = false;
        if(( valid.validEmpty(nombreSeguro) ) || (valid.validEmpty(numeroSeguro))  ){
          if( valid.validEmpty(nombreSeguro) ){
             this.valorEdit.add(nombreSeguro);
             this.camposEdit.add("name_insurance_patient");
          }
          if(valid.validEmpty(numeroSeguro)){
            this.valorEdit.add(numeroSeguro);
            this.camposEdit.add("insurance_number");
          }
          this.procesarUpdatePacienteSeguro();
          respuesta = true;
        }
        return respuesta;
    }
    public boolean update(){
        boolean respuesta = false;
        if(this.updatePaciente() || this.updatePacienteDireccion() || this.updatePacienteEmail() || this.updatePacienteSeguro() || this.updatePacienteTelefono())
        {
            respuesta = true;
        }
        
        return respuesta;
       // }
    }
    public boolean procesarInsertPaciente(){
       //String[] key = {"name_patient","last_patient","document_id","date_of_birth","sex","type_of_blood_id","when_it","id_user"};

        String[] key = {"name_patient","last_patient","document_id","sex","type_of_blood_id","when_it","id_user","date_of_birth"};
        System.out.println(this.fecha_nacimiento);
        String[] values = {this.nombre,this.apellido,this.cedula,this.sexo.toLowerCase(),this.tipo_sangre_id,"now()",this.usuarioID,this.fecha_nacimiento};
        
        this.id_paciente = mysql.generarInsertWithGetLastID(key, values, "patient");
        System.out.println("id Paciente "+ this.id_paciente);
        this.insertPacienteDireccion();
        this.insertPacienteSeguro();
        this.insertPacienteEmail();
        this.insertPacienteTelefono();
        boolean respuesta = true;
        
        return respuesta;
    }
    
    public String procesarInsertPaciente(String nombre,String apellido,String cedula,String sexo ,String tipo_sangre_id,String fecha_nacimiento){
        String[] key = {"name_patient","last_patient","document_id","sex","type_of_blood_id","when_it","id_user","date_of_birth"};
        String[] values = {nombre,apellido,cedula,sexo.toLowerCase(),tipo_sangre_id,"now()",this.usuarioID,fecha_nacimiento};
        this.id_paciente = mysql.generarInsertWithGetLastID(key, values, "patient");
        return this.id_paciente;
    }
    public boolean insertPacienteTelefono(){
        this.valorEdit = new ArrayList<String>();
       this.camposEdit = new ArrayList<String>();      
       boolean respuesta = false;
      ArrayList<String> telefonos = new ArrayList<String>();

       if(valid.validEmpty(this.telefonoCasa) || valid.validEmpty(this.telefonoCelular) || valid.validEmpty(this.telefonoTrabajo)){
            if(valid.validEmpty(this.telefonoCasa)){
              this.valorEdit.add(this.telefonoCasa);
              this.camposEdit.add("telephone");
             telefonos.add(this.telefonoCasa);
            }
            if(valid.validEmpty(this.telefonoCelular)){
              this.valorEdit.add(this.telefonoCelular);
              this.camposEdit.add("telephone");
             telefonos.add(this.telefonoCelular);
            }
            if(valid.validEmpty(this.telefonoTrabajo)){
              this.valorEdit.add(this.telefonoTrabajo);
              this.camposEdit.add("telephone");
             telefonos.add(this.telefonoTrabajo);
            }
             for(int i = 0 ; i < telefonos.size() ; i++){
                this.procesarInsertPacienteTelefono(telefonos.get(i));
            }
            respuesta = true;

       }  
      return respuesta;

    }
    public boolean procesarInsertPacienteTelefono(String telefono){
        String[] key = {"telephone","when_it","id_patient","id_user"};
        String[] values = {telefono,"now()",this.id_paciente,this.usuarioID};
        //System.out.println(" key "+this.key+" Values "+values+" total index "+values.length);
        boolean respuesta = mysql.generarInsert(key, values, "telephone");
        return respuesta;
    }
     public boolean insertPacienteEmail(){
        this.valorEdit = new ArrayList<String>();
       this.camposEdit = new ArrayList<String>();
       boolean respuesta = false;
       ArrayList<String> emails = new ArrayList<String>();
        if(valid.validEmpty(email1) || valid.validEmpty(email2)){
            if(valid.validEmpty(email1)){
            this.valorEdit.add(email1);
            this.camposEdit.add("email");
            emails.add(email1);
            }
            if(valid.validEmpty(email2)){
            this.valorEdit.add(email2);
            this.camposEdit.add("email");
            emails.add(email2);
            }
            
            for(int i = 0 ; i < emails.size() ; i++){
                this.procesarInsertPacienteEmail(emails.get(i));
            }
            respuesta = true;
        }
         return respuesta;
    }
    public boolean procesarInsertPacienteEmail(String email){
        String[] key = {"email","when_it","id_patient","id_user"};
        String[] values = {email,"now()",this.id_paciente,this.usuarioID};
        //System.out.println(" key "+this.key+" Values "+values+" total index "+values.length);
        boolean respuesta = mysql.generarInsert(key, values, "email");
        return respuesta;
    }
    
    public boolean procesarUpdatePaciente(){
         int total =this.camposEdit.size();
         String[] key = new String[total];
        String[] values =new String[total];

        for(int i = 0 ; i <  this.camposEdit.size() ; i++){
            key[i] =  this.camposEdit.get(i)/*{"password_user","type_of_user"}*/;
            values[i] = this.valorEdit.get(i);
        }
        System.out.println(" key "+this.key+" Values "+values+" total index "+values.length);
        boolean respuesta = mysql.updateRecord("patient",this.id,key, values);
        return respuesta;
    }
    
    public boolean procesarUpdatePacienteTelefono(){
         int total =this.camposEdit.size();
         String[] key = new String[total];
        String[] values =new String[total];

        for(int i = 0 ; i <  this.camposEdit.size() ; i++){
            key[i] =  this.camposEdit.get(i)/*{"password_user","type_of_user"}*/;
            values[i] = this.valorEdit.get(i);
        }
        System.out.println(" key "+this.key+" Values "+values+" total index "+values.length);
        String id = "(select max(id) from telephone where id_patient = '"+this.id+"' )";
        boolean respuesta = mysql.updateRecord("telephone","id",this.id,key, values);
        return respuesta;
    }
    
     public boolean procesarUpdatePacienteEmail(){
         int total =this.camposEdit.size();
         String[] key = new String[total];
        String[] values =new String[total];

        for(int i = 0 ; i <  this.camposEdit.size() ; i++){
            key[i] =  this.camposEdit.get(i)/*{"password_user","type_of_user"}*/;
            values[i] = this.valorEdit.get(i);
        }
        System.out.println(" key "+this.key+" Values "+values+" total index "+values.length);
        String id = "(select max(id) from email where id_patient = '"+this.id+"' )";
        boolean respuesta = mysql.updateRecord("email","id",this.id,key, values);
        return respuesta;
    }
     
      public boolean procesarUpdatePacienteDireccion(){
         int total =this.camposEdit.size();
         String[] key = new String[total];
        String[] values =new String[total];

        for(int i = 0 ; i <  this.camposEdit.size() ; i++){
            key[i] =  this.camposEdit.get(i)/*{"password_user","type_of_user"}*/;
            values[i] = this.valorEdit.get(i);
        }
        System.out.println(" key "+this.key+" Values "+values+" total index "+values.length);
        String id = "(select max(id) from address where id_patient = '"+this.id+"' )";
        boolean respuesta = mysql.updateRecord("address","id",this.id,key, values);
        return respuesta;
    }
    
       public boolean procesarUpdatePacienteSeguro(){
         int total =this.camposEdit.size();
         String[] key = new String[total];
        String[] values =new String[total];

        for(int i = 0 ; i <  this.camposEdit.size() ; i++){
            key[i] =  this.camposEdit.get(i)/*{"password_user","type_of_user"}*/;
            values[i] = this.valorEdit.get(i);
        }
        System.out.println(" key "+this.key+" Values "+values+" total index "+values.length);
        String id = "(select max(id) from insurance_patient where id_patient = '"+this.id+"' )";
        boolean respuesta = mysql.updateRecord("insurance_patient","id",this.id,key, values);
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
            //l.setText("Agregar Usuario");
            //jtf.enable(true);
        }else{
            b.setText("Editar");
            //l.setText("Editar Usuario");
            //jtf.enable(false);
        }
    }
    
    public void mostrarDatosTabla(JTable table,JLabel JLabelTotal){
        
       //problemas al capturar la fecha de la db
        String[] datos = {"id","name_patient","last_patient","sex","document_id"};

        Object[][] resultado = (Object[][]) this.mysql.generarSelect("patient", datos);
        //System.out.println(resultado);
        Object[][] infoTabla= (Object[][]) resultado[0][0];
        DefaultTableModel modelo = new DefaultTableModel(infoTabla,datos);
        JLabelTotal.setText(resultado[0][1]+"");
        table.setModel(modelo);
    }  
    public void variable(){
        System.out.println(id_paciente+" "+nombre+" "+apellido+" "+ telefonoCasa+" "+telefonoCelular+" "+telefonoTrabajo+" "+ email1+" "+email2+" "+ cedula+" "+ fecha_nacimiento+" "+ sexo+" "+ tipo_sangre_id+" "+ provincia+" "+ sector+" "+direccion+" "+id+" "+nombreSeguro+" "+numeroSeguro);
    }
    
    public void setTotalRegistro(int tresult){
        this.total.setText(tresult+"");
    }















    public void crearBusqueda(String idPaciente,String nombre,String apellido,String cedula,String fechaNacimientoDesde,String fechaNacimientoHasta,String sexo,String email,String telefono,String provincia,String sector,String direccion,String numeroSeguro,String nombreSeguro,String idTipoSangre,String idSonografia,String referidoPor,String QueContenga,String condicionSonografia,String fechaSonografiaDesde,String fechaSonografiaHasta,String estadoSonografia,String tipoSonografia,String Medico,String Hospital,String impuesto,String descuento,int hacer){
        this.impuesto = impuesto;
        this.descuento = descuento;
        this.HospitalID = Hospital;
        String where = " ";
        boolean primer = true;
        if(!idPaciente.isEmpty()){
            if(primer){
                where  += " ( p.id in ("+idPaciente+") ) ";
                primer = false;
            }else{
                where  += " and ( p.id in ("+idPaciente+") ) ";
            }
        }
        if(!nombre.isEmpty()){
            if(primer){
                where  += " p.name_patient like '%"+nombre+"%'  ";
                primer = false;
            }else{
                where  += " and ( p.name_patient like '%"+nombre+"%' )  ";
            }
        }
        if(!apellido.isEmpty()){
            if(primer){
                where  += " p.last_patient like '%"+apellido+"%'  ";
                primer = false;
            }else{
                where  += " and ( p.last_patient like '%"+apellido+"%' )  ";
            }
        }
        if(!cedula.isEmpty()){
            if(primer){
                where  += " p.document_id like '%"+cedula+"%'  ";
                primer = false;
            }else{
                where  += " and ( p.document_id like '%"+cedula+"%' )  ";
            }
        }
        if(!fechaNacimientoDesde.isEmpty() || !fechaNacimientoHasta.isEmpty() ){
            if(fechaNacimientoHasta.isEmpty()){
                JOptionPane.showMessageDialog(null, "Tiene que colocar una fecha hasta");
            }
            if(fechaNacimientoDesde.isEmpty()){
                JOptionPane.showMessageDialog(null, "Tiene que colocar una fecha desde");
            }
            if(primer){
                where  += " ( p.date_of_birth  between '"+fechaNacimientoDesde+"' and  '"+fechaNacimientoHasta+"' )   ";
                primer = false;
            }else{
                where  += " and ( p.date_of_birth between '"+fechaNacimientoDesde+"' and  '"+fechaNacimientoHasta+"' )  ";
            }
        }
        
        if(!sexo.isEmpty()){
            if(primer){
                where  += " p.sex = '"+sexo+"'  ";
                primer = false;
            }else{
                where  += " and ( p.sex = '"+sexo+"' )  ";
            }
        }
        if(!email.isEmpty()){
            if(primer){
                where  += " ( e.email like '%"+email+"%' ) and (e.display = '1') ";
                primer = false;
            }else{
                where  += " and ( e.email like '%"+email+"%' ) and (e.display = '1') ";
            }
        }
        if(!telefono.isEmpty()){
            if(primer){
                where  += " ( t.telephone like '%"+telefono+"%')  and (t.display = '1')  ";
                primer = false;
            }else{
                where  += " and ( t.telephone like '%"+telefono+"%' )  and (t.display = '1') ";
            }
        }
        if(!provincia.isEmpty()){
            if(primer){
                where  += " ( a.province like '%"+provincia+"%' )  and (a.display = '1')  ";
                primer = false;
            }else{
                where  += " and ( a.province like '%"+provincia+"%' )  and (a.display = '1') ";
            }
        }
        if(!sector.isEmpty()){
            if(primer){
                where  += " (a.sector like '%"+sector+"%' )  and (a.display = '1') ";
                primer = false;
            }else{
                where  += " and ( a.sector like '%"+sector+"%' )  and (a.display = '1')  ";
            }
        }
        if(!direccion.isEmpty()){
            if(primer){
                where  += " ( a.address like '%"+direccion+"%' ) and (a.display = '1') ";
                primer = false;
            }else{
                where  += " and ( a.address like '%"+direccion+"%' )  and (a.display = '1') ";
            }
        }
        if(!numeroSeguro.isEmpty()){
            if(primer){
                where  += " ( ip.insurance_number like '%"+numeroSeguro+"%' ) and (a.display = '1') ";
                primer = false;
            }else{
                where  += " and ( ip.insurance_number  like '%"+numeroSeguro+"%' )  and (ip.display = '1')   ";
            }
        }//
        if(!nombreSeguro.isEmpty()){
            if(primer){
                where  += " ( ip.name_insurance_patient like '%"+nombreSeguro+"%' ) and (a.display = '1')  ";
                primer = false;
            }else{
                where  += " and ( ip.name_insurance_patient like '%"+nombreSeguro+"%' ) and (a.display = '1')  ";
            }
        }
        if(!idTipoSangre.isEmpty()){
            if(primer){
                where  += " ( p.type_of_blood_id = '"+idTipoSangre+"' ) ";
                primer = false;
            }else{
                where  += " and ( p.type_of_blood_id = '"+idTipoSangre+"' )  ";
            }
        }
        //
        if(!Hospital.isEmpty()){
            if(primer){
                where  += " s.id_hospital = '"+Hospital+"'  ";
                primer = false;
            }else{
                where  += " and ( s.id_hospital = '"+Hospital+"' )  ";
            }
        }
        if(!Medico.isEmpty()){
            if(primer){
                where  += " s.id_user = '"+Medico+"'  ";
                primer = false;
            }else{
                where  += " and ( s.id_user = '"+Medico+"' )  ";
            }
        }
        if(!tipoSonografia.isEmpty()){
            if(primer){
                where  += " s.id_type_of_sonography in ("+tipoSonografia+")  ";
                primer = false;
            }else{
                where  += " and (  s.id_type_of_sonography in ("+tipoSonografia+") )  ";
            }
        }
        if(!estadoSonografia.isEmpty()){
            if(primer){
                where  += " s.status = '"+estadoSonografia+"'  ";
                primer = false;
            }else{
                where  += " and ( s.status = '"+estadoSonografia+"' )  ";
            }
        }
        if(!fechaSonografiaDesde.isEmpty() || !fechaSonografiaHasta.isEmpty()){
            if(fechaSonografiaHasta.isEmpty()){
                JOptionPane.showMessageDialog(null, "Tiene que colocar una fecha hasta");
            }
            if(fechaSonografiaDesde.isEmpty()){
                JOptionPane.showMessageDialog(null, "Tiene que colocar una fecha desde");
            }
            if(primer){
                where  += " (date(s.when_it) between '"+fechaSonografiaDesde+"' and  '"+fechaSonografiaHasta+"' ) ";
                primer = false;
            }else{
                where  += " and ( date(s.when_it) between '"+fechaSonografiaDesde+"' and  '"+fechaSonografiaHasta+"' )  ";
            }
        }
        if(!condicionSonografia.isEmpty()){
            if(primer){
                where  += " s.condition_sonography = '"+condicionSonografia+"'  ";
                primer = false;
            }else{
                where  += " and ( s.condition_sonography = '"+condicionSonografia+"' )  ";
            }
        }
        if(!QueContenga.isEmpty()){
            if(primer){
                where  += " s.body like '%"+QueContenga+"%'  ";
                primer = false;
            }else{
                where  += " and ( s.body like '%"+QueContenga+"%' )  ";
            }
        }
        if(!referidoPor.isEmpty()){
            if(primer){
                where  += " s.referred_for like '%"+referidoPor+"%'  ";
                primer = false;
            }else{
                where  += " and ( s.referred_for like '%"+referidoPor+"%' )  ";
            }
        }
        if(!idSonografia.isEmpty()){
            if(primer){
                where  += " ( s.id in ("+idSonografia+") ) ";
                primer = false;
            }else{
                where  += " and ( s.id in ("+idSonografia+") ) ";
            }
        }
        this.mostrarDatosTablaPorTodosCampos(where);
        if(hacer == 1){
            this.mostrarReportePDF();
        }
    }

     public void mostrarDatosTablaPorTodosCampos(String where){
        //String[] datos = {"p.id","p.name_patient","p.last_patient","p.document_id","p.date_of_birth","p.sex","tob.name_of_blood","edad"};
        String[] datos = {"s.id","nombre_completo","edad","tipo_sonografia","hospital","medico","s.condition_sonography","s.status","fecha_creado","precio"};

       /* String select = "p.id,p.name_patient,p.last_patient,p.document_id,p.date_of_birth,p.sex,tob.name_of_blood, " +
            "IF(TIMESTAMPDIFF( YEAR,p.date_of_birth, CURDATE() ) < 1 , " +
            "IF(TIMESTAMPDIFF( MONTH,p.date_of_birth, CURDATE() ) < 1 , " +
            "IF(TIMESTAMPDIFF( DAY,p.date_of_birth, CURDATE() ) < 1 ,' Horas', " +
            "CONCAT(TIMESTAMPDIFF(DAY, p.date_of_birth, CURDATE()) , ' Dias' ) ), " +
            "CONCAT(TIMESTAMPDIFF(MONTH, p.date_of_birth, CURDATE()) , ' Meses' ) ), " +
            "CONCAT(TIMESTAMPDIFF(YEAR, p.date_of_birth, CURDATE()) , ' Años' ) ) AS edad";*/
        where += " and ";
        
        /*String[] datos = {"s.id","nombre_completo","edad","tipo_sonografia","hospital","medico","s.condition_sonography","s.status",
            "fecha_creado"};*/
        String select = "s.id, s.condition_sonography, " +
                        "s.status, " +
                        "tos.name_of_type_sonography as tipo_sonografia, " +
                        "h.name_hospital, " +
                        "h.rnc, u.nombre_titulo AS medico,DATE_FORMAT(s.when_it,'%d-%m-%Y') AS fecha_creado, " +
                        "CONCAT(p.name_patient,' ',p.last_patient) AS nombre_completo,"
                + "CONCAT(h.name_hospital,' (',h.rnc,')') AS hospital, "
                + "IF(TIMESTAMPDIFF( YEAR,p.date_of_birth, CURDATE() ) < 1 , " +
"IF(TIMESTAMPDIFF( MONTH,p.date_of_birth, CURDATE() ) < 1 , " +
"IF(TIMESTAMPDIFF( DAY,p.date_of_birth, CURDATE() ) < 1 ,' Horas', " +
"CONCAT(TIMESTAMPDIFF(DAY, p.date_of_birth, CURDATE()) , ' Dias' ) ), " +
"CONCAT(TIMESTAMPDIFF(MONTH, p.date_of_birth, CURDATE()) , ' Meses' ) ), " +
"CONCAT(TIMESTAMPDIFF(YEAR, p.date_of_birth, CURDATE()) , ' Años' ) ) AS edad"
                + ",IFNULL((SELECT poh.price FROM price_of_hospital AS poh WHERE poh.id_hospital = h.id AND poh.id_type_of_sonography = tos.id LIMIT 1),0 ) AS precio";
        //String where = " ";
        
       /* String tablasJoin = "sonography AS s " +
                            "INNER JOIN type_of_sonography AS tos " +
                            "ON s.id_type_of_sonography = tos.id " +
                            "INNER JOIN hospital AS h " +
                            "ON s.id_hospital = h.id " +
                            "INNER JOIN users AS u " +
                            "ON s.id_user = u.id "
                          + "INNER JOIN patient AS p " +
                            "ON p.id = s.id_patient";
        */
        String tablasJoin = "sonography as s inner join "
                           +" patient AS p on s.id_patient = p.id " +
                            "INNER JOIN type_of_sonography AS tos " +
                            "ON s.id_type_of_sonography = tos.id " +
                            "INNER JOIN hospital AS h " +
                            "ON s.id_hospital = h.id " +
                            "INNER JOIN users AS u " +
                            "ON s.id_user = u.id "+
                            "LEFT JOIN address AS a " +
                            "ON p.id = a.id_patient " +
                            "LEFT JOIN email AS e " +
                            "ON p.id = e.id_patient " +
                            "LEFT JOIN insurance_patient AS ip " +
                            "ON p.id = ip.id_patient " +
                            "LEFT JOIN telephone AS t " +
                            "ON p.id = t.id_patient " +
                            "LEFT JOIN type_of_blood AS tob " +
                            "ON p.type_of_blood_id = tob.id ";
        
        this.mysql.setOrden(" group by s.id ");
        Object[][] resultado = (Object[][]) this.mysql.generarSelectMultipleTabla(tablasJoin, datos,select,where,"s.");
        //Object[][] resultado = (Object[][]) this.mysql.generarSelect("type_of_blood", datos);
        Object[][] infoTabla= (Object[][]) resultado[0][0];
        int tresult = (int)resultado[0][1];
        this.setTotalRegistro(tresult);
        this.totaRegistro = tresult+"";
        DefaultTableModel modelo = new DefaultTableModel(infoTabla,datos);
        this.jTable1.setModel(modelo);
        this.mysql.setOrden("");
        this.jTabbedPane1.setSelectedIndex(0);
        this.getCalculos(infoTabla);
        //this.crearNuevoPaciente();
    }
     public void getCalculos(Object[][] o){
         double subTotal = 0.00;String aux;
         this.tituloTotal = "Monto Total Con Descuentos ";
         this.tituloSubTotal = "Monto Sub Total";
         for(int c = 0 ; c < 30 ; c++){
         for(int i = 0 ; i < o.length ; i++){
            this.CuerpoReporte.add( new modelo10Columna( o[i][0].toString(),o[i][1].toString(),o[i][2].toString(),o[i][3].toString(),o[i][4].toString(),o[i][5].toString(),o[i][6].toString(),o[i][7].toString(),o[i][8].toString(),o[i][9].toString() ) );
            aux = o[i][9].toString();
            subTotal += Double.parseDouble(aux);
            //subTotal = String.valueOf(o[i][9].toString());
             /*for(int c = 0 ; c < o[i].length ; c++){
                System.out.println("i "+i+" c "+c+" "+o[i][c]);
                }*/
            }
         }
         if(subTotal > 0){
            // JOptionPane.showMessageDialog(null, "Entramos");
            this.Montos[0] = String.valueOf(subTotal);
            double montoDescuento = 0.00;
            if(!this.descuento.isEmpty()){
              //               JOptionPane.showMessageDialog(null, "hay descuento");

                int auxI = Integer.parseInt(this.descuento);
                double auxD = subTotal * this.getMontoPorcentaje( auxI );
                montoDescuento = auxD;
               this.Montos[1] =  auxD+"";
               this.tituloDescuento = "Monto Del "+this.descuento+"% Descuento ";
            }
            double montoImpuesto = 0.00;
            if(!this.impuesto.isEmpty()){
                // JOptionPane.showMessageDialog(null, "impuesto");
                int auxI = Integer.parseInt(this.impuesto);
                double auxD = subTotal * this.getMontoPorcentaje( auxI );
                montoImpuesto = auxD;
               this.Montos[2] =  auxD+"";
               this.tituloImpuesto = "Monto Del "+this.impuesto+"% Impuesto ";
            }
            double montoTotal = ((subTotal - montoDescuento) - montoImpuesto);
            this.Montos[3] =  montoTotal+"";
            
         }else{
             this.Montos[0]= "0.00";this.Montos[1]= "0.00";this.Montos[2]= "0.00";
             this.Montos[3]= "0.00";
         }
     }
     public double getMontoPorcentaje(int porciento){
         double monto = 0.00;
         String p = "0.";
         if(porciento < 10){
             p = p+"0"+porciento;
             monto = Double.parseDouble(p);
         }
         if(porciento < 100){
             p = p+porciento;
             monto = Double.parseDouble(p);
         }
         return monto;
     }
    public void buscadorEnter(java.awt.event.KeyEvent evt,int i,String loquesebusca){
        if(evt.getKeyCode() == 10){
            if(!loquesebusca.isEmpty()){
               this.buscarPor(i,loquesebusca);
            }else{
                JOptionPane.showMessageDialog(null, "Se ha precionado enter y se activo el buscador\npero el campo esta vacio");
            }
        }
    }
    public void buscarPor(int i,String loquesebusca){
        this.jTabbedPane1.setSelectedIndex(0);
        switch(i){
            case 1:
               this.mostrarDatosTablaPorVersionLibre(loquesebusca,1);  
               break;
            case 2:
               this.mostrarDatosTablaPorVersionLibre(loquesebusca,2); 
               break;
            case 3:
              this.mostrarDatosTablaPorVersionLibre(loquesebusca,3); 
               break;
            case 4:
               this.mostrarDatosTablaPorVersionLibre(loquesebusca,4); 
               break;
            case 5:
               this.mostrarDatosTablaPorVersionLibre(loquesebusca,5);
               break;
            case 6:
               this.mostrarDatosTablaPorVersionLibre(loquesebusca,6); 
               break;
            case 7:
               this.mostrarDatosTablaPorVersionLibre(loquesebusca,7); 
               break;
            case 8:
               this.mostrarDatosTablaPorVersionLibre(loquesebusca,8); 
               break;
            case 9:
               this.mostrarDatosTablaPorVersionLibre(loquesebusca,9); 
               break;
            case 10:
               this.mostrarDatosTablaPorVersionLibre(loquesebusca,10); 
               break;
            case 11:
               this.mostrarDatosTablaPorVersionLibre(loquesebusca,11); 
               break;
            case 12:
               this.mostrarDatosTablaPorVersionLibre(loquesebusca,12); 
               break;
            case 13:
               this.mostrarDatosTablaPorVersionLibre(loquesebusca,13); 
               break;
            case 14:
               this.mostrarDatosTablaPorVersionLibre(loquesebusca,14); 
               break;
            case 15:
               this.mostrarDatosTablaPorVersionLibre(loquesebusca,15); 
               break;
        }
    }
    
     public String[] optenerDatosSonografia(String idSonografia){
         
         
         this.mysql.setSelectCompos("s.body, s.referred_for, " +
                    "tos.name_of_type_sonography, u.nombre_titulo, " +
                    "h.address, h.email, h.eslogan, h.icono_url, " +
                    "h.name_hospital, h.rnc, h.telephone,h.web_page, "
                    + "IF(TIMESTAMPDIFF( YEAR,p.date_of_birth, CURDATE() ) < 1 ," +
                    "IF(TIMESTAMPDIFF( MONTH,p.date_of_birth, CURDATE() ) < 1 ," +
                    "IF(TIMESTAMPDIFF( DAY,p.date_of_birth, CURDATE() ) < 1 ,' Horas', " +
                    "CONCAT(TIMESTAMPDIFF(DAY, p.date_of_birth, CURDATE()) , ' Dias' ) ), " +
                    "CONCAT(TIMESTAMPDIFF(MONTH, p.date_of_birth, CURDATE()) , ' Meses' ) ), " +
                    "CONCAT(TIMESTAMPDIFF(YEAR, p.date_of_birth, CURDATE()) , ' Años' ) ) AS edad , " +
                    "p.name_patient, DATE_FORMAT(s.when_it,'%d-%m-%Y') AS fecha_creado,p.last_patient");
        
         String[] campos = {"s.body", "s.referred_for","tos.name_of_type_sonography","u.nombre_titulo", "h.address", 
             "h.email", "h.eslogan", "h.icono_url",
                            "h.name_hospital", "h.rnc", "h.telephone","h.web_page","edad","p.name_patient", "fecha_creado"
                            ,"p.last_patient"};
        
        String where = "where s.id = "+idSonografia+" and s.display = '1' ";
        String tablas = "sonography AS s " +
                        "INNER JOIN type_of_sonography AS tos " +
                        "ON s.id_type_of_sonography = tos.id " +
                        "INNER JOIN hospital AS h " +
                        "ON s.id_hospital = h.id " +
                        "INNER JOIN users AS u " +
                        "ON s.id_user = u.id "
                        + " INNER JOIN patient AS p " +
                        "ON p.id = s.id_patient";
        
        String[] datos = this.mysql.getValues(tablas,where , campos);
        this.mysql.setSelectCompos("*");
        return datos;
    }
  
     public void mostrarSonografiaPDF(String idHospital){
         String[] datos = this.optenerDatosSonografia(idHospital);
         String imprimir = "1";
        try {
            // TODO add your handling code here:
            JasperReport loadObject = (JasperReport) JRLoader.loadObject(JFrameCrearSonografia.class.getResource("/app/impresiones/RSonografia.jasper"));
            
            Map parameters = new HashMap<String, Object>();
  
            parameters.put("cuerpo",datos[0]);
            parameters.put("referido_por",datos[1]);
            parameters.put("nombre_paciente",datos[13]+" "+datos[15]);
            parameters.put("edad_paciente",datos[12]);            
            parameters.put("fecha_actual",datos[14]);
            parameters.put("tipo_sonografia",datos[2]);
            parameters.put("pagina_web_empresa",datos[11]);
            parameters.put("email_empresa",datos[5]);
            parameters.put("telefono_empresa",datos[10]);
            parameters.put("rnc_empresa",datos[9]);
            parameters.put("eslogan_empresa",datos[6]);
            parameters.put("nombre_empresa",datos[8]);
            parameters.put("icono_hospital",datos[7]);
            parameters.put("direccion_empresa",datos[4]);
            
            //this.nombreTituloUsuario = "Dra. Silvia Nolasco";
            parameters.put("nombre_medico",datos[3]);
            //this.nombreTituloUsuario 
                        
            //parameters.put("ptitulo", "hola eudy ya estoy cerca");
            //parameters.put("rutaParametro", "C:\\Users\\Eudy\\Documents\\NetBeansProjects\\pruebaClase\\reportes\\src\\reportes\\images.png");
            
            JasperPrint jp = JasperFillManager.fillReport(loadObject, parameters,new JREmptyDataSource());
            
            switch (imprimir){
                case "1":
                    JasperViewer jv = new JasperViewer(jp,false);
                    jv.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);
                    jv.setVisible(true);
                   break;
                case "2":
                    //sin el dialogo
                    JasperPrintManager.printReport(jp, false);
                    break;
                case "3":
                    //con el dialogo
                    JasperPrintManager.printReport(jp, true);
                    break;
            }
           
        } catch (JRException ex) {
           JOptionPane.showMessageDialog(null, ex);
        }
    }
    public String[] optenerDatosHospital(String idHospital){
         
         
         this.mysql.setSelectCompos("h.address,h.email, h.eslogan, h.icono_url,h.name_hospital, h.rnc, h.telephone,h.web_page, (DATE_FORMAT(NOW(),'%d / %m / %Y %h:%i %p')) as fecha_actual");
        
         String[] campos = {"h.address","h.email", "h.eslogan", "h.icono_url",
                            "h.name_hospital", "h.rnc", "h.telephone","h.web_page","fecha_actual"};
        
        String where = "where h.id = "+idHospital+" and h.display = '1' ";
        String tablas = " hospital AS h ";
        
        String[] datos = this.mysql.getValues(tablas,where , campos);
        this.mysql.setSelectCompos("*");
        return datos;
    }
     public void mostrarDatosTablaSonografia(JTable table,JLabel JLabelTotal){
        String[] datos = {"s.id","nombre_completo","edad","tipo_sonografia","hospital","medico","s.condition_sonography","s.status",
            "fecha_creado"};
        String select = "s.id, s.condition_sonography, " +
                        "s.status, " +
                        "tos.name_of_type_sonography as tipo_sonografia, " +
                        "h.name_hospital, " +
                        "h.rnc, u.nombre_titulo AS medico,DATE_FORMAT(s.when_it,'%d-%m-%Y') AS fecha_creado, " +
                        "CONCAT(p.name_patient,' ',p.last_patient) AS nombre_completo,"
                + "CONCAT(h.name_hospital,' (',h.rnc,')') AS hospital, "
                + "IF(TIMESTAMPDIFF( YEAR,p.date_of_birth, CURDATE() ) < 1 , " +
"IF(TIMESTAMPDIFF( MONTH,p.date_of_birth, CURDATE() ) < 1 , " +
"IF(TIMESTAMPDIFF( DAY,p.date_of_birth, CURDATE() ) < 1 ,' Horas', " +
"CONCAT(TIMESTAMPDIFF(DAY, p.date_of_birth, CURDATE()) , ' Dias' ) ), " +
"CONCAT(TIMESTAMPDIFF(MONTH, p.date_of_birth, CURDATE()) , ' Meses' ) ), " +
"CONCAT(TIMESTAMPDIFF(YEAR, p.date_of_birth, CURDATE()) , ' Años' ) ) AS edad";
        String where = " ";
        
        String tablasJoin = "sonography AS s " +
                            "INNER JOIN type_of_sonography AS tos " +
                            "ON s.id_type_of_sonography = tos.id " +
                            "INNER JOIN hospital AS h " +
                            "ON s.id_hospital = h.id " +
                            "INNER JOIN users AS u " +
                            "ON s.id_user = u.id "
                          + "INNER JOIN patient AS p " +
                            "ON p.id = s.id_patient";
        
        Object[][] resultado = (Object[][]) this.mysql.generarSelectMultipleTabla(tablasJoin, datos,select,where,"s.");
        //Object[][] resultado = (Object[][]) this.mysql.generarSelect("type_of_blood", datos);
        Object[][] infoTabla= (Object[][]) resultado[0][0];
        DefaultTableModel modelo = new DefaultTableModel(infoTabla,datos);
        JLabelTotal.setText(resultado[0][1]+"");
        table.setModel(modelo);
    }
     
     public void mostrarDatosTablaPorVersionLibre(String buscar,int i){
        
         String[] datos = {"s.id","nombre_completo","edad","tipo_sonografia","hospital","medico","s.condition_sonography","s.status","fecha_creado"};
     // String[] datos = {"s.id","nombre_completo","edad","tipo_sonografia","hospital","medico","s.condition_sonography","s.status","fecha_creado"};
        /*String select = "s.id, s.condition_sonography, " +
                        "s.status, " +
                        "tos.name_of_type_sonography as tipo_sonografia, " +
                        "h.name_hospital, " +
                        "h.rnc, u.nombre_titulo AS medico,DATE_FORMAT(s.when_it,'%d-%m-%Y') AS fecha_creado, " +
                        "CONCAT(p.name_patient,' ',p.last_patient) AS nombre_completo,"
                + "CONCAT(h.name_hospital,' (',h.rnc,')') AS hospital, "
                + "IF(TIMESTAMPDIFF( YEAR,p.date_of_birth, CURDATE() ) < 1 , " +
                    "IF(TIMESTAMPDIFF( MONTH,p.date_of_birth, CURDATE() ) < 1 , " +
                    "IF(TIMESTAMPDIFF( DAY,p.date_of_birth, CURDATE() ) < 1 ,' Horas', " +
                    "CONCAT(TIMESTAMPDIFF(DAY, p.date_of_birth, CURDATE()) , ' Dias' ) ), " +
                    "CONCAT(TIMESTAMPDIFF(MONTH, p.date_of_birth, CURDATE()) , ' Meses' ) ), " +
                    "CONCAT(TIMESTAMPDIFF(YEAR, p.date_of_birth, CURDATE()) , ' Años' ) ) AS edad";*/
        String select = "s.id, s.condition_sonography, " +
                        "s.status, " +
                        "tos.name_of_type_sonography as tipo_sonografia, " +
                        "h.name_hospital, " +
                        "h.rnc, u.nombre_titulo AS medico,DATE_FORMAT(s.when_it,'%d-%m-%Y') AS fecha_creado, " +
                        "CONCAT(p.name_patient,' ',p.last_patient) AS nombre_completo,"
                + "CONCAT(h.name_hospital,' (',h.rnc,')') AS hospital, "
                + "IF(TIMESTAMPDIFF( YEAR,p.date_of_birth, CURDATE() ) < 1 , " +
"IF(TIMESTAMPDIFF( MONTH,p.date_of_birth, CURDATE() ) < 1 , " +
"IF(TIMESTAMPDIFF( DAY,p.date_of_birth, CURDATE() ) < 1 ,' Horas', " +
"CONCAT(TIMESTAMPDIFF(DAY, p.date_of_birth, CURDATE()) , ' Dias' ) ), " +
"CONCAT(TIMESTAMPDIFF(MONTH, p.date_of_birth, CURDATE()) , ' Meses' ) ), " +
"CONCAT(TIMESTAMPDIFF(YEAR, p.date_of_birth, CURDATE()) , ' Años' ) ) AS edad";
        
      /*  String tablasJoin = "sonography AS s " +
                            "INNER JOIN type_of_sonography AS tos " +
                            "ON s.id_type_of_sonography = tos.id " +
                            "INNER JOIN hospital AS h " +
                            "ON s.id_hospital = h.id " +
                            "INNER JOIN users AS u " +
                            "ON s.id_user = u.id "
                          + "INNER JOIN patient AS p " +
                            "ON p.id = s.id_patient ";*/
        String tablasJoin = "sonography AS s " +
                            "INNER JOIN type_of_sonography AS tos " +
                            "ON s.id_type_of_sonography = tos.id " +
                            "INNER JOIN hospital AS h " +
                            "ON s.id_hospital = h.id " +
                            "INNER JOIN users AS u " +
                            "ON s.id_user = u.id "+
                            "INNER JOIN patient AS p " +
                            "ON p.id = s.id_patient ";
                        
        String where = "";
        switch(i){
            case 4:
                 where = "p.document_id = '"+buscar+"' and ";
                break;
            case 1:
                 where = "p.id in ("+buscar+") and ";
                break;   
            case 2:
                where = "p.name_patient like '%"+buscar+"%' and ";
                break;
            case 3:
                where = "p.last_patient like '%"+buscar+"%' and ";
                break;
            case 5:
                where = "p.date_of_birth = '"+buscar+"' and ";
                break;
            case 6:
                where = "t2.telephone like '%"+buscar+"%' and ";
                tablasJoin +=" left join telephone as t2 on p.id = t2.id_patient";
                break;    
            case 7:
                where = "t2.email like '%"+buscar+"%' and ";
                tablasJoin +=" left join email as t2 on p.id = t2.id_patient";
                break;
            case 8:
                where = "t2.insurance_number = '"+buscar+"' and ";
                tablasJoin +=" left join insurance_patient as t2 on p.id = t2.id_patient";
                break;
            case 9:
                where = "t2.province like '%"+buscar+"%' and ";
                tablasJoin +=" left join address as t2 on p.id = t2.id_patient";
                break;
            case 10:
                where = "t2.sector like '%"+buscar+"%' and ";
                tablasJoin +=" left join address as t2 on p.id = t2.id_patient";
                break;
            case 11:
                where = "t2.address like '%"+buscar+"%' and ";
                tablasJoin +=" left join address as t2 on p.id = t2.id_patient";
                break;
            case 12:
                where = "t2.name_insurance_patient = '"+buscar+"' and ";
                tablasJoin +=" left join insurance_patient as t2 on p.id = t2.id_patient";
                break;
            case 13:
                 where = "s.id in ("+buscar+") and ";
                break;
            case 14:
                where = "s.body like '%"+buscar+"%' and ";
                break;
            case 15:
                where = "s.referred_for like '%"+buscar+"%' and ";
                break;

        }
        
        
        
        Object[][] resultado = (Object[][]) this.mysql.generarSelectMultipleTabla(tablasJoin, datos,select,where,"s.");
        //Object[][] resultado = (Object[][]) this.mysql.generarSelect("type_of_blood", datos);
        Object[][] infoTabla= (Object[][]) resultado[0][0];
        int tresult = (int)resultado[0][1];
        this.setTotalRegistro(tresult);
        DefaultTableModel modelo = new DefaultTableModel(infoTabla,datos);
        this.jTable1.setModel(modelo);
        //this.crearNuevoPaciente();
    }
    
     
    public void mostrarReportePDF(){
         //String[] datos = this.optenerDatosSonografia(idHospital);
        String[] datosHospital = this.optenerDatosHospital(this.HospitalID);
        String imprimir = "1";
        try {
            // TODO add your handling code here:
            JasperReport loadObject = (JasperReport) JRLoader.loadObject(JFrameCrearSonografia.class.getResource("/app/impresiones/ReportesGenerales.jasper"));
            
            Map parameters = new HashMap<String, Object>();
            //{"h.address","h.email", "h.eslogan", "h.icono_url","h.name_hospital", "h.rnc", "h.telephone","h.web_page"}
            parameters.put("nombre_hospital",datosHospital[4]);
            parameters.put("direccion_empresa",datosHospital[0]);
            parameters.put("telefono_empresa",datosHospital[6]);
            parameters.put("email_empresa",datosHospital[1]);            
            parameters.put("pagina_web_empresa",datosHospital[7]);
            parameters.put("icono_hospital",datosHospital[3]);
            parameters.put("fecha_hora",datosHospital[8]);
            
            
            parameters.put("reporte_de","Sonografia");
            
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(this.CuerpoReporte);
           
            String urlImagenCreador = getClass().getResource("../../icono/eudyicono.png").getPath();
            parameters.put("icono_creador",urlImagenCreador);
            
            parameters.put("total_registro",this.totaRegistro);
            
            parameters.put("sub_total",this.tituloSubTotal);
            parameters.put("porciento",this.tituloDescuento);
            parameters.put("itbis",this.tituloImpuesto);
            parameters.put("total",this.tituloTotal);
            
            parameters.put("sub_total_monto",this.Montos[0]);
            parameters.put("porciento_monto",this.Montos[1]);
            parameters.put("itbis_monto",this.Montos[2]);
            parameters.put("total_monto",this.Montos[3]);
            //this.nombreTituloUsuario 
            tituloSubTotal="";tituloDescuento="";tituloImpuesto="";tituloTotal="";            
            //parameters.put("ptitulo", "hola eudy ya estoy cerca");
            //parameters.put("rutaParametro", "C:\\Users\\Eudy\\Documents\\NetBeansProjects\\pruebaClase\\reportes\\src\\reportes\\images.png");
            
            JasperPrint jp = JasperFillManager.fillReport(loadObject, parameters,ds/*new JREmptyDataSource()*/);
            
            switch (imprimir){
                case "1":
                    JasperViewer jv = new JasperViewer(jp,false);
                    jv.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);
                    jv.setVisible(true);
                   break;
                case "2":
                    //sin el dialogo
                    JasperPrintManager.printReport(jp, false);
                    break;
                case "3":
                    //con el dialogo
                    JasperPrintManager.printReport(jp, true);
                    break;
            }
           
        } catch (JRException ex) {
           JOptionPane.showMessageDialog(null, ex);
        }
    }
}
