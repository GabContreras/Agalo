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

//Los métodos vacíos no se ocuparán 
public class CtrlAdministrarUsuario implements MouseListener, KeyListener {

    private static final String CONTRASENA_ADMINISTRADOR = "Contraseña Administrador";

    private static final Logger logger = Logger.getLogger(CtrlAdministrarUsuario.class.getName());

    // 1- Mandar a llamar a las otras capas (modelo y vista)
    private final AdministrarUsuario modelo;
    private final frmAdministrarUsuarios vista;

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
            agregarAdmin();
        } else if (e.getSource() == vista.btnEliminarAdmin) {
            eliminarAdmin();
        } else if (e.getSource() == vista.jtbAdmin) {
            modelo.cargarDatosTabla(vista);
        } else if (e.getSource() == vista.btnEditarAdmin) {
            editarAdmin();
        }
    }

    private void agregarAdmin() {
        String nombre = vista.txtNombreAdmin.getText();
        String usuario = vista.txtUsuarioAdmin.getText();
        String correo = vista.txtCorreoAdmin.getText();
        // Obtener la contraseña de un JPasswordField
        char[] contrasenaArray = vista.txtContrasenaAdmin.getPassword();
        String contrasena = new String(contrasenaArray); // Convertir a String si es necesario

        String contrasenaEncriptada = encriptarContrasena(contrasena);
        if (contrasenaEncriptada == null) {
            mostrarError("Error al encriptar la contraseña.");
            return;
        }

        if (!validarDatosUsuario(usuario, nombre, correo, contrasena, false)) {
            return;
        }

        modelo.setNombre(nombre);
        modelo.setUsuario(usuario);
        modelo.setCorreoElectronico(correo);
        modelo.setContrasena(contrasenaEncriptada);
        modelo.Guardar();
        modelo.Mostrar(vista.jtbAdmin);
        limpiarCampos();
    }

    //Agregar Mensaje de error cuando no se ha seleccionado ningún usuario, agregar mensaje de éxito cuando se haya eliminado un usuario correctamente
    private void eliminarAdmin() {
        modelo.Eliminar(vista.jtbAdmin);
        modelo.Mostrar(vista.jtbAdmin);
    }

    private void editarAdmin() {
        String nombre = vista.txtNombreAdmin.getText();
        String usuario = vista.txtUsuarioAdmin.getText();
        String correo = vista.txtCorreoAdmin.getText();
        char[] contrasenaArray = vista.txtContrasenaAdmin.getPassword();
        String contrasena = new String(contrasenaArray); // Convertir a String si es necesario

        if (!validarDatosUsuario(usuario, nombre, correo, contrasena, true)) {
            return;
        }

        if (contrasena.isEmpty() || contrasena.equals(CONTRASENA_ADMINISTRADOR)) {
            modelo.setNombre(nombre);
            modelo.setUsuario(usuario);
            modelo.setCorreoElectronico(correo);
            modelo.ActualizarSinContrasena(vista.jtbAdmin);
        } else {
            String contrasenaEncriptada = encriptarContrasena(contrasena);
            if (contrasenaEncriptada == null) {
                mostrarError("Error al encriptar la contraseña.");
                return;
            }
            modelo.setContrasena(contrasenaEncriptada);
            modelo.setNombre(nombre);
            modelo.setUsuario(usuario);
            modelo.setCorreoElectronico(correo);
            modelo.Actualizar(vista.jtbAdmin);
        }

        modelo.Mostrar(vista.jtbAdmin);
        limpiarCampos();
    }

    private boolean validarDatosUsuario(String usuario, String nombre, String correo, String contrasena, boolean esEdicion) {
        if (usuario.equals("Usuario Administrador") || usuario.isEmpty()) {
            mostrarError("El usuario no puede estar vacío.");
            return false;
        }
        if (nombre.equals("Nombre Administrador") || nombre.isEmpty()) {
            mostrarError("El nombre no puede estar vacío.");
            return false;
        }
        if (!esCorreoValido(correo)) {
            mostrarError("El correo electrónico debe ser válido.");
            return false;
        }
        if (!esEdicion && (contrasena.equals(CONTRASENA_ADMINISTRADOR) || contrasena.length() < 6)) {
            mostrarError("La contraseña debe tener al menos 6 caracteres.");
            return false;
        }
        return true;
    }

    private boolean esCorreoValido(String correo) {
        // Expresión regular para validar el correo electrónico
        String regex = "([a-zA-Z0-9_.-]+)@([a-zA-Z]+)\\.([a-zA-Z]+)";
        return correo.matches(regex);
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(vista, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        /**
         *
         * Metodos vacíos porque el programa lo pide
         */
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        /**
         *
         * Metodos vacíos porque el programa lo pide
         */
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        /**
         *
         * Metodos vacíos porque el programa lo pide
         */
    }

    @Override
    public void mouseExited(MouseEvent e) {
        /**
         *
         * Metodos vacíos porque el programa lo pide
         */
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getSource() == vista.txtBuscarUsuarios) {
            modelo.buscarUsuario(vista.jtbAdmin, vista.txtBuscarUsuarios);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        /**
         *
         * Metodos vacíos porque el programa lo pide
         */
    }

    @Override
    public void keyReleased(KeyEvent e) {
        /**
         *
         * Metodos vacíos porque el programa lo pide
         */
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
            return null;
        }
    }

    private void limpiarCampos() {
        vista.txtNombreAdmin.setText("Nombre Administrador");
        vista.txtUsuarioAdmin.setText("Usuario Administrador");
        vista.txtCorreoAdmin.setText("Correo Electronico Administrador");
        vista.txtContrasenaAdmin.setText(CONTRASENA_ADMINISTRADOR);
    }
}
