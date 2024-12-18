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

    // Método para cargar las propiedades desde el archivo
    private static Properties loadProperties() {
        Properties properties = new Properties();
        String configFile = System.getProperty("config.file", "config.properties"); // Usa el archivo especificado o el predeterminado
        try (InputStream input = ClaseConexion.class.getClassLoader().getResourceAsStream(configFile)) {
            if (input == null) {
                logger.log(Level.SEVERE, "No se pudo encontrar el archivo de configuración: ", configFile);
                return null;
            }
            properties.load(input);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Error al cargar el archivo de propiedades: ", ex);
        }
        return properties;
    }

    // Creación del método de conexión que retorna la conexión
    public static Connection getConexion() {
        Properties properties = loadProperties();
        if (properties == null) {
            return null; // Si no se pudieron cargar las propiedades, devuelve null
        }

         url = properties.getProperty("db.url");
         usuario = properties.getProperty("db.user");
         cont = properties.getProperty("db.password");

        try {
            return DriverManager.getConnection(url, usuario, cont);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error de SQL: ", e);
            return null;
        }
    }

    // Constructor privado para evitar instanciación
    private ClaseConexion() {
        // No se permite la instanciación
    }
}
