/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import controlador.CtrlCambiarContrasena;
import java.awt.Color;
import modelo.UsuarioEscritorio;

/**
 *
 * @author Jero
 */
public class FrmCambiarContrasena extends javax.swing.JFrame {

    /**
     * Creates new form frmCambiarContrasena
     */
    public FrmCambiarContrasena() {
        initComponents();
    }

    public static void initFrmCambiarContrasena() {
        UsuarioEscritorio modelo = new UsuarioEscritorio();
        FrmCambiarContrasena vista = new FrmCambiarContrasena();
        new CtrlCambiarContrasena(modelo, vista);

        vista.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCambiarContrasena = new javax.swing.JButton();
        txtConfirmarContrasena = new javax.swing.JTextField();
        txtNuevaContrasena = new javax.swing.JTextField();
        button1 = new custom.Button();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCambiarContrasena.setBackground(new java.awt.Color(0, 0, 0));
        btnCambiarContrasena.setForeground(new java.awt.Color(255, 255, 255));
        btnCambiarContrasena.setText("Cambiar Contraseña");
        btnCambiarContrasena.addActionListener(this::btnCambiarContrasenaActionPerformed);
        getContentPane().add(btnCambiarContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 430, 210, 50));

        txtConfirmarContrasena.setText(REPETIR_CONTRA);
        txtConfirmarContrasena.setToolTipText("");
        txtConfirmarContrasena.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtConfirmarContrasenaFocusGained();
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtConfirmarContrasenaFocusLost();
            }
        });
        getContentPane().add(txtConfirmarContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 350, 510, 40));

        txtNuevaContrasena.setText(CONTRA_NUEVA);
        txtNuevaContrasena.setToolTipText("");
        txtNuevaContrasena.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNuevaContrasenaFocusGained();
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNuevaContrasenaFocusLost();
            }
        });
        getContentPane().add(txtNuevaContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 280, 510, 50));

        button1.setBackground(new java.awt.Color(6, 6, 20));
        getContentPane().add(button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 230, 590, 370));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img/fondor.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-3, 0, 1400, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //Constantes    
    private static final String CONTRA_NUEVA = "Escriba Su Nueva Contraseña";
    private static final String REPETIR_CONTRA = "Repetir Contraseña";

    private void txtNuevaContrasenaFocusGained() {//GEN-FIRST:event_txtNuevaContrasenaFocusGained
        if (txtNuevaContrasena.getText().equals(CONTRA_NUEVA)) {
            txtNuevaContrasena.setText("");
            txtNuevaContrasena.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_txtNuevaContrasenaFocusGained

    private void txtConfirmarContrasenaFocusLost() {//GEN-FIRST:event_txtConfirmarContrasenaFocusLost
        if (txtConfirmarContrasena.getText().equals("")) {
            txtConfirmarContrasena.setText(REPETIR_CONTRA);
            txtConfirmarContrasena.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_txtConfirmarContrasenaFocusLost

    private void txtConfirmarContrasenaFocusGained() {//GEN-FIRST:event_txtConfirmarContrasenaFocusGained
        if (txtConfirmarContrasena.getText().equals(REPETIR_CONTRA)) {
            txtConfirmarContrasena.setText("");
            txtConfirmarContrasena.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_txtConfirmarContrasenaFocusGained

    private void txtNuevaContrasenaFocusLost() {//GEN-FIRST:event_txtNuevaContrasenaFocusLost
        if (txtNuevaContrasena.getText().equals("")) {
            txtNuevaContrasena.setText(CONTRA_NUEVA);
            txtNuevaContrasena.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_txtNuevaContrasenaFocusLost

    private void btnCambiarContrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarContrasenaActionPerformed
        //Add your handling code here:
    }//GEN-LAST:event_btnCambiarContrasenaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCambiarContrasena.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(FrmCambiarContrasena::initFrmCambiarContrasena);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnCambiarContrasena;
    private custom.Button button1;
    private javax.swing.JLabel jLabel1;
    public javax.swing.JTextField txtConfirmarContrasena;
    public javax.swing.JTextField txtNuevaContrasena;
    // End of variables declaration//GEN-END:variables
}
