/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.jframes;
import app.Classes.ClassGenerarReporte;
/**
 *
 * @author Eudy
 */
import conection.Mysql;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class JFrameGenerarReporte extends javax.swing.JFrame {

    
    private int lineas = 10;
    private String palabraBuscar="",id="";
    private conection.Mysql mysql;
    private ClassGenerarReporte user ;
    private String usuarioID,nombreUsuario,nombreTituloUsuario;

    /**
     * Creates new form User
     */
    public JFrameGenerarReporte() {
        initComponents();
       this.user.mostrarDatosTabla(jTable1,this.jLabel6);
      // this.jtable();
    }
    public JFrameGenerarReporte(conection.Mysql mysql) {
       initComponents();
        this.mysql = mysql;
        user = new ClassGenerarReporte(this.mysql); 
        //this.user.llenarComboBox(this.jComboBoxSangre);
        this.user.llenarComboBoxTipoSonografia(this.jListTipoSonografia);
        this.user.llenarComboBoxCondicionSonografia(this.jComboBoxBuscarCondicionSonografia);
        this.user.llenarComboBoxEstadoSonografia(this.jComboBoxBuscarEstadoSonografia);
        this.user.llenarComboBoxHospital(this.jComboBoxBuscarHospital);
        this.user.llenarComboBoxMedico(this.jComboBoxBuscarMedico);
        this.user.llenarComboBoxSexo(this.jComboBoxBuscarSexo);
        this.user.llenarComboBoxTipoSangre(jComboBoxBuscarTipoSangre);
        
        //this.user.mostrarDatosTablaSonografia(this.jTable1,this.jLabel6);
        this.user.setjTabbedPane1(jTabbedPane1);
        this.jTabbedPane1.setSelectedIndex(1);
        //this.user.mostrarDatosTabla(jTable1,this.jLabel6);
        this.user.setTotal(jLabel6);
        this.user.setjTable1(jTable1);
       
      // this.jtable();
    }
    public void setDatosUsuario(String usuarioID, String nombreUsuario,String nombreTituloUsuario){
        this.usuarioID = usuarioID;
        this.nombreUsuario = nombreUsuario;
        this.nombreTituloUsuario = nombreTituloUsuario;
        //JOptionPane.showMessageDialog(null, "Usuario "+this.usuarioID+" "+this.nombreUsuario+" "+this.nombreTituloUsuario);
        this.user.setDatosUsuario(usuarioID, nombreUsuario, nombreTituloUsuario);
    }
    public void procesarConsulta(int i){
         // TODO add your handling code here:
        String sexo = this.jComboBoxBuscarSexo.getSelectedItem().toString().toLowerCase();
        String tipo_sangre = "",tipo_sonografia ="",idMedico="",idHospital="";
        System.out.println(this.jComboBoxBuscarTipoSangre.getSelectedIndex());
        if(this.jComboBoxBuscarTipoSangre.getSelectedIndex() != 0){
            tipo_sangre = this.user.getIdTipoSangre(this.jComboBoxBuscarTipoSangre.getSelectedIndex()-1);
        }
        tipo_sonografia = this.getSeleted();
        /*if(this.jComboBoxBuscarTipoSonografia.getSelectedIndex() != 0){
            tipo_sonografia = this.user.getIdTipoSonografia(this.jComboBoxBuscarTipoSonografia.getSelectedIndex()-1);
        }*/
        if(this.jComboBoxBuscarMedico.getSelectedIndex() != 0){
            idMedico = this.user.getIdMedico(this.jComboBoxBuscarMedico.getSelectedIndex()-1);
        }
        if(this.jComboBoxBuscarHospital.getSelectedIndex() != 0){
            idHospital = this.user.getIdHospital(this.jComboBoxBuscarHospital.getSelectedIndex()-1);
        }
        String fecha_nacimientoDesde = ((javax.swing.JTextField)this.jDateChooseBuscarrFechaNacimientoDesde.getDateEditor().getUiComponent()).getText();
        String fecha_nacimientoHasta = ((javax.swing.JTextField)this.jDateChooseBuscarrFechaNacimientoHasta.getDateEditor().getUiComponent()).getText();
        String fecha_sonografiaDesde = ((javax.swing.JTextField)this.jDateChooseBuscarrFechaSonografiaDesde.getDateEditor().getUiComponent()).getText();
        String fecha_sonografiaHasta = ((javax.swing.JTextField)this.jDateChooseBuscarrFechaSonografiaHasta.getDateEditor().getUiComponent()).getText();

        this.user.crearBusqueda(this.jTextFieldIDPaciente.getText(),this.jTextFieldBuscarNombre.getText(),this.jTextFieldBuscarApellido.getText(),this.jTextFieldBuscarCedula.getText(),fecha_nacimientoDesde,fecha_nacimientoHasta,sexo,this.jTextFieldBuscarEmail.getText(),this.jTextFieldBuscarTelefono.getText(),this.jTextFieldBuscarDireccionProvincia.getText(),this.jTextFieldBuscarSectorDireccion.getText(),this.jTextFieldBuscarAdressDireccion.getText(),this.jTextFieldBuscarNoSeguro.getText(),this.jTextFieldBuscarNombreSeguro.getText(),tipo_sangre,this.jTextFieldIDSonografia.getText(),this.jTextFieldReferidoPor.getText(),this.jTextFieldBuscarQueContenga.getText(),this.jComboBoxBuscarCondicionSonografia.getSelectedItem().toString(),fecha_sonografiaDesde,fecha_sonografiaHasta,this.jComboBoxBuscarEstadoSonografia.getSelectedItem().toString(),tipo_sonografia,idMedico,idHospital,this.jTextFieldRegistrarImpuesto.getText(),this.jTextFieldRegistrarPorcentaje.getText(),i);
        String[] totales = this.user.getMontos();
        this.jLabelDescuento.setText(totales[1]);
        this.jLabelImpuesto.setText(totales[2]);
        this.jLabelSubTotal.setText(totales[0]);
    }
    public String getSeleted(){
        int[] l = this.jListTipoSonografia.getSelectedIndices();
        String lista="";
        for(int i = 0 ; i < l.length ; i++){
            if(i==0){
                lista += this.user.getIdTipoSonografia(l[i]);
                //JOptionPane.showMessageDialog(null, l.get(i).toString());
            }else{
            lista += ","+this.user.getIdTipoSonografia(l[i]);
            }
        }
        return lista;
    }
    
    /*EJEMPLO SIMPLE PARA MOSTRAR DATOS EN UNA TABLA
    public void jtable(){
            JOptionPane.showMessageDialog(null, "Tabla");
           //DefaultTableModel modelo = (DefaultTableModel) this.jTable1.getModel();
           
           String[] titulos = {"id","usuario"};
           this.lineas = this.lineas + 2;
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
          this.jTable1.setModel(modelo);
    }*/
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        VerHistorico = new javax.swing.JMenuItem();
        EditarEliminarVerTelefono = new javax.swing.JMenuItem();
        EditarEliminarVerEmail = new javax.swing.JMenuItem();
        EditarEliminarVerDireccion = new javax.swing.JMenuItem();
        EditarEliminarVerSeguro = new javax.swing.JMenuItem();
        EliminarPaciente = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabelSubTotal = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabelDescuento = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabelImpuesto = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanelColummna1 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jTextFieldIDPaciente = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldBuscarCedula = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jTextFieldBuscarNoSeguro = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jTextFieldBuscarDireccionProvincia = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jTextFieldBuscarTelefono = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jTextFieldReferidoPor = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jTextFieldIDSonografia = new javax.swing.JTextField();
        jPanelColumna2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldBuscarNombre = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jComboBoxBuscarSexo = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        jTextFieldBuscarSectorDireccion = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jTextFieldBuscarEmail = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jTextFieldBuscarNombreSeguro = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldBuscarQueContenga = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jComboBoxBuscarCondicionSonografia = new javax.swing.JComboBox<>();
        jPanelColumna3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldBuscarApellido = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jDateChooseBuscarrFechaNacimientoDesde = new com.toedter.calendar.JDateChooser();
        jLabel31 = new javax.swing.JLabel();
        jTextFieldBuscarAdressDireccion = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jDateChooseBuscarrFechaSonografiaDesde = new com.toedter.calendar.JDateChooser();
        jLabel39 = new javax.swing.JLabel();
        jComboBoxBuscarEstadoSonografia = new javax.swing.JComboBox<>();
        jLabel42 = new javax.swing.JLabel();
        jDateChooseBuscarrFechaNacimientoHasta = new com.toedter.calendar.JDateChooser();
        jLabel40 = new javax.swing.JLabel();
        jDateChooseBuscarrFechaSonografiaHasta = new com.toedter.calendar.JDateChooser();
        jPanelColumna4 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jComboBoxBuscarHospital = new javax.swing.JComboBox<>();
        jLabel44 = new javax.swing.JLabel();
        jComboBoxBuscarMedico = new javax.swing.JComboBox<>();
        jLabel45 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListTipoSonografia = new javax.swing.JList<>();
        jLabel25 = new javax.swing.JLabel();
        jComboBoxBuscarTipoSangre = new javax.swing.JComboBox<>();
        jLabel43 = new javax.swing.JLabel();
        jTextFieldRegistrarPorcentaje = new javax.swing.JTextField();
        jTextFieldRegistrarImpuesto = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jComboBoxTipoReporte = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        VerHistorico.setText("VerHistorico");
        VerHistorico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                VerHistoricoMousePressed(evt);
            }
        });
        jPopupMenu1.add(VerHistorico);

        EditarEliminarVerTelefono.setText("Editar / Eliminar / Ver Teléfono");
        EditarEliminarVerTelefono.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                EditarEliminarVerTelefonoMousePressed(evt);
            }
        });
        jPopupMenu1.add(EditarEliminarVerTelefono);

        EditarEliminarVerEmail.setText("Editar / Eliminar / Ver Email");
        EditarEliminarVerEmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                EditarEliminarVerEmailMousePressed(evt);
            }
        });
        jPopupMenu1.add(EditarEliminarVerEmail);

        EditarEliminarVerDireccion.setText("Editar / Eliminar / Ver Dirección");
        EditarEliminarVerDireccion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                EditarEliminarVerDireccionMousePressed(evt);
            }
        });
        jPopupMenu1.add(EditarEliminarVerDireccion);

        EditarEliminarVerSeguro.setText("Editar / Eliminar / Ver Seguro");
        EditarEliminarVerSeguro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                EditarEliminarVerSeguroMousePressed(evt);
            }
        });
        jPopupMenu1.add(EditarEliminarVerSeguro);

        EliminarPaciente.setText("EliminarPaciente");
        EliminarPaciente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                EliminarPacienteMousePressed(evt);
            }
        });
        jPopupMenu1.add(EliminarPaciente);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Busccador de Sonografia");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Reporte");

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Nombre de usuario"
            }
        ));
        jTable1.setComponentPopupMenu(jPopupMenu1);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel6.setText("4000");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("Total de Registro");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel6)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabelSubTotal.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabelSubTotal.setText("4000");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel12.setText("Sub Total");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelSubTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabelSubTotal)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabelDescuento.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabelDescuento.setText("4000");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel14.setText("Descuento");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelDescuento, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabelDescuento)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabelImpuesto.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabelImpuesto.setText("4000");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel16.setText("Impuesto");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelImpuesto, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabelImpuesto)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 18, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Resultado de la busqueda", jPanel3);

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("Id Paciente");

        jTextFieldIDPaciente.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTextFieldIDPaciente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldIDPacienteKeyPressed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Cedula Paciente");

        jTextFieldBuscarCedula.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTextFieldBuscarCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldBuscarCedulaKeyPressed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("No. Seguro Paciente");

        jTextFieldBuscarNoSeguro.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTextFieldBuscarNoSeguro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldBuscarNoSeguroKeyPressed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Provincia Paciente");

        jTextFieldBuscarDireccionProvincia.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTextFieldBuscarDireccionProvincia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldBuscarDireccionProvinciaKeyPressed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("Teléfono Paciente");

        jTextFieldBuscarTelefono.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTextFieldBuscarTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldBuscarTelefonoKeyPressed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("Sonografia Referida por");

        jTextFieldReferidoPor.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTextFieldReferidoPor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldReferidoPorKeyPressed(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("Id Sonografia");

        jTextFieldIDSonografia.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTextFieldIDSonografia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldIDSonografiaKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanelColummna1Layout = new javax.swing.GroupLayout(jPanelColummna1);
        jPanelColummna1.setLayout(jPanelColummna1Layout);
        jPanelColummna1Layout.setHorizontalGroup(
            jPanelColummna1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTextFieldBuscarNoSeguro)
            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTextFieldBuscarDireccionProvincia)
            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTextFieldBuscarTelefono)
            .addComponent(jTextFieldBuscarCedula)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTextFieldIDPaciente)
            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTextFieldReferidoPor)
            .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTextFieldIDSonografia)
            .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelColummna1Layout.setVerticalGroup(
            jPanelColummna1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelColummna1Layout.createSequentialGroup()
                .addComponent(jLabel36)
                .addGap(6, 6, 6)
                .addComponent(jTextFieldIDSonografia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel35)
                .addGap(6, 6, 6)
                .addComponent(jTextFieldReferidoPor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel32)
                .addGap(6, 6, 6)
                .addComponent(jTextFieldIDPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel10)
                .addGap(6, 6, 6)
                .addComponent(jTextFieldBuscarCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel27)
                .addGap(10, 10, 10)
                .addComponent(jTextFieldBuscarNoSeguro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel29)
                .addGap(10, 10, 10)
                .addComponent(jTextFieldBuscarDireccionProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel33)
                .addGap(6, 6, 6)
                .addComponent(jTextFieldBuscarTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Nombres Paciente");

        jTextFieldBuscarNombre.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTextFieldBuscarNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldBuscarNombreKeyPressed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Sexo Paciente");

        jComboBoxBuscarSexo.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jComboBoxBuscarSexo.setToolTipText("");

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Sector Paciente");

        jTextFieldBuscarSectorDireccion.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTextFieldBuscarSectorDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldBuscarSectorDireccionKeyPressed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Email Paciente");

        jTextFieldBuscarEmail.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTextFieldBuscarEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldBuscarEmailKeyPressed(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("Nombre Seguro Paciente");

        jTextFieldBuscarNombreSeguro.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTextFieldBuscarNombreSeguro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldBuscarNombreSeguroKeyPressed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Que Contenga");

        jTextFieldBuscarQueContenga.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTextFieldBuscarQueContenga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldBuscarQueContengaKeyPressed(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("Condición Sonografia");

        jComboBoxBuscarCondicionSonografia.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jComboBoxBuscarCondicionSonografia.setToolTipText("");

        javax.swing.GroupLayout jPanelColumna2Layout = new javax.swing.GroupLayout(jPanelColumna2);
        jPanelColumna2.setLayout(jPanelColumna2Layout);
        jPanelColumna2Layout.setHorizontalGroup(
            jPanelColumna2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelColumna2Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(jPanelColumna2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldBuscarNombre, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldBuscarQueContenga)
                    .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxBuscarCondicionSonografia, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jComboBoxBuscarSexo, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
            .addComponent(jTextFieldBuscarNombreSeguro, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTextFieldBuscarSectorDireccion, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTextFieldBuscarEmail, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanelColumna2Layout.setVerticalGroup(
            jPanelColumna2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelColumna2Layout.createSequentialGroup()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldBuscarQueContenga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxBuscarCondicionSonografia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldBuscarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxBuscarSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldBuscarNombreSeguro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldBuscarSectorDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel30)
                .addGap(4, 4, 4)
                .addComponent(jTextFieldBuscarEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Apellidos Paciente");

        jTextFieldBuscarApellido.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTextFieldBuscarApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldBuscarApellidoKeyPressed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Fecha Nacimiento Desde");

        jDateChooseBuscarrFechaNacimientoDesde.setDateFormatString("yyyy-MM-dd");
        jDateChooseBuscarrFechaNacimientoDesde.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jDateChooseBuscarrFechaNacimientoDesde.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jDateChooseBuscarrFechaNacimientoDesdeKeyPressed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Dirección Paciente");

        jTextFieldBuscarAdressDireccion.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTextFieldBuscarAdressDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldBuscarAdressDireccionKeyPressed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("Fecha Sonografia Desde");

        jDateChooseBuscarrFechaSonografiaDesde.setDateFormatString("yyyy-MM-dd");
        jDateChooseBuscarrFechaSonografiaDesde.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jDateChooseBuscarrFechaSonografiaDesde.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jDateChooseBuscarrFechaSonografiaDesdeKeyPressed(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("Estado Sonografia");

        jComboBoxBuscarEstadoSonografia.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jComboBoxBuscarEstadoSonografia.setToolTipText("");

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setText("Fecha Nacimiento Hasta");

        jDateChooseBuscarrFechaNacimientoHasta.setDateFormatString("yyyy-MM-dd");
        jDateChooseBuscarrFechaNacimientoHasta.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jDateChooseBuscarrFechaNacimientoHasta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jDateChooseBuscarrFechaNacimientoHastaKeyPressed(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setText("Fecha Sonografia Hasta");

        jDateChooseBuscarrFechaSonografiaHasta.setDateFormatString("yyyy-MM-dd");
        jDateChooseBuscarrFechaSonografiaHasta.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jDateChooseBuscarrFechaSonografiaHasta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jDateChooseBuscarrFechaSonografiaHastaKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanelColumna3Layout = new javax.swing.GroupLayout(jPanelColumna3);
        jPanelColumna3.setLayout(jPanelColumna3Layout);
        jPanelColumna3Layout.setHorizontalGroup(
            jPanelColumna3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
            .addComponent(jDateChooseBuscarrFechaNacimientoDesde, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTextFieldBuscarAdressDireccion)
            .addComponent(jTextFieldBuscarApellido)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jDateChooseBuscarrFechaSonografiaDesde, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jComboBoxBuscarEstadoSonografia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
            .addComponent(jDateChooseBuscarrFechaNacimientoHasta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jDateChooseBuscarrFechaSonografiaHasta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelColumna3Layout.setVerticalGroup(
            jPanelColumna3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelColumna3Layout.createSequentialGroup()
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooseBuscarrFechaSonografiaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooseBuscarrFechaSonografiaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxBuscarEstadoSonografia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldBuscarApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooseBuscarrFechaNacimientoDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooseBuscarrFechaNacimientoHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldBuscarAdressDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setText("Hospital");

        jComboBoxBuscarHospital.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jComboBoxBuscarHospital.setToolTipText("");

        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("Medico");

        jComboBoxBuscarMedico.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jComboBoxBuscarMedico.setToolTipText("");

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText("Tipo Sonografia");

        jListTipoSonografia.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jListTipoSonografia);

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Tipo de sangre Paciente");

        jComboBoxBuscarTipoSangre.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jComboBoxBuscarTipoSangre.setToolTipText("");

        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("Registrar Porcentaje %");

        jTextFieldRegistrarPorcentaje.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTextFieldRegistrarPorcentaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldRegistrarPorcentajeKeyPressed(evt);
            }
        });

        jTextFieldRegistrarImpuesto.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTextFieldRegistrarImpuesto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldRegistrarImpuestoKeyPressed(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setText("Registrar Impuesto %");

        jLabel47.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setText("Tipo Reporte");

        jComboBoxTipoReporte.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jComboBoxTipoReporte.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sonografia", "Paciente" }));
        jComboBoxTipoReporte.setToolTipText("");

        javax.swing.GroupLayout jPanelColumna4Layout = new javax.swing.GroupLayout(jPanelColumna4);
        jPanelColumna4.setLayout(jPanelColumna4Layout);
        jPanelColumna4Layout.setHorizontalGroup(
            jPanelColumna4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jComboBoxBuscarHospital, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jComboBoxBuscarMedico, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
            .addComponent(jScrollPane2)
            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jComboBoxBuscarTipoSangre, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTextFieldRegistrarPorcentaje)
            .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTextFieldRegistrarImpuesto)
            .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jComboBoxTipoReporte, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelColumna4Layout.setVerticalGroup(
            jPanelColumna4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelColumna4Layout.createSequentialGroup()
                .addComponent(jLabel45)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel44)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxBuscarMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel41)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxBuscarHospital, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxBuscarTipoSangre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldRegistrarPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel46)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldRegistrarImpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel47)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxTipoReporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanelColummna1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelColumna2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelColumna3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelColumna4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanelColummna1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelColumna2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 78, Short.MAX_VALUE))
            .addComponent(jPanelColumna3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelColumna4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Opciones De Reporte", jPanel1);

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("Ver");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setText("Generar");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2)
                        .addComponent(jButton3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void EliminarPacienteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EliminarPacienteMousePressed
        // TODO add your handling code here:
        // TODO add your handling code here:
         // TODO add your handling code here:
        int fila = this.jTable1.getSelectedRow();
         
        String id = this.jTable1.getValueAt(fila, 0).toString();
        String user = this.jTable1.getValueAt(fila, 0).toString();
        
        this.id = id;
        int respuesta = JOptionPane.showConfirmDialog(null, "Desea eliminar este registro?", "Eliminar registro", JOptionPane.YES_NO_OPTION);
        if(respuesta == JOptionPane.YES_OPTION){
            boolean eliminado = this.user.elimnar(id);
            if(eliminado){
                JOptionPane.showMessageDialog(null, "Se elimino correctamente el registro.");
                this.user.mostrarDatosTabla(this.jTable1, this.jLabel6);
                this.user.setAgregar(true);
                //this.user.cambiarTestoParaEditar(this.jButton1,this.jLabel9,this.jTextFieldNombre);
                //this.user.limpiarTexto(this.jTextFieldNombre, this.jTextFieldApellido,this.jTextFieldTelefonoCelular,this.jTextFieldEmail2,this.jTextFieldCedula,this.jTextFieldTelefonoCasa,this.jTextFieldProvincia,this.jTextFieldSector,this.jTextFieldDireccion,this.jComboBoxSexo,this.jComboBoxSangre,this.jTextFieldNombreSeguro,this.jTextFieldNumeroSeguro,this.jDateChooserFechaNacimiento,this.jTextFieldTelefonoTrabajo,this.jTextFieldEmail1);

            }else{
                 JOptionPane.showMessageDialog(null, "No se pudo eliminar correctamente el registro.");           
            }
        }
        System.out.println("prueba "+id+" prueba ");
    }//GEN-LAST:event_EliminarPacienteMousePressed

    private void EditarEliminarVerEmailMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditarEliminarVerEmailMousePressed
        // TODO add your handling code here:
        int fila = this.jTable1.getSelectedRow();
        String id_patient = this.jTable1.getValueAt(fila, 0).toString();
        new JFrameEmail(mysql,id_patient,this.usuarioID,this.nombreUsuario,this.nombreTituloUsuario).setVisible(true);
    }//GEN-LAST:event_EditarEliminarVerEmailMousePressed

    private void EditarEliminarVerSeguroMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditarEliminarVerSeguroMousePressed
        // TODO add your handling code here:
        int fila = this.jTable1.getSelectedRow();
        String id_patient = this.jTable1.getValueAt(fila, 0).toString();
        new JFrameSeguro(mysql,id_patient,this.usuarioID,this.nombreUsuario,this.nombreTituloUsuario).setVisible(true);
                                                     
    }//GEN-LAST:event_EditarEliminarVerSeguroMousePressed

    private void EditarEliminarVerDireccionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditarEliminarVerDireccionMousePressed
        // TODO add your handling code here:
        int fila = this.jTable1.getSelectedRow();
        String id_patient = this.jTable1.getValueAt(fila, 0).toString();
        new JFrameDireccion(mysql,id_patient,this.usuarioID,this.nombreUsuario,this.nombreTituloUsuario).setVisible(true);
    }//GEN-LAST:event_EditarEliminarVerDireccionMousePressed

    private void EditarEliminarVerTelefonoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditarEliminarVerTelefonoMousePressed
        // TODO add your handling code here:
         int fila = this.jTable1.getSelectedRow();
        String id_patient = this.jTable1.getValueAt(fila, 0).toString();
        new JFrameTelefono(mysql,id_patient,this.usuarioID,this.nombreUsuario,this.nombreTituloUsuario).setVisible(true);
        
    }//GEN-LAST:event_EditarEliminarVerTelefonoMousePressed

    private void VerHistoricoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VerHistoricoMousePressed
        // TODO add your handling code here:
        int fila = this.jTable1.getSelectedRow();
        String id_patient = this.jTable1.getValueAt(fila, 0).toString();
        new JFrameHistorialPaciente(mysql,id_patient).setVisible(true);
    }//GEN-LAST:event_VerHistoricoMousePressed

    private void jTextFieldIDPacienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldIDPacienteKeyPressed
        // TODO add your handling code here:
        this.user.buscadorEnter(evt, 1, this.jTextFieldIDPaciente.getText());
    }//GEN-LAST:event_jTextFieldIDPacienteKeyPressed

    private void jTextFieldBuscarNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscarNombreKeyPressed
        // TODO add your handling code here:
        this.user.buscadorEnter(evt, 2, this.jTextFieldBuscarNombre.getText());
    }//GEN-LAST:event_jTextFieldBuscarNombreKeyPressed

    private void jTextFieldBuscarApellidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscarApellidoKeyPressed
        // TODO add your handling code here:
       this.user.buscadorEnter(evt, 3, this.jTextFieldBuscarApellido.getText());
    }//GEN-LAST:event_jTextFieldBuscarApellidoKeyPressed

    private void jTextFieldBuscarCedulaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscarCedulaKeyPressed
        // TODO add your handling code here:
        this.user.buscadorEnter(evt, 4, this.jTextFieldBuscarCedula.getText());

    }//GEN-LAST:event_jTextFieldBuscarCedulaKeyPressed

    private void jDateChooseBuscarrFechaNacimientoDesdeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDateChooseBuscarrFechaNacimientoDesdeKeyPressed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "evento press activo");
       String fecha_nacimiento = ((javax.swing.JTextField)this.jDateChooseBuscarrFechaNacimientoDesde.getDateEditor().getUiComponent()).getText();
        this.user.buscadorEnter(evt, 5, fecha_nacimiento);
    }//GEN-LAST:event_jDateChooseBuscarrFechaNacimientoDesdeKeyPressed

    private void jTextFieldBuscarNoSeguroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscarNoSeguroKeyPressed
        // TODO add your handling code here:
        this.user.buscadorEnter(evt, 8, this.jTextFieldBuscarNoSeguro.getText());
    }//GEN-LAST:event_jTextFieldBuscarNoSeguroKeyPressed

    private void jTextFieldBuscarSectorDireccionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscarSectorDireccionKeyPressed
        // TODO add your handling code here:
        this.user.buscadorEnter(evt, 7, this.jTextFieldBuscarSectorDireccion.getText());        
    }//GEN-LAST:event_jTextFieldBuscarSectorDireccionKeyPressed

    private void jTextFieldBuscarTelefonoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscarTelefonoKeyPressed
        // TODO add your handling code here:
        this.user.buscadorEnter(evt, 6, this.jTextFieldBuscarTelefono.getText());
    }//GEN-LAST:event_jTextFieldBuscarTelefonoKeyPressed

    private void jTextFieldBuscarDireccionProvinciaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscarDireccionProvinciaKeyPressed
        // TODO add your handling code here:
        this.user.buscadorEnter(evt, 9, this.jTextFieldBuscarDireccionProvincia.getText());
    }//GEN-LAST:event_jTextFieldBuscarDireccionProvinciaKeyPressed

    private void jTextFieldBuscarEmailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscarEmailKeyPressed
        // TODO add your handling code here:
        this.user.buscadorEnter(evt, 10, this.jTextFieldBuscarEmail.getText());
    }//GEN-LAST:event_jTextFieldBuscarEmailKeyPressed

    private void jTextFieldBuscarAdressDireccionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscarAdressDireccionKeyPressed
        // TODO add your handling code here:
        this.user.buscadorEnter(evt, 11, this.jTextFieldBuscarAdressDireccion.getText());        
    }//GEN-LAST:event_jTextFieldBuscarAdressDireccionKeyPressed

    private void jTextFieldBuscarNombreSeguroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscarNombreSeguroKeyPressed
        // TODO add your handling code here:
        this.user.buscadorEnter(evt, 12, this.jTextFieldBuscarNombreSeguro.getText());
    }//GEN-LAST:event_jTextFieldBuscarNombreSeguroKeyPressed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
       this.procesarConsulta(0);
    }//GEN-LAST:event_jButton2MouseClicked

    private void jTextFieldReferidoPorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldReferidoPorKeyPressed
        // TODO add your handling code here:
        this.user.buscadorEnter(evt, 15, this.jTextFieldReferidoPor.getText());
    }//GEN-LAST:event_jTextFieldReferidoPorKeyPressed

    private void jTextFieldIDSonografiaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldIDSonografiaKeyPressed
        // TODO add your handling code here:
        this.user.buscadorEnter(evt, 13, this.jTextFieldIDSonografia.getText());
    }//GEN-LAST:event_jTextFieldIDSonografiaKeyPressed

    private void jTextFieldBuscarQueContengaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscarQueContengaKeyPressed
        // TODO add your handling code here:
        this.user.buscadorEnter(evt, 14, this.jTextFieldBuscarQueContenga.getText());
    }//GEN-LAST:event_jTextFieldBuscarQueContengaKeyPressed

    private void jDateChooseBuscarrFechaSonografiaDesdeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDateChooseBuscarrFechaSonografiaDesdeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooseBuscarrFechaSonografiaDesdeKeyPressed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int select = this.jTable1.rowAtPoint(evt.getPoint());
        String id = this.jTable1.getValueAt(select, 0).toString();
        this.user.mostrarSonografiaPDF(id);
        //int select = this.jTable1.rowAtPoint(evt.getPoint());
        //String id = this.jTable1.getValueAt(select, 0).toString();
        //this.id = id;
        //System.out.println("Click editar registro "+id);
        // String[] respuesta = this.user.mostrarEditarPaciente(id);
        //this.user.cambiarTestoParaEditar(this.jButton1,this.jLabel9,this.jTextFieldNombre);

        //this.jTextFieldNombre.setText(respuesta[1]);
        //this.jTextFieldCedula.setText(respuesta[3]);
        //this.jTextField4.setText(respuesta[4]);
        //this.jTextFieldApellido.setText(respuesta[2]);
        //this.jComboBoxSexo.setSelectedItem(respuesta[5]);
        //this.jTextArea1.setText(respuesta[6]);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jDateChooseBuscarrFechaSonografiaHastaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDateChooseBuscarrFechaSonografiaHastaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooseBuscarrFechaSonografiaHastaKeyPressed

    private void jDateChooseBuscarrFechaNacimientoHastaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDateChooseBuscarrFechaNacimientoHastaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooseBuscarrFechaNacimientoHastaKeyPressed

    private void jTextFieldRegistrarPorcentajeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldRegistrarPorcentajeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldRegistrarPorcentajeKeyPressed

    private void jTextFieldRegistrarImpuestoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldRegistrarImpuestoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldRegistrarImpuestoKeyPressed

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        // TODO add your handling code here:
        this.procesarConsulta(1);
    }//GEN-LAST:event_jButton3MouseClicked

    
    /**
     * @param args the command line arguments
     */
   //public static void  main(String[] a){
   //   new JFrameGenerarReporte().setVisible(true);
  // }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem EditarEliminarVerDireccion;
    private javax.swing.JMenuItem EditarEliminarVerEmail;
    private javax.swing.JMenuItem EditarEliminarVerSeguro;
    private javax.swing.JMenuItem EditarEliminarVerTelefono;
    private javax.swing.JMenuItem EliminarPaciente;
    private javax.swing.JMenuItem VerHistorico;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBoxBuscarCondicionSonografia;
    private javax.swing.JComboBox<String> jComboBoxBuscarEstadoSonografia;
    private javax.swing.JComboBox<String> jComboBoxBuscarHospital;
    private javax.swing.JComboBox<String> jComboBoxBuscarMedico;
    private javax.swing.JComboBox<String> jComboBoxBuscarSexo;
    private javax.swing.JComboBox<String> jComboBoxBuscarTipoSangre;
    private javax.swing.JComboBox<String> jComboBoxTipoReporte;
    private com.toedter.calendar.JDateChooser jDateChooseBuscarrFechaNacimientoDesde;
    private com.toedter.calendar.JDateChooser jDateChooseBuscarrFechaNacimientoHasta;
    private com.toedter.calendar.JDateChooser jDateChooseBuscarrFechaSonografiaDesde;
    private com.toedter.calendar.JDateChooser jDateChooseBuscarrFechaSonografiaHasta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelDescuento;
    private javax.swing.JLabel jLabelImpuesto;
    private javax.swing.JLabel jLabelSubTotal;
    private javax.swing.JList<String> jListTipoSonografia;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanelColummna1;
    private javax.swing.JPanel jPanelColumna2;
    private javax.swing.JPanel jPanelColumna3;
    private javax.swing.JPanel jPanelColumna4;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldBuscarAdressDireccion;
    private javax.swing.JTextField jTextFieldBuscarApellido;
    private javax.swing.JTextField jTextFieldBuscarCedula;
    private javax.swing.JTextField jTextFieldBuscarDireccionProvincia;
    private javax.swing.JTextField jTextFieldBuscarEmail;
    private javax.swing.JTextField jTextFieldBuscarNoSeguro;
    private javax.swing.JTextField jTextFieldBuscarNombre;
    private javax.swing.JTextField jTextFieldBuscarNombreSeguro;
    private javax.swing.JTextField jTextFieldBuscarQueContenga;
    private javax.swing.JTextField jTextFieldBuscarSectorDireccion;
    private javax.swing.JTextField jTextFieldBuscarTelefono;
    private javax.swing.JTextField jTextFieldIDPaciente;
    private javax.swing.JTextField jTextFieldIDSonografia;
    private javax.swing.JTextField jTextFieldReferidoPor;
    private javax.swing.JTextField jTextFieldRegistrarImpuesto;
    private javax.swing.JTextField jTextFieldRegistrarPorcentaje;
    // End of variables declaration//GEN-END:variables

}
