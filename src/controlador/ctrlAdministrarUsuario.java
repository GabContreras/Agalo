package controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.AdministrarUsuario;
import vista.frmAdministrarUsuarios;

public class CtrlAdministrarUsuario implements MouseListener, KeyListener {

    private static final Logger logger = Logger.getLogger(CtrlAdministrarUsuario.class.getName());

    // 1- Mandar a llamar a las otras capas (modelo y vista)
    private AdministrarUsuario modelo;
    private frmAdministrarUsuarios vista;

    /**
     *
     * Metodos vacíos porque el programa lo pide
     */
    // 2- Crear el constructor
    public CtrlAdministrarUsuario(AdministrarUsuario modelo, frmAdministrarUsuarios vista) {
        this.modelo = modelo;
        this.vista = vista;

        vista.btnAgregarAdmin.addMouseListener(this);
        vista.btnEditarAdmin.addMouseListener(this);
        vista.btnEliminarAdmin.addMouseListener(this);

        vista.txtBuscarUsuarios.addKeyListener(this);

        vista.jtbAdmin.addMouseListener(this); // Listener para la tabla
        modelo.Mostrar(vista.jtbAdmin);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.btnAgregarAdmin) {
            String nombre = vista.txtNombreAdmin.getText();
            String usuario = vista.txtUsuarioAdmin.getText();
            String correo = vista.txtCorreoAdmin.getText();
            String contrasena = vista.txtContrasenaAdmin.getText(); // Asegúrate de que tengas este campo

            // Encriptar la contraseña antes de guardarla
            String contrasenaEncriptada = encriptarContrasena(vista.txtContrasenaAdmin.getText());
            if (contrasenaEncriptada == null) {
                JOptionPane.showMessageDialog(vista, "Error al encriptar la contraseña.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Validaciones
            if (usuario.equals("Usuario Administrador") || usuario.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "El usuario no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (nombre.equals("Nombre Administrador") || nombre.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "El nombre no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!correo.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                JOptionPane.showMessageDialog(vista, "El correo electrónico debe ser válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (contrasena.equals("Contraseña Administrador") || contrasena.length() < 6) { // Asegúrate de que el tamaño sea suficiente
                JOptionPane.showMessageDialog(vista, "La contraseña debe tener al menos 6 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            modelo.setNombre(nombre);
            modelo.setUsuario(usuario);
            modelo.setCorreoElectronico(correo);
            modelo.setContrasena(contrasenaEncriptada);

            modelo.Guardar();
            modelo.Mostrar(vista.jtbAdmin);

//            // Mensaje de éxito
//            JOptionPane.showMessageDialog(vista, "Administrador agregado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            // Limpiar los campos después de agregar
            limpiarCampos();
        }

        if (e.getSource() == vista.btnEliminarAdmin) {
            modelo.Eliminar(vista.jtbAdmin);
            modelo.Mostrar(vista.jtbAdmin);
//            // Mensaje de éxito
//            JOptionPane.showMessageDialog(vista, "Administrador eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }

        if (e.getSource() == vista.jtbAdmin) {
            modelo.cargarDatosTabla(vista);
        }

        if (e.getSource() == vista.btnEditarAdmin) {
            // Obtener datos de los campos de texto
            String nombre = vista.txtNombreAdmin.getText();
            String usuario = vista.txtUsuarioAdmin.getText();
            String correo = vista.txtCorreoAdmin.getText();
            String contrasena = vista.txtContrasenaAdmin.getText();

            // Validaciones
            if (usuario.equals("Usuario Administrador") || usuario.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "El usuario no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (nombre.equals("Nombre Administrador") || nombre.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "El nombre no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!correo.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                JOptionPane.showMessageDialog(vista, "El correo electrónico debe ser válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Verificar si el campo de contraseña está vacío
            if (contrasena.isEmpty() || contrasena.equals("Contraseña Administrador")) {
                // Si la contraseña está vacía, llamar a ActualizarSinContrasena
                modelo.setNombre(nombre);
                modelo.setUsuario(usuario);
                modelo.setCorreoElectronico(correo);
                modelo.ActualizarSinContrasena(vista.jtbAdmin);

                // Mensaje de éxito
                JOptionPane.showMessageDialog(vista, "Administrador actualizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            } else {
                // Si la contraseña no está vacía, encriptarla y llamar a Actualizar
                String contrasenaEncriptada = encriptarContrasena(contrasena);
                if (contrasenaEncriptada == null) {
                    JOptionPane.showMessageDialog(vista, "Error al encriptar la contraseña.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Asignar valores al modelo
                modelo.setContrasena(contrasenaEncriptada);
                modelo.setNombre(nombre);
                modelo.setUsuario(usuario);
                modelo.setCorreoElectronico(correo);
                modelo.Actualizar(vista.jtbAdmin);

                // Mensaje de éxito
                JOptionPane.showMessageDialog(vista, "Administrador actualizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            }

            modelo.Mostrar(vista.jtbAdmin); // Actualizar la tabla

            // Limpiar los campos después de agregar
            limpiarCampos();
        }

        if (e.getSource() == vista.jtbAdmin) {
            // Cargar datos del administrador seleccionado en los campos de texto
            modelo.cargarDatosTabla(vista);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getSource() == vista.txtBuscarUsuarios) {
            modelo.buscarUsuario(vista.jtbAdmin, vista.txtBuscarUsuarios);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    /**
     * Método para encriptar la contraseña utilizando SHA-256.
     *
     * @param contrasena la contraseña a encriptar.
     * @return la contraseña encriptada en formato hexadecimal.
     */
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
            // Registrar el error en el log
            logger.log(Level.SEVERE, "Error en la encriptación de la contraseña", e);
            // Mostrar un mensaje genérico al usuario
            JOptionPane.showMessageDialog(null, "Ocurrió un error al procesar la contraseña. Por favor, inténtelo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
            return null; // O manejar el error de otra manera
        }
    }

    private void limpiarCampos() {
        vista.txtNombreAdmin.setText("Nombre Administrador");
        vista.txtUsuarioAdmin.setText("Usuario Administrador");
        vista.txtCorreoAdmin.setText("Correo Electronico Administrador");
        vista.txtContrasenaAdmin.setText("Contraseña Administrador");
    }
}
