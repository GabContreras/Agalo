package vista;

import controlador.CtrlAdministrarEmpleadores;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import modelo.AdministrarEmpleadores;

public class FrmEmpresa extends javax.swing.JPanel {

    public FrmEmpresa() {

        initComponents();

        AdministrarEmpleadores modelo = new AdministrarEmpleadores();
        new CtrlAdministrarEmpleadores(modelo, this);

        UIManager.getSystemLookAndFeelClassName();
        UIManager.put("ComboBox.border", BorderFactory.createEmptyBorder());

        jtEmpresa.setBackground(java.awt.Color.WHITE); // Cambia el fondo de las celdas de la tabla
        jtEmpresa.setFillsViewportHeight(true); // Asegura que el fondo cubra todo el área
        jtEmpresa.getParent().setBackground(java.awt.Color.WHITE);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder()); // Establecer borde vacío

        // Personalizar el encabezado de la tabla
        jtEmpresa.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        jtEmpresa.getTableHeader().setBackground(java.awt.Color.WHITE);
        jtEmpresa.setRowHeight(45);

        // Configurar las líneas horizontales de la tabla
        jtEmpresa.setGridColor(new java.awt.Color(230, 230, 230)); // Líneas gris claro
        jtEmpresa.setShowHorizontalLines(true); // Mostrar líneas horizontales
        jtEmpresa.setShowVerticalLines(false); // Ocultar líneas verticales si lo deseas

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(java.awt.Color.WHITE); // Fondo blanco
        headerRenderer.setForeground(new Color(0, 0, 0)); // Texto en color negro
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        headerRenderer.setFont(new Font("Segoe UI", Font.BOLD, 12));

        for (int i = 0; i < jtEmpresa.getColumnModel().getColumnCount(); i++) {
            jtEmpresa.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        jtEmpresa.setRowHeight(25);
        jtEmpresa.setGridColor(new Color(230, 230, 230));

        // Personalizar el JComboBox
//        jComboBox1.setBackground(new Color(255, 255, 255)); // Fondo blanco
//        jComboBox1.setForeground(new Color(50, 50, 50)); // Texto gris oscuro
//        jComboBox1.setFont(new Font("Segoe UI", Font.PLAIN, 14)); // Fuente minimalista
//        
//        // Crear un borde rectangular y aplicarlo al JComboBox
//        jComboBox1.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1)); // Borde rectangular de 1px
//
//        // Establecer el renderer personalizado para los items del JComboBox
//        jComboBox1.setRenderer(new DefaultListCellRenderer() {
//            @Override
//            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
//                JLabel renderer = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
//                
//                // Colores minimalistas
//                if (isSelected) {
//                    renderer.setBackground(new Color(220, 220, 220)); // Color de selección minimalista
//                    renderer.setForeground(new Color(0, 0, 0)); // Texto negro
//                } else {
//                    renderer.setBackground(Color.WHITE); // Fondo blanco
//                    renderer.setForeground(new Color(50, 50, 50)); // Texto gris oscuro
//                }
//
//                // Margen y alineación minimalista
//                renderer.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Margen interno
//                renderer.setHorizontalAlignment(SwingConstants.LEFT); // Alineación izquierda
//                return renderer;
//            }
//        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtBuscarEmpleador = new custom.TextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtEmpresa = new javax.swing.JTable();
        btnRestringirEmpleador = new custom.Button();

        setBackground(new java.awt.Color(250, 249, 249));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img/empresa_1.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel2.setText("Gestion de Empleadores");

        txtBuscarEmpleador.setText("Buscar");
        txtBuscarEmpleador.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarEmpleadorFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarEmpleadorFocusLost(evt);
            }
        });

        jtEmpresa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jtEmpresa);

        btnRestringirEmpleador.setForeground(new java.awt.Color(255, 0, 0));
        btnRestringirEmpleador.setText("Restringir Empleador");
        btnRestringirEmpleador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestringirEmpleadorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(txtBuscarEmpleador, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnRestringirEmpleador, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 979, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscarEmpleador, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRestringirEmpleador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 42, 1050, 710));
    }// </editor-fold>//GEN-END:initComponents

    private void btnRestringirEmpleadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestringirEmpleadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRestringirEmpleadorActionPerformed
    private static final String BUSQUEDA = "Buscar";

    private void txtBuscarEmpleadorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarEmpleadorFocusGained
        if (txtBuscarEmpleador.getText().equals(BUSQUEDA)) {
            txtBuscarEmpleador.setText("");
        }    }//GEN-LAST:event_txtBuscarEmpleadorFocusGained

    private void txtBuscarEmpleadorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarEmpleadorFocusLost
        if (txtBuscarEmpleador.getText().equals("")) {
            txtBuscarEmpleador.setText(BUSQUEDA);
        }    }//GEN-LAST:event_txtBuscarEmpleadorFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public custom.Button btnRestringirEmpleador;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jtEmpresa;
    public custom.TextField txtBuscarEmpleador;
    // End of variables declaration//GEN-END:variables
}
