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
public class ClassCrearSonografia{
    private conection.Mysql mysql;
    private ValidData valid = new ValidData();
    private String usuario,numeroSeguro,type_user,id,id_patient="1";
    private String[] key = {"telephone","id_type_of_telephone","when_it","id_user","id_patient"};
    private int lineas=10;
    private boolean agregar=true;
    private ArrayList<String> camposEdit = new ArrayList<String>();
    private ArrayList<String> valorEdit = new ArrayList<String>();
    private String NombreTabla = " telephone";        
    private String[] idHospitales,idTipoDeSonografia;
    private String telefonoOld="",tipoOld="";
    private String usuarioID,nombreUsuario,nombreTituloUsuario;

    
    public ClassCrearSonografia(){
        mysql = new Mysql();
    }
    public ClassCrearSonografia(conection.Mysql mysql){
       this.mysql =mysql;
    }
     public void setDatosUsuario(String usuarioID, String nombreUsuario,String nombreTituloUsuario){
        this.usuarioID = usuarioID;
        this.nombreUsuario = nombreUsuario;
        this.nombreTituloUsuario = nombreTituloUsuario;
        JOptionPane.showMessageDialog(null, "Usuario "+this.usuarioID+" "+this.nombreUsuario+" "+this.nombreTituloUsuario);
    }
      public void llenarComboBoxHospital(javax.swing.JComboBox JCTipoSangre){
            String[] campos = {"id","name_hospital","rnc"};
            
            Object[][] resultado = (Object[][])  this.mysql.generarSelect("hospital",campos,"name_hospital","asc","");
            Object[][] infoTabla= (Object[][]) resultado[0][0];
          //System.out.println(infoTabla.length);
          //System.out.println(infoTabla[1].length);
          
          this.idHospitales = new String[infoTabla.length];
          for(int i = 0 ; i < infoTabla.length ; i++){
                JCTipoSangre.addItem(infoTabla[i][1]+" ("+infoTabla[i][2]+")");
                idHospitales[i]= infoTabla[i][0].toString();
               // System.out.println(infoTabla[i][0]+" "+infoTabla[i][1]);
          }
    }
     public void llenarComboBoxTipoDeSonografia(javax.swing.JComboBox JCTipoSangre){
            String[] campos = {"id","name_of_type_sonography"};
            
            Object[][] resultado = (Object[][])  this.mysql.generarSelect("type_of_sonography",campos,"name_of_type_sonography","asc","");
            Object[][] infoTabla= (Object[][]) resultado[0][0];
          //System.out.println(infoTabla.length);
          //System.out.println(infoTabla[1].length);
          
          this.idTipoDeSonografia = new String[infoTabla.length];
          for(int i = 0 ; i < infoTabla.length ; i++){
                JCTipoSangre.addItem(infoTabla[i][1]);
                this.idTipoDeSonografia[i]= infoTabla[i][0].toString();
                //System.out.println(infoTabla[i][0]+" "+infoTabla[i][1]);
          }
    }
    
    public String getIdHospital(int index){
        return this.idHospitales[index];
    }
    public String getIdTipoDeSonografia(int index){
        return this.idTipoDeSonografia[index];
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
    public boolean insertSonografia(String usuario,String numeroSeguro){
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
         int existe = this.mysql.getValues(this.NombreTabla, "where telephone = '"+usuario+"' and id_patient = '"+this.id_patient+"' ");
          if(existe < 1){
            return true;
        }else{
            //JOptionPane.showMessageDialog(null, "El numero ya existe");
            return false;
          }
    }
    public void limpiarTexto(javax.swing.JComboBox texto,javax.swing.JTextField texto1){
       texto.setSelectedIndex(0);texto1.setText("");
    
    }
    public boolean update( String usuario,String numeroSeguro, String id){
        boolean respuesta = true;
        if(valid.validEmpty(numeroSeguro)){
            if( (!this.validarUsuario(numeroSeguro)) && (!numeroSeguro.equals(this.telefonoOld)) ){
                System.out.println("numero telefono existe");
                respuesta = false;
                return respuesta;
            }else{
               System.out.println("Vamos");
                this.valorEdit.add(numeroSeguro);
                this.camposEdit.add("telephone");
                
                this.valorEdit.add(usuario);
                this.camposEdit.add("id_type_of_telephone");
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
        //`body``when_it``condition_sonography``status``id_type_of_sonography``id_patient``id_hospital`
        String[] values = {this.numeroSeguro,this.usuario,"now()",this.usuarioID,this.id_patient};
        System.out.println(" key "+this.key+" Values "+values+" total index "+values.length);
        boolean respuesta = mysql.generarInsert(this.key, values, this.NombreTabla);
        return respuesta;
    }
    
     public String procesarInsert(String idPaciente,String referred_for,String body,String condition_sonography,String id_type_of_sonography,String id_hospital){
        //`body``when_it``condition_sonography``status``id_type_of_sonography``id_patient``id_hospital`
        String[] campos1 = {"id_patient","referred_for", "body", "condition_sonography","id_type_of_sonography","id_hospital","when_it","id_user"};
        String[] values = {idPaciente,referred_for,body,condition_sonography,id_type_of_sonography,id_hospital,"now()",this.usuarioID};
       // boolean respuesta = mysql.generarInsert(campos1, values, "sonography");
       String respuesta = mysql.generarInsertWithGetLastID(campos1, values, "sonography");
       return respuesta;
    }
    public boolean procesarUpdateSonografia(String[] values,String id){
        String[] key = {"referred_for", "body","condition_sonography"};
        //System.out.println(" key "+this.key+" Values "+values+" total index "+values.length);
        boolean respuesta = mysql.updateRecord("sonography",id,key, values);
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
        //String[] datos = {"id","telephone","id_type_of_telephone"};
        //String campo = "id_patient = '"+this.id_patient+"' and telephone";
         String[] datos = {"t.id","t.telephone","t1.name_type_telephone"};
        String select = "t.id, t.telephone, t1.name_type_telephone";
        String where = "t.id_patient = '"+this.id_patient+"' and t.telephone like '%"+palabraBuscar+"%' and ";
        String tablasJoin = ""+this.NombreTabla+" as t left join type_of_telephone as t1 on "
                + "t.id_type_of_telephone = t1.id";
        Object[][] resultado = (Object[][]) this.mysql.generarSelectMultipleTabla(tablasJoin, datos,select,where,"t."); /*this.mysql.generarSelect(this.NombreTabla, datos,palabraBuscar,campo)*/;
        Object[][] infoTabla= (Object[][]) resultado[0][0];
        DefaultTableModel modelo = new DefaultTableModel(infoTabla,datos);
        JLabelTotal.setText(resultado[0][1]+"");
        table.setModel(modelo); 
    }
    public String[] mostrarEditarUsuario(String id){
        String[] campos ={"t.id","t.telephone","t1.name_type_telephone"};
        String join = this.NombreTabla+" as t left join type_of_telephone as t1 "
                + "on t.id_type_of_telephone = t1.id";
        String columnas = "t.id, t.telephone, t.id_type_of_telephone, t1.name_type_telephone";
        String[] respuesta = this.mysql.generarSelectWithJoin(join,"t.id,t.telephone,t1.name_type_telephone", id+" and id_patient = "+this.id_patient,campos,"t.");
        this.telefonoOld = respuesta[1];
        this.tipoOld = respuesta[2];
        this.setAgregar(false);
        return respuesta;
    }
    
    public void cambiarTestoParaEditar(javax.swing.JButton b,javax.swing.JLabel l,javax.swing.JTextField jtf){
        if(this.getAgregar()){
            b.setText("Agregar");
            l.setText("Agregar Teléfono");
            //jtf.enable(true);
        }else{
            b.setText("Editar");
            l.setText("Editar Teléfono");
            //jtf.enable(false);
        }
    }
    
    public void mostrarDatosTabla(JTable table,JLabel JLabelTotal){
        String[] datos = {"t.id","t.telephone","t1.name_type_telephone"};
        String select = "t.id, t.telephone, t1.name_type_telephone";
        String where = "t.id_patient = '"+this.id_patient+"' and ";
        String tablasJoin = ""+this.NombreTabla+" as t left join type_of_telephone as t1 on "
                + "t.id_type_of_telephone = t1.id";
        Object[][] resultado = (Object[][]) this.mysql.generarSelectMultipleTabla(tablasJoin, datos,select,where,"t.");
        //Object[][] resultado = (Object[][]) this.mysql.generarSelect("type_of_blood", datos);
        Object[][] infoTabla= (Object[][]) resultado[0][0];
        DefaultTableModel modelo = new DefaultTableModel(infoTabla,datos);
        JLabelTotal.setText(resultado[0][1]+"");
        table.setModel(modelo);
        
    }
    
    public String[] optenerDatosHospital(String idHospital){
        String[] campos = {"name_hospital","telephone","email","address","web_page","rnc","icono_url","eslogan"};
        String where = "where id = "+idHospital+" and display = '1' ";
        String[] datos = this.mysql.getValues("hospital",where , campos);
        return datos;
    }
    
}
