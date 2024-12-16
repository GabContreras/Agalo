/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista;

import controlador.CtrlAdministrarEmpresas;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import modelo.AdministrarEmpresas;

/**
 *
 * @author Estudiante
 */
public class FrmDashboard extends javax.swing.JPanel {

    /**
     * Creates new form Dashboard
     */
    private static final String FUENTE_SEGOE = "Segoe UI";
    private static final String FUENTE_SANSS = "SansSerif";

    private static final String BUSQUEDA = "Buscar";

    public FrmDashboard() {

        initComponents();

        FrmDashboard frmDashboard = this;
        AdministrarEmpresas modelo = new AdministrarEmpresas();
        new CtrlAdministrarEmpresas(modelo, frmDashboard);

        UIManager.getSystemLookAndFeelClassName();

        jtSolicitudEmpresa.setBackground(java.awt.Color.WHITE); // Cambia el fondo de las celdas de la tabla
        jtSolicitudEmpresa.setFillsViewportHeight(true); // Asegura que el fondo cubra toda el área
        jtSolicitudEmpresa.getParent().setBackground(java.awt.Color.WHITE);

        jScrollPane3.setBorder(javax.swing.BorderFactory.createEmptyBorder()); // Establecer borde vacío

        // Personalizar el encabezado de la tabla
        jtSolicitudEmpresa.getTableHeader().setFont(new Font(FUENTE_SEGOE, Font.BOLD, 12));
        jtSolicitudEmpresa.getTableHeader().setBackground(java.awt.Color.WHITE);
        jtSolicitudEmpresa.setRowHeight(45);

        // Configurar las líneas horizontales de la tabla
        jtSolicitudEmpresa.setGridColor(new java.awt.Color(230, 230, 230)); // Líneas gris claro
        jtSolicitudEmpresa.setShowHorizontalLines(true); // Mostrar líneas horizontales
        jtSolicitudEmpresa.setShowVerticalLines(false); // Ocultar líneas verticales si lo deseas

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(java.awt.Color.WHITE); // Fondo blanco
        headerRenderer.setForeground(new Color(0, 0, 0)); // Texto en color negro
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        headerRenderer.setFont(new Font(FUENTE_SEGOE, Font.BOLD, 12));

        for (int i = 0; i < jtSolicitudEmpresa.getColumnModel().getColumnCount(); i++) {
            jtSolicitudEmpresa.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        jtSolicitudEmpresa.setRowHeight(25);
        jtSolicitudEmpresa.setGridColor(new Color(230, 230, 230));

    }

    public void initDashboard() {
        FrmDashboard frmDashboard = new FrmDashboard();
        AdministrarEmpresas modelo = new AdministrarEmpresas();
        new CtrlAdministrarEmpresas(modelo, frmDashboard);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtEmpresas = new javax.swing.JLabel();
        txtUsers = new javax.swing.JLabel();
        txtWorks = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        btnAceptarSolicitud = new custom.Button();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtSolicitudEmpresa = new javax.swing.JTable();
        txtBuscarEmpresa = new custom.TextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        btnRechazarSolicitud = new custom.Button();
        btnActu = new custom.Button();
        txtCorreo = new javax.swing.JLabel();
        txtNombre = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        button2 = new custom.Button();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(250, 249, 249));
        setMaximumSize(new java.awt.Dimension(1030, 770));
        setMinimumSize(new java.awt.Dimension(1120, 780));
        setPreferredSize(new java.awt.Dimension(1120, 780));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(250, 249, 249));
        jPanel3.setMaximumSize(new java.awt.Dimension(1030, 770));
        jPanel3.setMinimumSize(new java.awt.Dimension(1120, 780));
        jPanel3.setPreferredSize(new java.awt.Dimension(1120, 780));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font(FUENTE_SANSS, 0, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(204, 204, 204));
        jLabel16.setText("Usuarios ");
        jLabel16.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 220, 60, -1));

        jLabel17.setBackground(new java.awt.Color(204, 204, 204));
        jLabel17.setFont(new java.awt.Font(FUENTE_SANSS, 0, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(204, 204, 204));
        jLabel17.setText("Registrados");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 230, 70, -1));

        jLabel18.setFont(new java.awt.Font(FUENTE_SANSS, 0, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(204, 204, 204));
        jLabel18.setText("Empresas");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 220, -1, -1));

        jLabel19.setFont(new java.awt.Font(FUENTE_SANSS, 0, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(204, 204, 204));
        jLabel19.setText("Asociadas");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 230, -1, -1));

        jLabel20.setFont(new java.awt.Font(FUENTE_SANSS, 0, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(204, 204, 204));
        jLabel20.setText("Trabajos");
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 220, -1, -1));

        jLabel21.setFont(new java.awt.Font(FUENTE_SANSS, 0, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(204, 204, 204));
        jLabel21.setText("Publicados");
        jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 230, -1, -1));

        txtEmpresas.setFont(new java.awt.Font(FUENTE_SEGOE, 1, 33)); // NOI18N
        txtEmpresas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtEmpresas.setText("4567");
        jPanel3.add(txtEmpresas, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 180, 100, -1));

        txtUsers.setFont(new java.awt.Font(FUENTE_SEGOE, 1, 33)); // NOI18N
        txtUsers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtUsers.setText("1233");
        txtUsers.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel3.add(txtUsers, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 180, 90, -1));

        txtWorks.setFont(new java.awt.Font(FUENTE_SEGOE, 1, 33)); // NOI18N
        txtWorks.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtWorks.setText("7865");
        jPanel3.add(txtWorks, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 180, 100, -1));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        btnAceptarSolicitud.setForeground(new java.awt.Color(51, 102, 0));
        btnAceptarSolicitud.setText("ACEPTAR SOLICITUD");
        btnAceptarSolicitud.addActionListener(this::btnAceptarSolicitudActionPerformed);

        jtSolicitudEmpresa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Empresa", "Representante", "Correo Electrónico", "Teléfono", "Dirección", "Departamento"
            }
        ));
        jScrollPane3.setViewportView(jtSolicitudEmpresa);

        txtBuscarEmpresa.setText(BUSQUEDA);
        txtBuscarEmpresa.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarEmpresaFocusGained();
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarEmpresaFocusLost();
            }
        });
        txtBuscarEmpresa.addActionListener(this::txtBuscarEmpresaActionPerformed);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel14.setFont(new java.awt.Font(FUENTE_SANSS, 1, 16)); // NOI18N
        jLabel14.setText("EVALUACIÓN DE SOLICITUDES");

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img/request.png"))); // NOI18N

        btnRechazarSolicitud.setForeground(new java.awt.Color(150, 9, 9));
        btnRechazarSolicitud.setText("RECHAZAR SOLICITUD");
        btnRechazarSolicitud.addActionListener(this::btnRechazarSolicitudActionPerformed);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(txtBuscarEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(btnAceptarSolicitud, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnRechazarSolicitud, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(14, 14, 14))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1061, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(10, Short.MAX_VALUE))))
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(0, 384, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 246, Short.MAX_VALUE)))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel14)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAceptarSolicitud, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBuscarEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRechazarSolicitud, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(0, 261, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 236, Short.MAX_VALUE)))
        );

        jPanel3.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 1090, 490));

        btnActu.setBackground(new java.awt.Color(103, 70, 136));
        btnActu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img/config.png"))); // NOI18N
        btnActu.addActionListener(this::btnActuActionPerformed);
        jPanel3.add(btnActu, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, 40, 30));

        txtCorreo.setFont(new java.awt.Font(FUENTE_SANSS, 0, 14)); // NOI18N
        txtCorreo.setForeground(new java.awt.Color(102, 102, 102));
        txtCorreo.setText("¡Bienvenido al panel de control! Aquí encontrarás un resumen general del estado actual de la plataforma, permitiéndote administrar y monitorear las actividades clave de manera rápida y eficiente.");
        jPanel3.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, 210, 70));

        txtNombre.setFont(new java.awt.Font(FUENTE_SANSS, 1, 18)); // NOI18N
        jPanel3.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 80, -1, -1));

        jLabel12.setFont(new java.awt.Font(FUENTE_SANSS, 1, 18)); // NOI18N
        jLabel12.setText("Bienvenid@!");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 80, -1, -1));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img/perfillll_4.png"))); // NOI18N
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 720, 290));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img/estadisticas.png"))); // NOI18N
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 40, -1, -1));

        button2.setText("button2");
        button2.addActionListener(this::button2ActionPerformed);
        jPanel3.add(button2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 200, 130, 40));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 30, -1, -1));

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 780));

        jLabel5.setFont(new java.awt.Font(FUENTE_SANSS, 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("gbsss_16");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 130, -1, -1));

        jLabel7.setFont(new java.awt.Font(FUENTE_SANSS, 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("gbsss@gmail.com");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 150, -1, -1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 30, 350, 230));

        jPanel5.setBackground(new java.awt.Color(250, 249, 249));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 800, 280));

        jLabel2.setFont(new java.awt.Font(FUENTE_SANSS, 1, 18)); // NOI18N
        jLabel2.setText("Gabriela");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 80, -1, -1));

        jLabel3.setFont(new java.awt.Font(FUENTE_SANSS, 1, 18)); // NOI18N
        jLabel3.setText("Bienvenid@!");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 80, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img/perfil.png"))); // NOI18N
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 830, -1));

        jLabel4.setFont(new java.awt.Font(FUENTE_SANSS, 1, 18)); // NOI18N
        jLabel4.setText("Bienvenida");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 60, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed

    }//GEN-LAST:event_button2ActionPerformed

    private void btnActuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActuActionPerformed

    }//GEN-LAST:event_btnActuActionPerformed

    private void btnAceptarSolicitudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarSolicitudActionPerformed

    }//GEN-LAST:event_btnAceptarSolicitudActionPerformed

    private void btnRechazarSolicitudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRechazarSolicitudActionPerformed
        //Add your handling code here:
    }//GEN-LAST:event_btnRechazarSolicitudActionPerformed

    private void txtBuscarEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarEmpresaActionPerformed
        //Add your handling code here:
    }//GEN-LAST:event_txtBuscarEmpresaActionPerformed

    private void txtBuscarEmpresaFocusGained() {//GEN-FIRST:event_txtBuscarEmpresaFocusGained
        if (txtBuscarEmpresa.getText().equals(BUSQUEDA)) {
            txtBuscarEmpresa.setText("");
        }
    }//GEN-LAST:event_txtBuscarEmpresaFocusGained

    private void txtBuscarEmpresaFocusLost() {//GEN-FIRST:event_txtBuscarEmpresaFocusLost
        if (txtBuscarEmpresa.getText().equals("")) {
            txtBuscarEmpresa.setText(BUSQUEDA);
        }
    }//GEN-LAST:event_txtBuscarEmpresaFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public custom.Button btnAceptarSolicitud;
    public custom.Button btnActu;
    public custom.Button btnRechazarSolicitud;
    private custom.Button button2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane3;
    public javax.swing.JTable jtSolicitudEmpresa;
    public custom.TextField txtBuscarEmpresa;
    public javax.swing.JLabel txtCorreo;
    public javax.swing.JLabel txtEmpresas;
    public javax.swing.JLabel txtNombre;
    public javax.swing.JLabel txtUsers;
    public javax.swing.JLabel txtWorks;
    // End of variables declaration//GEN-END:variables
}
