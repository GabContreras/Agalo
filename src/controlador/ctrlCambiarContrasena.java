package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JOptionPane;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.UsuarioEscritorio;
import vista.frmCambiarContrasena;
import vista.frmLogin;

public class CtrlCambiarContrasena implements ActionListener {

    private static final String MENSAJE_ERROR = "Error";

    private UsuarioEscritorio modelo;
    private frmCambiarContrasena vista;
    private static final Logger logger = Logger.getLogger(CtrlCambiarContrasena.class.getName());

    public CtrlCambiarContrasena(UsuarioEscritorio modelo, frmCambiarContrasena vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.vista.btnCambiarContrasena.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnCambiarContrasena) {
            String nuevaContrasena = vista.txtNuevaContrasena.getText();
            String confirmarContrasena = vista.txtConfirmarContrasena.getText();

            // Validar que ambas contraseñas coincidan
            if (!nuevaContrasena.equals(confirmarContrasena)) {
                JOptionPane.showMessageDialog(vista, "Las contraseñas no coinciden.", MENSAJE_ERROR, JOptionPane.ERROR_MESSAGE);
                return; // Sale de la función si no coinciden
            }

            // Encriptar la nueva contraseña antes de guardar
            String contrasenaEncriptada = encriptarContrasena(nuevaContrasena);
            if (contrasenaEncriptada != null) {
                // Actualizar la contraseña en la base de datos
                modelo.actualizar_contra(CtrlIngresoCorreo.correoEnviado, contrasenaEncriptada);
                JOptionPane.showMessageDialog(vista, "La contraseña se cambió correctamente.");

                // Redirigir al login después del cambio de contraseña
                frmLogin login = new frmLogin();
                login.initFrmLogin();
                vista.dispose();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al encriptar la contraseña.", MENSAJE_ERROR, JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private String encriptarContrasena(String contrasena) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(contrasena.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            logger.log(Level.SEVERE, "Error en la encriptación de la contraseña", e);
            // Mostrar un mensaje genérico al usuario
            JOptionPane.showMessageDialog(null, "Ocurrió un error al procesar la contraseña. Por favor, inténtelo de nuevo.", MENSAJE_ERROR , JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
