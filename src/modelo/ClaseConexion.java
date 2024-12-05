package modelo;

import java.sql.*;

public class ClaseConexion {

    // Variables para la cadena de conexión
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USUARIO = "agalo"; // Agregar usuario local antes de iniciar sesión
    private static final String CONT = "agalo"; // Agregar contraseña local antes de iniciar sesión

// Constructor privado para evitar instanciación
    private ClaseConexion() {
        // No se permite la instanciación
    }

// Creación del método de conexión que retorna la conexión
    public static Connection getConexion() {
        try {
            // Cargar el driver JDBC (comentariado porque hoy en día no es necesario)
//            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Obtener la conexión en una variable
            //NOTA: sonar indica que es mejor retornar directamente la conexion
           return DriverManager.getConnection(URL, USUARIO, CONT);
            // Retornamos la variable que tiene la conexión
        } catch (SQLException e) {
            System.out.println("Este es el error: " + e);
            return null;
//        } catch (ClassNotFoundException ex) {
//            System.out.println("Este es el error de la clase: " + ex);
//            return null;
        }
    }
}
