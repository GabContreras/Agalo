/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.InputStream;
import java.io.IOException;

/**
 *
 * @author Jero
 */
public class EnviarCorreo {

    
    // Constructor privado para evitar instanciación
    private EnviarCorreo() {
        // No se permite la instanciación
    }
    // Variables para la cadena de conexión
    private static String MyAccEm;
    private static String PassAcc;
    // Instancia de logger para sustituir los system out 
    private static final Logger logger = Logger.getLogger(ClaseConexion.class.getName());

    // Cargar las propiedades desde el archivo
    static {
        Properties properties = new Properties();
        try (InputStream input = ClaseConexion.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                logger.log(Level.SEVERE, "No se pudo encontrar el archivo de configuración.");
            }
            // Cargar las propiedades
            properties.load(input);
            MyAccEm = properties.getProperty("gm.MyAccEm");
            PassAcc = properties.getProperty("gm.PassAcc");
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Error al cargar el archivo de propiedades: ", ex);
        }
    }

    public static boolean enviarCorreo(String recipient, String subject, String content) {

        //1- Propiedades del servidor de correo
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // Crear sesión
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MyAccEm, PassAcc);
            }
        });

        // 3- Enviar el correo
        try {
            // Crear mensaje
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(MyAccEm));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject);

            // Configurar el contenido del mensaje para aceptar HTML
            message.setContent(content, "text/html; charset=utf-8");

            // Enviar mensaje
            Transport.send(message);
            System.out.println("Correo enviado con éxito");
            return true;
        } catch (MessagingException e) {
            logger.log(Level.SEVERE, "Error al enviar correo: ", e);
            return false;
        }

    }

}
