package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.UsuarioEscritorio;
import vista.frmRegistro;
import vista.frmLogin; // Importar el formulario de login
import java.util.regex.Pattern;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

public class ctrlRegistrar implements ActionListener {

    private UsuarioEscritorio modelo;
    private frmRegistro vista;

    // Constructor 
    public ctrlRegistrar(UsuarioEscritorio modelo, frmRegistro vista) {
        this.modelo = modelo;
        this.vista = vista;

        this.vista.btnRegistrar.addActionListener(this); // Usar ActionListener en lugar de MouseListener
        this.vista.btnLogear.addActionListener(this);  // Agregar ActionListener para el botón de login
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnRegistrar) {
            System.out.println("Botón registrar clicado");

            // Validar entradas primero
            if (!validarEntradas()) {
                return; // Si las validaciones de los campos fallan, salir del método
            }

            // Luego, validar si se han aceptado los términos y condiciones
            if (!vista.jCondiciones.isSelected()) {
                JOptionPane.showMessageDialog(vista, "Debe aceptar los términos y condiciones para registrarse.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return; // Si el checkbox no está marcado, salir del método
            }
            
            // Configurar modelo con los datos de la vista
            modelo.setNombre(vista.txtNombre.getText());
            modelo.setUsuario(vista.txtUsuario.getText());
            modelo.setCorreo(vista.txtCorreoElectronico.getText());
            
            // Nuevos campos agregados
            modelo.setEdad(Integer.parseInt(vista.txtEdadRegistro.getText()));
            modelo.setCiudad(vista.txtCiudadRegistro.getText());
            modelo.setPais(vista.txtPaisRegistro.getText());

            // Encriptar la contraseña antes de guardarla
            String contrasenaEncriptada = encriptarContrasena(vista.txtCiudadRegistro.getText());
            if (contrasenaEncriptada == null) {
                JOptionPane.showMessageDialog(vista, "Error al encriptar la contraseña.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            modelo.setContrasena(contrasenaEncriptada);

            try {
                modelo.GuardarUsuario(); // Llamada para guardar el usuario
                // Solo si no se lanza excepción mostramos el mensaje de éxito
                JOptionPane.showMessageDialog(vista, "Usuario Guardado con éxito.");
                frmLogin.initFrmLogin();
                vista.dispose();
            } catch (Exception ex) {
                // Si ocurre una excepción, mostramos el mensaje de error
                JOptionPane.showMessageDialog(vista, "Error al guardar el usuario: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }

        } else if (e.getSource() == vista.btnLogear) {
            // Cambiar al formulario de login
            frmLogin loginForm = new frmLogin();
            loginForm.setVisible(true);
            vista.dispose(); // Cierra el formulario actual
        }
    }

    /**
     * Método para validar los campos de entrada del formulario.
     *
     * @return true si todas las validaciones pasan, false en caso contrario.
     */
    private boolean validarEntradas() {
        StringBuilder errores = new StringBuilder(); // Para acumular los mensajes de error

        // Validación de formato de correo electrónico primero
        if (vista.txtCorreoElectronico.getText().trim().isEmpty() || vista.txtCorreoElectronico.getText().trim().equals("Correo")) {
            errores.append("El campo 'Correo Electrónico' no puede estar vacío.\n");
        } else if (!esCorreoValido(vista.txtCorreoElectronico.getText().trim())) {
            errores.append("El correo electrónico no tiene un formato válido.\n");
        }

        // Validación de campos vacíos o que contengan el texto predeterminado
        if (vista.txtNombre.getText().trim().isEmpty() || vista.txtNombre.getText().trim().equals("Nombre")) {
            errores.append("El campo 'Nombre' no puede estar vacío.\n");
        }

        if (vista.txtUsuario.getText().trim().isEmpty() || vista.txtUsuario.getText().trim().equals("Usuario")) {
            errores.append("El campo 'Usuario' no puede estar vacío.\n");
        }

        if (vista.txtCiudadRegistro.getText().trim().isEmpty() || vista.txtCiudadRegistro.getText().trim().equals("Contraseña")) {
            errores.append("El campo 'Contraseña' no puede estar vacío.\n");
        } else if (vista.txtCiudadRegistro.getText().length() < 6) { // Validación de longitud de contraseña
            errores.append("La contraseña debe tener al menos 6 caracteres.\n");
        }

        // Validación de edad
        if (vista.txtEdadRegistro.getText().trim().isEmpty()) {
            errores.append("El campo 'Edad' no puede estar vacío.\n");
        } else {
            try {
                int edad = Integer.parseInt(vista.txtEdadRegistro.getText());
                if (edad < 18) {
                    errores.append("Debe tener al menos 18 años para registrarse.\n");
                }
            } catch (NumberFormatException e) {
                errores.append("El campo 'Edad' debe ser un número válido.\n");
            }
        }

        // Validación de ciudad
        if (vista.txtCiudadRegistro.getText().trim().isEmpty()) {
            errores.append("El campo 'Ciudad' no puede estar vacío.\n");
        }

        // Validación de país
        if (vista.txtPaisRegistro.getText().trim().isEmpty()) {
            errores.append("El campo 'País' no puede estar vacío.\n");
        }

        // Mostrar errores si existen
        if (errores.length() > 0) {
            JOptionPane.showMessageDialog(vista, errores.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            return false; // Las validaciones fallaron
        }

        return true; // Todas las validaciones pasaron
    }

    /**
     * Método para validar el formato de un correo electrónico usando una
     * expresión regular.
     *
     * @param correo el correo electrónico a validar.
     * @return true si el correo es válido, false en caso contrario.
     */
    private boolean esCorreoValido(String correo) {
        // Expresión regular para validar el correo electrónico
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return Pattern.matches(regex, correo);
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
            JOptionPane.showMessageDialog(vista, "Error en la encriptación de la contraseña: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return null;
        }
    }
}
