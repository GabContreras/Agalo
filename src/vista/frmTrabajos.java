/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Gudelia
 */
public class frmTrabajos extends javax.swing.JPanel {

    /**
     * Creates new form frmTrabajos
     */
    public frmTrabajos() {
        initComponents();
        UIManager.getSystemLookAndFeelClassName();
         UIManager.put("ComboBox.border", BorderFactory.createEmptyBorder());
        
        jtTrabajos.setBackground(java.awt.Color.WHITE); // Cambia el fondo de las celdas de la tabla
        jtTrabajos.setFillsViewportHeight(true); // Asegura que el fondo cubra todo el área
        jtTrabajos.getParent().setBackground(java.awt.Color.WHITE);
        
         jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder()); // Establecer borde vacío
        
        // Personalizar el encabezado de la tabla
        jtTrabajos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD,12));
        jtTrabajos.getTableHeader().setBackground(java.awt.Color.WHITE);
        jtTrabajos.setRowHeight(45);
        
        
        // Configurar las líneas horizontales de la tabla
        jtTrabajos.setGridColor(new java.awt.Color(230, 230, 230)); // Líneas gris claro
        jtTrabajos.setShowHorizontalLines(true); // Mostrar líneas horizontales
        jtTrabajos.setShowVerticalLines(false); // Ocultar líneas verticales si lo deseas
        
         DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(java.awt.Color.WHITE); // Fondo blanco
        headerRenderer.setForeground(new Color(0, 0, 0)); // Texto en color negro
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        headerRenderer.setFont(new Font("Segoe UI", Font.BOLD, 12)); 
        
        for (int i = 0; i < jtTrabajos.getColumnModel().getColumnCount(); i++) {
            jtTrabajos.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        jtTrabajos.setRowHeight(25);
        jtTrabajos.setGridColor(new Color(230, 230, 230));
        
        // Personalizar el JComboBox
        jComboBoxTrabajos.setBackground(new Color(255, 255, 255)); // Fondo blanco
        jComboBoxTrabajos.setForeground(new Color(50, 50, 50)); // Texto gris oscuro
        jComboBoxTrabajos.setFont(new Font("Segoe UI", Font.PLAIN, 14)); // Fuente minimalista
        
        // Crear un borde rectangular y aplicarlo al JComboBox
        jComboBoxTrabajos.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1)); // Borde rectangular de 1px

        // Establecer el renderer personalizado para los items del JComboBox
        jComboBoxTrabajos.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel renderer = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                
                // Colores minimalistas
                if (isSelected) {
                    renderer.setBackground(new Color(220, 220, 220)); // Color de selección minimalista
                    renderer.setForeground(new Color(0, 0, 0)); // Texto negro
                } else {
                    renderer.setBackground(Color.WHITE); // Fondo blanco
                    renderer.setForeground(new Color(50, 50, 50)); // Texto gris oscuro
                }

                // Margen y alineación minimalista
                renderer.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Margen interno
                renderer.setHorizontalAlignment(SwingConstants.LEFT); // Alineación izquierda
                return renderer;
            }
        });
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
        jComboBoxTrabajos = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtTrabajos = new javax.swing.JTable();
        btnGenerarReporte = new custom.Button();
        textField1 = new custom.TextField();

        setBackground(new java.awt.Color(250, 249, 249));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jComboBoxTrabajos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img/trabajo.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel2.setText("Gestion de Trabajos ");

        jtTrabajos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jtTrabajos);

        btnGenerarReporte.setText("Generar Reporte");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(116, 116, 116)
                                .addComponent(jComboBoxTrabajos, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnGenerarReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 922, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 49, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxTrabajos, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGenerarReporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 47, 1020, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private custom.Button btnGenerarReporte;
    public javax.swing.JComboBox<String> jComboBoxTrabajos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jtTrabajos;
    private custom.TextField textField1;
    // End of variables declaration//GEN-END:variables
}
