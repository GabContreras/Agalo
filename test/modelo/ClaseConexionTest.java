package modelo;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*; // Asegúrate de que esta línea esté presente

public class ClaseConexionTest {

    private static Connection connection; // Cambiado a static para que funcione con @BeforeClass y @AfterClass

    @BeforeClass
    public static void setUp() {
        // Inicializar la conexión antes de todas las pruebas
        connection = ClaseConexion.getConexion();
    }

    @AfterClass
    public static void tearDown() throws SQLException {
        // Cerrar la conexión después de todas las pruebas
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Test
    public void testGetConexion() {
        System.out.println("getConexion");
        assertNotNull("La conexión no debería ser nula", connection);
        try {
            assertFalse("La conexión debería estar abierta", connection.isClosed());
        } catch (SQLException e) {
            fail("Error al verificar el estado de la conexión: " + e.getMessage());
        }
    }
}
