/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.Classes;

import app.jframes.JFrameCrearSonografia;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

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
    
    public void mostrarDatosTablaSonografia(JTable table){
        String[] datos = {"s.id","s.condition_sonography","s.status","tos.name_of_type_sonography","h.name_hospital","h.rnc","medico"};
        String select = "s.id, s.condition_sonography, " +
                        "s.status, " +
                        "tos.name_of_type_sonography, " +
                        "h.name_hospital, " +
                        "h.rnc, " +
                        "u.nombre_titulo AS medico";
        String where = " ";
        
        String tablasJoin = "sonography AS s " +
                            "INNER JOIN type_of_sonography AS tos " +
                            "ON s.id_type_of_sonography = tos.id " +
                            "INNER JOIN hospital AS h " +
                            "ON s.id_hospital = h.id " +
                            "INNER JOIN users AS u " +
                            "ON s.id_user = u.id";
        
        Object[][] resultado = (Object[][]) this.mysql.generarSelectMultipleTabla(tablasJoin, datos,select,where,"s.");
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
        String[] campos ={"t.id","t.name_patient","t.last_patient","t.document_id","fecha_nacimiento","t.sex","t1.name_of_blood","edad"};
        String join = "patient as t left join type_of_blood as t1 "
                + "on t.type_of_blood_id = t1.id";
        String columnas = "t.id,t.name_patient,t.last_patient,t.document_id,fecha_nacimiento,t.sex,t1.name_of_blood,edad";
        String camposSelect="t.id,t.name_patient,t.last_patient,t.document_id,"
                + " DATE_FORMAT(t.date_of_birth,'%d-%m-%Y') AS fecha_nacimiento,"
                + "t.sex,t1.name_of_blood," +
            "IF(TIMESTAMPDIFF( YEAR,t.date_of_birth, CURDATE() ) < 1 , " +
            "IF(TIMESTAMPDIFF( MONTH,t.date_of_birth, CURDATE() ) < 1 , " +
            "IF(TIMESTAMPDIFF( DAY,t.date_of_birth, CURDATE() ) < 1 ,' Horas', " +
            "CONCAT(TIMESTAMPDIFF(DAY, t.date_of_birth, CURDATE()) , ' Dias' ) ), " +
            "CONCAT(TIMESTAMPDIFF(MONTH, t.date_of_birth, CURDATE()) , ' Meses' ) ), " +
            "CONCAT(TIMESTAMPDIFF(YEAR, t.date_of_birth, CURDATE()) , ' Años' ) ) AS edad";
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
}
