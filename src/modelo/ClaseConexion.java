package modelo;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.InputStream;
import java.io.IOException;

public class ClaseConexion {

    // Variables para la cadena de conexión
    private static String url;
    private static String usuario;
    private static String cont;

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
            url = properties.getProperty("db.url");
            usuario = properties.getProperty("db.user");
            cont = properties.getProperty("db.password");
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Error al cargar el archivo de propiedades: ", ex);
        }
    }

    // Constructor privado para evitar instanciación
    private ClaseConexion() {
        // No se permite la instanciación
    }

    // Creación del método de conexión que retorna la conexión
    public static Connection getConexion() {
        try {
            // Obtener la conexión en una variable
            return DriverManager.getConnection(url, usuario, cont);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error de SQL: ", e);
            return null;
        }
    }
}
