package modelo;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.SQLException;

public class ClaseConexionTest {
    
    private static Connection connection;

    @BeforeClass
    public static void setUp() {
        // Establecer la propiedad del sistema para el archivo de configuración
        System.setProperty("config.file", "config-valid(test).properties");
        connection = ClaseConexion.getConexion();
    }

    @AfterClass
    public static void tearDown() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Test
    public void testGetConexionValid() {
        Connection validConnection = ClaseConexion.getConexion();
        assertNotNull("La conexión no debería ser nula", validConnection);
        try {
            assertFalse("La conexión debería estar abierta", validConnection.isClosed());
        } catch (SQLException e) {
            fail("Error al verificar el estado de la conexión: " + e.getMessage());
        } finally {
            try {
                validConnection.close();
            } catch (SQLException e) {
                fail("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    @Test
    public void testGetConexionInvalid() {
        // Cambia el archivo de configuración a uno con valores inválidos
        System.setProperty("config.file", "config-invalid(test).properties");
        Connection invalidConnection = ClaseConexion.getConexion();
        assertNull("La conexión debería ser nula si las propiedades son incorrectas", invalidConnection);
    }

}
