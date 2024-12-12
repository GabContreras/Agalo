package tests;

import modelo.ClaseConexion;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;

public class ClaseConexionTest {

    private Connection connection;

    @Before
    public void setUp() {
        // Aquí puedes configurar cualquier cosa que necesites antes de cada prueba
        connection = ClaseConexion.getConexion();
    }

    @After
    public void tearDown() throws SQLException {
        // Cerrar la conexión después de cada prueba
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Test
    public void testGetConexion() {
        // Verificar que la conexión no sea nula
        assertNotNull("La conexión no debe ser nula", connection);
    }
}