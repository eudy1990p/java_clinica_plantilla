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
public class ClassTelefono{
    private conection.Mysql mysql;
    private ValidData valid = new ValidData();
    private String usuario,numeroSeguro,type_user,id,id_patient="1";
    private String[] key = {"telephone","id_type_of_telephone","when_it","id_user","id_patient"};
    private int id_user;
    private int lineas=10;
    private boolean agregar=true;
    private ArrayList<String> camposEdit = new ArrayList<String>();
    private ArrayList<String> valorEdit = new ArrayList<String>();
    private String NombreTabla = " telephone";        
    private String[] idTipoTelefono;
    private String telefonoOld="",tipoOld="";
    
    public ClassTelefono(){
        mysql = new Mysql();
    }
    public ClassTelefono(conection.Mysql mysql){
       this.mysql =mysql;
    }
    public String getIdTipoSangre(int index){
        return this.idTipoTelefono[index];
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
        String[] values = {this.numeroSeguro,this.usuario,"now()",this.id_user+"1",this.id_patient};
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
    
}
