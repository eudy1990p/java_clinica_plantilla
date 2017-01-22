/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.Classes;

import app.jframes.JFrameHospital;
import conection.Mysql;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.lang.Object;
import java.util.ArrayList;
import app.jframes.viewSelectFile;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Eudy
 */
public class ClassHospital{
    private conection.Mysql mysql;
    private ValidData valid = new ValidData();
    private String nombre,telefono,email, rnc, paginaWeb, direccion,id,RutaIcono,eslogan;
    private String[] key = {"id","name_hospital","rnc","telephone","email","web_page","address","icono_url","eslogan"};
   
    private int lineas=10;
    private boolean agregar=true;
    private ArrayList<String> camposEdit = new ArrayList<String>();
    private ArrayList<String> valorEdit = new ArrayList<String>();
    private String usuarioID,nombreUsuario,nombreTituloUsuario;
    private ClassHospital yo;
    private app.jframes.JFrameHospital padre;

    public JFrameHospital getPadre() {
        return padre;
    }

    public void setPadre(JFrameHospital padre) {
        this.padre = padre;
    }

    public ClassHospital getYo() {
        return yo;
    }

    public void setYo(ClassHospital yo) {
        this.yo = yo;
    }

            
    public ClassHospital(){
        mysql = new Mysql();
    }
    public ClassHospital(conection.Mysql mysql,app.jframes.JFrameHospital padre){
       this.mysql =mysql;
       this.padre = padre;
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
    public void setDatosUsuario(String usuarioID, String nombreUsuario,String nombreTituloUsuario){
        this.usuarioID = usuarioID;
        this.nombreUsuario = nombreUsuario;
        this.nombreTituloUsuario = nombreTituloUsuario;
        //JOptionPane.showMessageDialog(null, "Usuario "+this.usuarioID+" "+this.nombreUsuario+" "+this.nombreTituloUsuario);
        
    }
    public boolean insert(String nombre,String telefono,String email,String rnc,String paginaWeb,String direccion,String rutaIcono, String eslogan){
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
                    this.RutaIcono = rutaIcono;
                    this.eslogan = eslogan;
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
    public boolean update( String nombre,String telefono,String email,String rnc,String paginaWeb,String direccion,String id,String rutaIcono,String eslogan){
        
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
        if(valid.validEmpty(rutaIcono)){
          this.valorEdit.add(rutaIcono);
          this.camposEdit.add("icono_url");
        }
        
         if(valid.validEmpty(eslogan)){
          this.valorEdit.add(eslogan);
          this.camposEdit.add("eslogan");
        }
            this.direccion = direccion;
            this.paginaWeb = paginaWeb;
            this.rnc = rnc;
            this.email = email;
            this.telefono = telefono;
            this.nombre = nombre;
            this.RutaIcono = rutaIcono;
            this.eslogan = eslogan;
            this.id = id;
            JOptionPane.showMessageDialog(null,"Update img "+rutaIcono );
            boolean respuesta = this.procesarUpdate();
            return respuesta;
       // }
    }
    public boolean procesarInsert(){
        String[] key = {"name_hospital","telephone","email","address","web_page","when_it","rnc","id_user","icono_url","eslogan"};
        String[] values = {this.nombre,this.telefono,this.email,this.direccion,this.paginaWeb,"now()",this.rnc,this.usuarioID,this.RutaIcono,this.eslogan};
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
            //System.err.println(" key "+this.key[i]+" Values "+values[i]);
        }
        
        JOptionPane.showMessageDialog(null,"Update img value "+values+" key "+ key);
        boolean respuesta = mysql.updateRecord("hospital",this.id,key, values);
        return respuesta;
    }
    
    public void mostrarDatosTabla(JTable table,JLabel JLabelTotal,String palabraBuscar){
        String[] datos = {"id","name_hospital","rnc","telephone","email","web_page","address","eslogan"};
        String campo = "name_hospital";
        System.out.println(palabraBuscar);
        Object[][] resultado = (Object[][]) this.mysql.generarSelect("hospital", datos,palabraBuscar,campo);
        Object[][] infoTabla= (Object[][]) resultado[0][0];
        DefaultTableModel modelo = new DefaultTableModel(infoTabla,datos);
        JLabelTotal.setText(resultado[0][1]+"");
        table.setModel(modelo); 
    }
    public String[] mostrarEditarUsuario(String id){
        String[] campos = {"id","name_hospital","rnc","telephone","email","web_page","address","icono_url","eslogan"};
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
        /*String[] titulos = {"id","usuario"};
          //modelo.setColumnIdentifiers(titulos);
           Object[][] fila = new Object[this.lineas][2];
           for(int c = 0 ; c < this.lineas ; c++){
               System.out.println("---- x "+ c);
                //for(int i = 0 ; i < 2 ; i++){
                    fila[c][0] = "x "+ c+" y ";
                    //System.out.println("x "+ c+" y "+i);
                    fila[c][1]  = "Eudy";
                    //modelo.addRow(fila);
                //}
           }
          DefaultTableModel modelo = new DefaultTableModel(fila,titulos);
          table.setModel(modelo);*/


        //table.setModel(null);
        //DefaultTableModel modal = (DefaultTableModel) table.getModel();
        //table.setModel(new DefaultTableModel());
        //table.setModel(modal);
        //DefaultTableModel modal = (DefaultTableModel) table.getModel();
        
        //Object[] columnas = new Object[modal.getColumnCount()];
        //{"name_hospital","password_user","when_it","id_user","type_of_user"}
        String[] datos = {"id","name_hospital","rnc","telephone","email","web_page","address"};
        
        Object[][] resultado = (Object[][]) this.mysql.generarSelect("hospital", datos);
        Object[][] infoTabla= (Object[][]) resultado[0][0];
        DefaultTableModel modelo = new DefaultTableModel(infoTabla,datos);
        JLabelTotal.setText(resultado[0][1]+"");
        table.setModel(modelo);
       // JLabelTotal.setText(""+modal.getRowCount());
        
    }
    
   /* public void mostrarNuevoDatoTabla(JTable table,JLabel JLabelTotal){
        //table.setModel(this.getBasicModel());
        DefaultTableModel modal = (DefaultTableModel) table.getModel();
        DefaultTableModel t = new DefaultTableModel();
        table.setModel(t);
        table.setModel(modal);
        
        
        Object[] columnas = new Object[modal.getColumnCount()];
        //{"name_hospital","password_user","when_it","id_user","type_of_user"}
        String[] datos = {"id","name_hospital"};
        String where = "where id = (SELECT max(id) from users)";
        this.mysql.generarSelectLastRow("users",where, modal, columnas,datos);
        JLabelTotal.setText(""+modal.getRowCount());
        
    }*/
  private javax.swing.JLabel l;
  public void cargarImagen(javax.swing.JLabel l){
      this.l = l;
       viewSelectFile chooser = new viewSelectFile(yo);
       chooser.setVisible(true);
      // String path = chooser.getNameFile();
       //   this.setImage(path);
       
  }
  private boolean willClick = true;
  
  public void restartWillClick(){
        this.willClick = true;
    }
  public void setImage(String path,javax.swing.JLabel l){
        this.ScaleImage(l,path);
        this.RutaIcono = path;
        this.padre.setRutaIcono(this.RutaIcono);
    }  
  public void setImage(String path){
        this.ScaleImage(this.l,path);
        this.RutaIcono = path;
        this.padre.setRutaIcono(this.RutaIcono);
    }
    public void ScaleImage(JLabel jLabel,String path){
        try{
            jLabel.setText("");
            ImageIcon imagen = new ImageIcon(path); 
            Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(jLabel.getWidth(),jLabel.getHeight(),Image.SCALE_DEFAULT));
            jLabel.setIcon(icono); 
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error "+e);
        }
    }
    
}