package controlador;

import java.awt.event.*;
import java.util.Random;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import modelo.EnviarCorreo;
import modelo.UsuarioEscritorio;
import vista.frmIngresoCorreo;
import vista.frmValidarCodigo;

/**
 *
 * No se utilizarán los métodos vacíos
 */
public class CtrlIngresoCorreo implements MouseListener {

    private UsuarioEscritorio modelo;
    private frmIngresoCorreo vista;
    static Random random = new Random();
    static int numeroAleatorio = 1000 + random.nextInt(9000);
    static String correoEnviado;

    private static final String TITULO_ERROR = "Error";

    public CtrlIngresoCorreo(UsuarioEscritorio modelo, frmIngresoCorreo vista) {
        this.modelo = modelo;
        this.vista = vista;

        vista.btnRecuperarContrasena.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.btnRecuperarContrasena) {
            correoEnviado = vista.txtRecuperarContrasena.getText();

            // Validar el formato del correo
            if (!esCorreoValido(correoEnviado)) {
                JOptionPane.showMessageDialog(vista, "Por favor ingrese un correo electrónico válido.",
                        TITULO_ERROR, JOptionPane.ERROR_MESSAGE);
            }

            // Validar que el correo no esté vacío
            if (!validarEntradas(correoEnviado)) {
                return;
            }

            // Verificar si el correo existe en la base de datos
            if (!modelo.verificarCorreoExistente(correoEnviado)) {
                JOptionPane.showMessageDialog(vista, "El correo electrónico no está registrado en el sistema.", TITULO_ERROR, JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Genera un número aleatorio de 4 dígitos (1000 a 9999)
            String subject = "Recuperación de contraseña";
            // HTML para el contenido del correo
            String contentHtml = getHtmlCorreo(numeroAleatorio);
//            System.out.println("El código es: " + numeroAleatorio);

            // Envía el correo con el código de recuperación en formato HTML
            boolean correoEnviadoExitosamente = EnviarCorreo.enviarCorreo(correoEnviado, subject, contentHtml);

            if (correoEnviadoExitosamente) {
                // Cambiar a la ventana de validación del código
                frmValidarCodigo.initFrmValidarCodigo();
                vista.dispose();

                // Mostrar mensaje de éxito
                JOptionPane.showMessageDialog(vista, "Se ha enviado un código de recuperación a tu correo.");
            } else {
                // Mostrar mensaje de error si no se pudo enviar el correo
                JOptionPane.showMessageDialog(vista, "No se pudo enviar el correo. Por favor, inténtelo de nuevo más tarde.", TITULO_ERROR, JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Método para validar los campos de entrada del formulario.
     *
     * @return true si todas las validaciones pasan, false en caso contrario.
     */
    private boolean validarEntradas(String correo) {
        StringBuilder errores = new StringBuilder();

        if (correo.trim().isEmpty()) {
            errores.append("El campo 'Correo electrónico' no puede estar vacío.\n");
        }

        if (errores.length() > 0) {
            JOptionPane.showMessageDialog(vista, errores.toString(), TITULO_ERROR, JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private String getHtmlCorreo(int codigo) {
        return "<html>\n"
                + "<body style=\"font-family: 'Roboto', sans-serif;"
                + "background-color: #f5f7fa; margin: 0; padding: 0;\">\n"
                + "<div class=\"container\" style=\"width: 100%; max-width: 600px; margin: 50px auto; "
                + "background-color: #ffffff; padding: 30px 20px; border-radius: 15px; "
                + "box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);\">\n"
                + "<div class=\"img\" style=\"text-align: center; margin-top: 40px;\">\n"
                + "<img src=\"https://i.imgur.com/bXHJUmC.png\" alt=\"Logo\" width=\"400\" "
                + "style=\"border-radius: 10px;\">\n"
                + "</div>\n"
                + "<div class=\"message\" style=\"text-align: center; color: #2c3e50; margin-bottom: 40px;\">\n"
                + "<h2 style=\"font-size: 28px; font-weight: 600; margin-bottom: 10px;\">Recuperación de Contraseña</h2>\n"
                + "<p style=\"font-size: 18px; color: #7f8c8d;\">Usa el siguiente código para recuperar tu contraseña:</p>\n"
                + "<div class=\"code\" style=\"display: inline-block; padding: 20px 40px; font-size: 26px; color: #000;"
                + "background-color: #d2dee7; border-radius: 10px; margin-top: 20px; letter-spacing: 2px;\">"
                + codigo + "</div>\n"
                + "</div>\n"
                + "<div class=\"footer-logo\" style=\"text-align: center; margin-top: 40px;\">\n"
                + "<img src=\"https://i.imgur.com/TU8KAcy.png\" alt=\"Logo\" width=\"550\" style=\"border-radius: 10px;\">\n"
                + "</div>\n"
                + "</div>\n"
                + "</body>\n"
                + "</html>";
    }

    private boolean esCorreoValido(String correo) {
        // Expresión regular para validar el correo electrónico
        String regex = "^[\\w-.]+@[\\w-]+(\\.[\\w-]+)+$";
        return Pattern.matches(regex, correo);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //No se utilizarán los métodos vacíos
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //No se utilizarán los métodos vacíos
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //No se utilizarán los métodos vacíos
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //No se utilizarán los métodos vacíos
    }
}
