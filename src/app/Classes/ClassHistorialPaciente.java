/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.Classes;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Eudy
 */
public class ClassHistorialPaciente {
    private conection.Mysql mysql;
    private String id_patient;
    
    public ClassHistorialPaciente(conection.Mysql mysql,String id_patient){
            this.mysql = mysql;
            this.id_patient = id_patient;
    }
    
    public void mostrarDatosTablaTelefono(JTable table){
        String[] datos = {"t.id","t.telephone","t1.name_type_telephone"};
        String select = "t.id, t.telephone, t1.name_type_telephone";
        String where = "t.id_patient = '"+this.id_patient+"' and ";
        String tablasJoin = "telephone as t left join type_of_telephone as t1 on "
                + "t.id_type_of_telephone = t1.id";
        Object[][] resultado = (Object[][]) this.mysql.generarSelectMultipleTabla(tablasJoin, datos,select,where,"t.");
        //Object[][] resultado = (Object[][]) this.mysql.generarSelect("type_of_blood", datos);
        Object[][] infoTabla= (Object[][]) resultado[0][0];
        DefaultTableModel modelo = new DefaultTableModel(infoTabla,datos);
        table.setModel(modelo);
    }
    
    public void mostrarDatosTablaEmail(JTable table){
        String[] datos = {"id","email"};
        String where = "id_patient = '"+this.id_patient+"' and ";
        Object[][] resultado = (Object[][]) this.mysql.generarSelect("email", datos,where);
        //Object[][] resultado = (Object[][]) this.mysql.generarSelect("type_of_blood", datos);
        Object[][] infoTabla= (Object[][]) resultado[0][0];
        DefaultTableModel modelo = new DefaultTableModel(infoTabla,datos);
        table.setModel(modelo); 
    }
    
    public void mostrarDatosTablaSeguro(JTable table){
        String[] datos = {"id","name_insurance_patient","insurance_number"};
        String where = "id_patient = '"+this.id_patient+"' and ";
        Object[][] resultado = (Object[][]) this.mysql.generarSelect("insurance_patient", datos,where);
        //Object[][] resultado = (Object[][]) this.mysql.generarSelect("type_of_blood", datos);
        Object[][] infoTabla= (Object[][]) resultado[0][0];
        DefaultTableModel modelo = new DefaultTableModel(infoTabla,datos);
        table.setModel(modelo);
        
    }
     public void mostrarDatosTablaDireccion(JTable table){
        String[] datos = {"id","sector","province","address"};
        String where = "id_patient = '"+this.id_patient+"' and ";
        Object[][] resultado = (Object[][]) this.mysql.generarSelect("address", datos,where);
        //Object[][] resultado = (Object[][]) this.mysql.generarSelect("type_of_blood", datos);
        Object[][] infoTabla= (Object[][]) resultado[0][0];
        DefaultTableModel modelo = new DefaultTableModel(infoTabla,datos);
        table.setModel(modelo);
    }
    
    public String[] motrarDatoPaciente(){
        String[] campos ={"t.id","t.name_patient","t.last_patient","t.document_id","t.date_of_birth","t.sex","t1.name_of_blood","edad"};
        String join = "patient as t left join type_of_blood as t1 "
                + "on t.type_of_blood_id = t1.id";
        String columnas = "t.id,t.name_patient,t.last_patient,t.document_id,t.date_of_birth,t.sex,t1.name_of_blood,edad";
        String camposSelect="t.id,t.name_patient,t.last_patient,t.document_id,t.date_of_birth,t.sex,t1.name_of_blood,(CURDATE() -t.date_of_birth) as edad";
        String[] respuesta = this.mysql.generarSelectWithJoin(join,camposSelect,this.id_patient,campos,"t.");
        return respuesta;
    }
    
    public void cargarDatoPaciente(JLabel l1,JLabel l2,JLabel l3,JLabel l4,JLabel l5,JLabel l6){
       String[] paciente = this.motrarDatoPaciente();
       l1.setText(paciente[1]+" "+paciente[2]);
       l2.setText(paciente[3]);
       l3.setText(paciente[4]);
       l4.setText(paciente[5]);
       l5.setText(paciente[6]);
       l6.setText(paciente[7]);
    }
    
}
