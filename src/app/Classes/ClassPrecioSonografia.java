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
public class ClassPrecioSonografia{
    private conection.Mysql mysql;
    private ValidData valid = new ValidData();
    private String usuario,numeroSeguro,type_user,id,id_patient="1";
    private String[] key = {"price","id_hospital","id_type_of_sonography","when_it","id_user"};

    private int lineas=10;
    private boolean agregar=true;
    private ArrayList<String> camposEdit = new ArrayList<String>();
    private ArrayList<String> valorEdit = new ArrayList<String>();
    private String NombreTabla = " price_of_hospital";        
    private String[] idTipoTelefono;
    private String hospitalOld="",tipoSonografiaOld="";
      private String usuarioID,nombreUsuario,nombreTituloUsuario;
          private String[] idHospitales,idTipoDeSonografia;


    public void setDatosUsuario(String usuarioID, String nombreUsuario,String nombreTituloUsuario){
        this.usuarioID = usuarioID;
        this.nombreUsuario = nombreUsuario;
        this.nombreTituloUsuario = nombreTituloUsuario;
        //JOptionPane.showMessageDialog(null, "Usuario "+this.usuarioID+" "+this.nombreUsuario+" "+this.nombreTituloUsuario);
        
    }
     public void llenarComboBoxHospital(javax.swing.JComboBox JCTipoSangre){
            String[] campos = {"id","name_hospital","rnc"};
            
            Object[][] resultado = (Object[][])  this.mysql.generarSelect("hospital",campos,"name_hospital","asc","");
            Object[][] infoTabla= (Object[][]) resultado[0][0];
          //System.out.println(infoTabla.length);
          //System.out.println(infoTabla[1].length);llenarComboBox
          
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
    public ClassPrecioSonografia(){
        mysql = new Mysql();
    }
    public ClassPrecioSonografia(conection.Mysql mysql){
       this.mysql =mysql;
    }
    public String getIdTipoSangre(int index){
        return this.idTipoTelefono[index];
    }
    public String getIdHospital(int index){
        return this.idHospitales[index];
    }
    public String getIdTipoSonografia(int index){
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
    public void llenarComboBox(javax.swing.JComboBox JCTipoSangre){
            String[] campos = {"id","name_type_telephone"};
            
            Object[][] resultado = (Object[][])  this.mysql.generarSelect("type_of_telephone",campos,"id","asc","");
            Object[][] infoTabla= (Object[][]) resultado[0][0];
          //System.out.println(infoTabla.length);
          //System.out.println(infoTabla[1].length);
          
          this.idTipoTelefono = new String[infoTabla.length];
          for(int i = 0 ; i < infoTabla.length ; i++){
                JCTipoSangre.addItem(infoTabla[i][1]);
                idTipoTelefono[i]= infoTabla[i][0].toString();
               // System.out.println(infoTabla[i][0]+" "+infoTabla[i][1]);
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
    public boolean validarNuevoPrecio(String idHospital, String idTipoSonografia){
         int existe = this.mysql.getValues(this.NombreTabla, "where id_type_of_sonography = '"+idTipoSonografia+"' and id_hospital = '"+idHospital+"' ");
          if(existe < 1){
            return true;
        }else{
            JOptionPane.showMessageDialog(null, "Ya tiene precio esta sonografia");
            return false;
          }
    }
    public void limpiarTexto(javax.swing.JComboBox texto,javax.swing.JTextField texto1,javax.swing.JComboBox texto2){
       texto.setSelectedIndex(0);texto1.setText("");texto2.setSelectedIndex(0);
    }
    public boolean update( String precio,String hospitalID,String tipoSonografiaID, String id){
        boolean respuesta = true;

            if(  (!numeroSeguro.equals(this.tipoSonografiaOld)) ){
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
         
            return respuesta;
       // }
    }
    public boolean procesarInsert(){
        String[] values = {this.numeroSeguro,this.usuario,"now()",this.usuarioID,this.id_patient};
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
    
    
    public boolean procesarUpdate(String precio,String hospitalID,String tipoSonografiaID, String id){
        String[] key = {"price","id_type_of_sonography","id_hospital"};
        String[] values = {precio,tipoSonografiaID,hospitalID};
        System.out.println(" key "+this.key+" Values "+values+" total index "+values.length);
        boolean respuesta = mysql.updateRecord(this.NombreTabla,id,key, values);
        return respuesta;
    }
            
    public void mostrarDatosTabla(JTable table,JLabel JLabelTotal,String palabraBuscar){
        /*
            SELECT poh.id, poh.price, h.name_hospital, tos.name_of_type_sonography

FROM price_of_hospital AS poh

INNER JOIN hospital AS h
ON poh.id_hospital = h.id 

INNER JOIN type_of_sonography AS tos
ON poh.id_type_of_sonography = tos.id
        */
        String[] datos = {"poh.id","poh.price","h.name_hospital","tos.name_of_type_sonography"};
        String select = "poh.id, poh.price,h.name_hospital,tos.name_of_type_sonography";
        String where = " poh.price like '%"+palabraBuscar+"%' and ";
        String tablasJoin = ""+this.NombreTabla+" AS poh " +
                             "INNER JOIN hospital AS h " +
                             "ON poh.id_hospital = h.id " +
                             "INNER JOIN type_of_sonography AS tos " +
                             "ON poh.id_type_of_sonography = tos.id ";
        
        
        Object[][] resultado = (Object[][]) this.mysql.generarSelectMultipleTabla(tablasJoin, datos,select,where,"poh."); /*this.mysql.generarSelect(this.NombreTabla, datos,palabraBuscar,campo)*/;
        Object[][] infoTabla= (Object[][]) resultado[0][0];
        DefaultTableModel modelo = new DefaultTableModel(infoTabla,datos);
        JLabelTotal.setText(resultado[0][1]+"");
        table.setModel(modelo); 
    }
     public boolean procesarInsert(String idHospital,String tipoSonografia, String precio){
        boolean respuesta = this.validarNuevoPrecio(idHospital,tipoSonografia);
         if(respuesta){
       // JOptionPane.showMessageDialog(null, "Ya estamos entrando");
        String[] key = {"price","id_hospital","id_type_of_sonography","when_it","id_user"};
        String[] values = {precio,idHospital,tipoSonografia,"now()",this.usuarioID};
        System.out.println(" key "+this.key+" Values "+values+" total index "+values.length);
            respuesta = mysql.generarInsert(key, values, this.NombreTabla);
         }
        return respuesta;
    }
    public String[] mostrarEditarUsuario(String id){
        //String[] campos ={"t.id","t.telephone","t1.name_type_telephone"};
        String[] campos = {"poh.id","poh.price","h.name_hospital","tos.name_of_type_sonography","h.rnc"};
        String join = ""+this.NombreTabla+" AS poh " +
                             "INNER JOIN hospital AS h " +
                             "ON poh.id_hospital = h.id " +
                             "INNER JOIN type_of_sonography AS tos " +
                             "ON poh.id_type_of_sonography = tos.id ";
        String columnas = "poh.id, poh.price,h.name_hospital,tos.name_of_type_sonography";
        String[] respuesta = this.mysql.generarSelectWithJoin(join,"poh.id, poh.price,h.name_hospital,tos.name_of_type_sonography,h.rnc", id,campos,"poh.");
         this.tipoSonografiaOld = respuesta[2];
          this.hospitalOld = respuesta[3];
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
     /*   String[] datos = {"t.id","t.telephone","t1.name_type_telephone"};
        String select = "t.id, t.telephone, t1.name_type_telephone";
        String where = "t.id_patient = '"+this.id_patient+"' and ";
        String tablasJoin = ""+this.NombreTabla+" as t left join type_of_telephone as t1 on "
                + "t.id_type_of_telephone = t1.id";*/
     String[] datos = {"poh.id","poh.price","h.name_hospital","tos.name_of_type_sonography"};
        String select = " poh.id,poh.price,h.name_hospital,tos.name_of_type_sonography ";
        String where = " ";
        String tablasJoin = ""+this.NombreTabla+" AS poh " +
                             "INNER JOIN hospital AS h " +
                             "ON poh.id_hospital = h.id " +
                             "INNER JOIN type_of_sonography AS tos " +
                             "ON poh.id_type_of_sonography = tos.id ";
        Object[][] resultado = (Object[][]) this.mysql.generarSelectMultipleTabla(tablasJoin, datos,select,where,"poh.");
        //Object[][] resultado = (Object[][]) this.mysql.generarSelect("type_of_blood", datos);
        Object[][] infoTabla= (Object[][]) resultado[0][0];
        DefaultTableModel modelo = new DefaultTableModel(infoTabla,datos);
        JLabelTotal.setText(resultado[0][1]+"");
        table.setModel(modelo);
        
    }
    
}
