/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import app.Classes.ValidData;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
//import app.jframes.JFrameBlood;
import app.jframes.*;

/**
 *
 * @author Eudy
 */
public class Main extends javax.swing.JFrame {
     private conection.Mysql mysql;
     private String[] session;
     private ValidData validador;
    
    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        this.configurations();
    }
    
    public Main(conection.Mysql mysql){
        this.mysql = mysql;
        initComponents();
        this.configurations();
    }
    public Main(conection.Mysql mysql,String[] session){
        this.mysql = mysql;
        this.session = session;
        this.validador = new ValidData();
        System.out.println(session[2]);
        this.validador.validarPermisos(session[2]);
        initComponents();
        this.configurations();
    }
    public void configurations(){
        //maximizar en hambos sentidos
        this.setExtendedState(MAXIMIZED_BOTH);
        
        //pantalla completa
        //GraphicsDevice grafica=GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        //grafica.setFullScreenWindow(this);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu Principal / Tu Sonografia");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setText("Generar Reporte");

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton2.setText("Nueva Sonografia");

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton3.setText("Nuevo Paciente");

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton4.setText("Buscar Sonografia");

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton5.setText("Buscar Paciente");

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton6.setText("Buscar Entrega");

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton7.setText("Generar Entrega");

        jMenu1.setText("Inicio");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jMenuItem2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuItem2.setText("About It");
        jMenuItem2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem2MousePressed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuItem13.setText("Cambiar Clave");
        jMenuItem13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem13MousePressed(evt);
            }
        });
        jMenu1.add(jMenuItem13);

        jMenuItem12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuItem12.setText("Salir");
        jMenuItem12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem12MousePressed(evt);
            }
        });
        jMenu1.add(jMenuItem12);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Sonografia");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jMenuItem1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuItem1.setText("Crear Nueva");
        jMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem1MousePressed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuItem3.setText("Buscar");
        jMenuItem3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem3MousePressed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Reportes");
        jMenu3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jMenuItem8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuItem8.setText("Generar");
        jMenuItem8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem8MousePressed(evt);
            }
        });
        jMenu3.add(jMenuItem8);

        jMenuBar1.add(jMenu3);

        jMenu5.setText("Pacientes");
        jMenu5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jMenuItem4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuItem4.setText("Crear Nuevo");
        jMenuItem4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem4MousePressed(evt);
            }
        });
        jMenu5.add(jMenuItem4);

        jMenuItem5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuItem5.setText("Buscar");
        jMenuItem5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem5MousePressed(evt);
            }
        });
        jMenu5.add(jMenuItem5);

        jMenuBar1.add(jMenu5);

        jMenu4.setText("Entregas");
        jMenu4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jMenuItem6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuItem6.setText("Crear Nueva");
        jMenu4.add(jMenuItem6);

        jMenuItem7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuItem7.setText("Buscar");
        jMenu4.add(jMenuItem7);

        jMenuBar1.add(jMenu4);

        jMenu6.setText("Administración");
        jMenu6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jMenuItem9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuItem9.setText("Hospitales");
        jMenuItem9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem9MousePressed(evt);
            }
        });
        jMenu6.add(jMenuItem9);

        jMenuItem10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuItem10.setText("Tipo De Sangre");
        jMenuItem10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem10MousePressed(evt);
            }
        });
        jMenu6.add(jMenuItem10);

        jMenuItem11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuItem11.setText("Usuarios");
        jMenuItem11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem11MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem11MousePressed(evt);
            }
        });
        jMenu6.add(jMenuItem11);

        jMenuItem15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuItem15.setText("Tipo De Sonografia");
        jMenuItem15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem15MousePressed(evt);
            }
        });
        jMenu6.add(jMenuItem15);

        jMenuItem14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuItem14.setText("Precio Sonografia Por Hospital");
        jMenuItem14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem14MousePressed(evt);
            }
        });
        jMenu6.add(jMenuItem14);

        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(35, 35, 35)
                .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(47, 47, 47)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(46, 46, 46))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(23, 23, 23)
                .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(31, 31, 31)
                .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(288, 288, 288))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(31, 31, 31))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(91, 91, 91))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem11MouseClicked
        // TODO add your handling code here:
      
    }//GEN-LAST:event_jMenuItem11MouseClicked

    private void jMenuItem11MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem11MousePressed
        // TODO add your handling code here:
        if(this.validador.getBtCrearUsuario()){
              JFrameUser usuario =  new JFrameUser(this.mysql);
                    usuario.setDatosUsuario(this.session[0], this.session[1], this.session[3]);
               usuario.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem11MousePressed

    private void jMenuItem10MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem10MousePressed
        // TODO add your handling code here:
        if(this.validador.getBtCrearTypoDeSangre()){
          JFrameBlood sangre =  new JFrameBlood(this.mysql);
          sangre.setDatosUsuario(this.session[0], this.session[1], this.session[3]);
          sangre.setVisible(true);
          
        }
    }//GEN-LAST:event_jMenuItem10MousePressed

    private void jMenuItem9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem9MousePressed
        // TODO add your handling code here:
        
        if(this.validador.getBtCrearHospital()){
            JFrameHospital hospital =new JFrameHospital(this.mysql);
            hospital.setYo(hospital);
            hospital.setDatosUsuario(this.session[0], this.session[1], this.session[3]);
            hospital.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem9MousePressed

    private void jMenuItem4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem4MousePressed
        // TODO add your handling code here:
         if(this.validador.getBtCrearPaciente()){
          JFrameCrearPaciente crearPaciente=  new JFrameCrearPaciente(this.mysql);
          crearPaciente.setDatosUsuario(this.session[0], this.session[1], this.session[3]);
          crearPaciente.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem4MousePressed

    private void jMenuItem1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MousePressed
        // TODO add your handling code here:
        if(this.validador.getBtCrearSonografia()){
          JFrameCrearSonografia sonografia =  new JFrameCrearSonografia(this.mysql);
          sonografia.setYO(sonografia);
          sonografia.setDatosUsuario(this.session[0], this.session[1], this.session[3]);
          sonografia.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem1MousePressed

    private void jMenuItem15MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem15MousePressed
        // TODO add your handling code here:
        //permiso
      JFrameTipoSonografia tipoSonografia =  new JFrameTipoSonografia(this.mysql);
      
      tipoSonografia.setDatosUsuario(this.session[0], this.session[1], this.session[3]);
      tipoSonografia.setVisible(true);
    }//GEN-LAST:event_jMenuItem15MousePressed

    private void jMenuItem14MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem14MousePressed
        // TODO add your handling code here:
        JFramePrecioSonografia precioSonografia =  new JFramePrecioSonografia(this.mysql,this.session[0], this.session[1], this.session[3]);
        precioSonografia.setVisible(true);
    }//GEN-LAST:event_jMenuItem14MousePressed

    private void jMenuItem12MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem12MousePressed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuItem12MousePressed

    private void jMenuItem13MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem13MousePressed
        // TODO add your handling code here:
         JFrameCambiarClave usuario =  new JFrameCambiarClave(this.mysql);
         usuario.setDatosUsuario(this.session[0], this.session[1], this.session[3]);
         usuario.setVisible(true);
    }//GEN-LAST:event_jMenuItem13MousePressed

    private void jMenuItem2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem2MousePressed
        // TODO add your handling code here:
        new AboutCreator().setVisible(true);
    }//GEN-LAST:event_jMenuItem2MousePressed

    private void jMenuItem5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem5MousePressed
        // TODO add your handling code here:
        if(this.validador.getBtBuscarPaciente()){
          JFrameBuscarPaciente crearPaciente=  new JFrameBuscarPaciente(this.mysql);
          crearPaciente.setDatosUsuario(this.session[0], this.session[1], this.session[3]);
          crearPaciente.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem5MousePressed

    private void jMenuItem3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem3MousePressed
        // TODO add your handling code here:
        //JFrameBuscarSonografia
        if(this.validador.getBtBuscarSonografia()){
          JFrameBuscarSonografia buscarSonografia=  new JFrameBuscarSonografia(this.mysql);
          buscarSonografia.setDatosUsuario(this.session[0], this.session[1], this.session[3]);
          buscarSonografia.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem3MousePressed

    private void jMenuItem8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem8MousePressed
        // TODO add your handling code here:
        if(this.validador.getBtGenerarReporte()){
          JFrameGenerarReporte generarReporte=  new JFrameGenerarReporte(this.mysql);
          generarReporte.setDatosUsuario(this.session[0], this.session[1], this.session[3]);
          generarReporte.setVisible(true);
        }
        
    }//GEN-LAST:event_jMenuItem8MousePressed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    // End of variables declaration//GEN-END:variables
}
