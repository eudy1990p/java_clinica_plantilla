/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conection;


import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Eudy
 */
public class Mysql {

     private static Connection Conexion;
     private String user="root",pass="",db_name="java_hospital_theme_db";
     
     public Mysql(){
         this.MySQLConnection(user, pass, db_name);
     }

    public void MySQLConnection(String user, String pass, String db_name) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db_name, user, pass);
            System.out.println("Se ha iniciado la conexión con el servidor de forma exitosa");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean generarInsert(String[] key, String[] value,String tableName) {
        String values = "";
        String keys = "";
        try {
            for(int i = 0 ; i < value.length ; i++){
                System.out.println(i +" "+value.length);
                if(i == 0){
                    System.out.println("0 Values "+value[i]+" key "+key[i]);
                    keys += key[i];
                    if(value[i].equals("now()")){
                        values += ""+value[i]+"";
                    }else{
                        values += "'"+value[i]+"'";
                    }
                }else{
                    System.out.println("0 + Values "+value[i]+" key "+key[i]);
                    keys += ","+key[i];
                    if(value[i].equals("now()")){
                        values += ","+value[i]+"";
                    }else{
                        values += ",'"+value[i]+"'";
                    }
                }
                
            }
            //System.out.println("Salimos Values "+values+" key "+keys);
            String Query = "INSERT INTO " + tableName + " "
                    + "("+keys+")"
                    + "values ("+values+")";
            System.out.println("Query "+Query);
          /*  String Query = "INSERT INTO " + table_name + " VALUES("
                    + "\"" + ID + "\", "
                    + "\"" + name + "\", "
                    + "\"" + lastname + "\", "
                    + "\"" + age + "\", "
                    + "\"" + gender + "\")";*/
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
            JOptionPane.showMessageDialog(null, "Datos almacenados de forma exitosa");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en el almacenamiento de datos");
            return false;
        }
    }
    
    public void generarSelectLastRow(String table_name,String where,DefaultTableModel model,Object[] columnas,String[] datos) {
        try {
            String Query = "SELECT * FROM " + table_name +" "+where;
            System.out.println(Query);
            Statement st = Conexion.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
            
            
            while (resultSet.next()) {
                    for(int i = 0 ; i < datos.length; i++){
                        columnas[i] = resultSet.getString(datos[i]);
                    }
                    model.addRow(columnas);
               /* System.out.println("ID: " + resultSet.getString("ID") + " "
                        + "Nombre: " + resultSet.getString("Nombre") + " " + resultSet.getString("Apellido") + " "
                        + "Edad: " + resultSet.getString("Edad") + " "
                        + "Sexo: " + resultSet.getString("Sexo"));
                    */
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la adquisición de datos");
        }
    }
    
    public void generarSelect(String table_name,String[] campos,javax.swing.JTable tabla) {
        try {
            String Query = "SELECT * FROM " + table_name;
            Statement st = Conexion.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
            
            resultSet.beforeFirst();  
            resultSet.last();  
            
           System.out.print("GetRow "+resultSet.getRow());
            resultSet.beforeFirst();
            while (resultSet.next()) {
                    System.out.println("ID: " + resultSet.getString("id"));
                    // for(int i = 0 ; i < campos.length; i++){
                      //  columnas[i] = resultSet.getString(datos[i]);
                    //}
                    //model.addRow(columnas);
               /* System.out.println("ID: " + resultSet.getString("ID") + " "
                        + "Nombre: " + resultSet.getString("Nombre") + " " + resultSet.getString("Apellido") + " "
                        + "Edad: " + resultSet.getString("Edad") + " "
                        + "Sexo: " + resultSet.getString("Sexo"));
                    */
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la adquisición de datos");
        }
    }
    
    public void closeConnection() {
        try {
            Conexion.close();
            System.out.println("Se ha finalizado la conexión con el servidor");
        } catch (SQLException ex) {
            Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createDB(String name) {
        try {
            String Query = "CREATE DATABASE " + name;
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
            MySQLConnection("root", "", name);
            JOptionPane.showMessageDialog(null, "Se ha creado la base de datos " + name + " de forma exitosa");
        } catch (SQLException ex) {
            Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createTable(String name) {
        try {
            String Query = "CREATE TABLE " + name + ""
                    + "(ID VARCHAR(25),Nombre VARCHAR(50), Apellido VARCHAR(50),"
                    + " Edad VARCHAR(3), Sexo VARCHAR(1))";
            JOptionPane.showMessageDialog(null, "Se ha creado la base de tabla " + name + " de forma exitosa");
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
        } catch (SQLException ex) {
            Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertData(String table_name, String ID, String name, String lastname, String age, String gender) {
        try {
            String Query = "INSERT INTO " + table_name + " VALUES("
                    + "\"" + ID + "\", "
                    + "\"" + name + "\", "
                    + "\"" + lastname + "\", "
                    + "\"" + age + "\", "
                    + "\"" + gender + "\")";
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
            JOptionPane.showMessageDialog(null, "Datos almacenados de forma exitosa");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en el almacenamiento de datos");
        }
    }

    public void getValues(String table_name) {
        try {
            String Query = "SELECT * FROM " + table_name;
            Statement st = Conexion.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);

            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getString("ID") + " "
                        + "Nombre: " + resultSet.getString("Nombre") + " " + resultSet.getString("Apellido") + " "
                        + "Edad: " + resultSet.getString("Edad") + " "
                        + "Sexo: " + resultSet.getString("Sexo"));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la adquisición de datos");
        }
    }

    public void deleteRecord(String table_name, String ID) {
        try {
            String Query = "DELETE FROM " + table_name + " WHERE ID = \"" + ID + "\"";
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error borrando el registro especificado");
        }
    }

    
}
