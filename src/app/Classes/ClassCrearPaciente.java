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
    private  String id_paciente,nombre,apellido, telefonoCasa, telefonoCelular, telefonoTrabajo, email1, email2, cedula, fecha_nacimiento, sexo, tipo_sangre_id, provincia, sector, direccion,id,nombreSeguro,numeroSeguro;
    private String[] key = {"id","name_patient","last_patient","document_id","date_of_birth","sex","type_of_blood_id"};
    private int id_user;
    private int lineas=10;
    private boolean agregar=true;
    private ArrayList<String> camposEdit;
    private ArrayList<String> valorEdit;
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
          //System.out.println(infoTabla.length);
          //System.out.println(infoTabla[1].length);
          
          this.idTipoSangre = new String[infoTabla.length];
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
        String[] values = {this.numeroSeguro,this.nombreSeguro,"now()",this.id_user+"1",this.id_paciente};
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
        String[] values = {this.sector,this.provincia,this.direccion,"now()",this.id_user+"1",this.id_paciente};
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
        String[] values = {this.nombre,this.apellido,this.cedula,this.sexo,this.tipo_sangre_id,"now()",this.id_user+"1",this.fecha_nacimiento};
        
        this.id_paciente = mysql.generarInsertWithGetLastID(key, values, "patient");
        System.out.println("id Paciente "+ this.id_paciente);
        this.insertPacienteDireccion();
        this.insertPacienteSeguro();
        this.insertPacienteEmail();
        this.insertPacienteTelefono();
        boolean respuesta = true;
        
        return respuesta;
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
        String[] values = {telefono,"now()",this.id_paciente,this.id_user+"1"};
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
        String[] values = {email,"now()",this.id_paciente,this.id_user+"1"};
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
            l.setText("Agregar Usuario");
            //jtf.enable(true);
        }else{
            b.setText("Editar");
            l.setText("Editar Usuario");
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
}
