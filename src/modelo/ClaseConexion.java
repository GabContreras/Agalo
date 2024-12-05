package modelo;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClaseConexion {

    // Variables para la cadena de conexión
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USUARIO = ""; // Agregar usuario local antes de iniciar sesión
    private static final String CONT = ""; // Agregar contraseña local antes de iniciar sesión

    //Instancia de logger para sustituir los system out 
    private static final Logger logger = Logger.getLogger(ClaseConexion.class.getName());
// Constructor privado para evitar instanciación

    private ClaseConexion() {
        // No se permite la instanciación
    }

// Creación del método de conexión que retorna la conexión
    public static Connection getConexion() {
        try {
            // Cargar el driver JDBC (comentariado porque hoy en día no es necesario)

            // Obtener la conexión en una variable
            //NOTA: sonar indica que es mejor retornar directamente la conexion
            return DriverManager.getConnection(URL, USUARIO, CONT);
            // Retornamos la variable que tiene la conexión
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error de SQL: ", e);
            return null;
        }
    }
}
