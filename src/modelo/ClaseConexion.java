package modelo;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.InputStream;
import java.io.IOException;

public class ClaseConexion {
    // Variables para la cadena de conexión
    private static String Url;
    private static String Usuario;
    private static String Cont;
    
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
            Url = properties.getProperty("db.url");
            Usuario = properties.getProperty("db.user");
            Cont = properties.getProperty("db.password");
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
            return DriverManager.getConnection(Url, Usuario, Cont);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error de SQL: ", e);
            return null;
        }
    }
    
    @Test
    public void testGetConexion() {
        // Intentar obtener una conexión
        Connection conexion = ClaseConexion.getConexion();

        // Verificar que la conexión no sea nula
        assertNotNull("La conexión no debe ser nula", conexion);

        // Cerrar la conexión si se ha establecido
        if (conexion != null) {
            try {
                conexion.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}