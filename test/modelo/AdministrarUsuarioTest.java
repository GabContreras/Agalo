package modelo;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import vista.FrmAdministrarUsuarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdministrarUsuarioTest {

    private Connection connection; // Declarar la variable de conexión
    private AdministrarUsuario instance;
    private JTable jtbAdmin;
    private FrmAdministrarUsuarios vista;

    @Before
    public void setUp() {
        // Configurar la conexión y la transacción
        connection = ClaseConexion.getConexion();
        try {
            connection.setAutoCommit(false); // Desactivar el autocommit
        } catch (SQLException ex) {
            Logger.getLogger(AdministrarUsuarioTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Inicializar la instancia y la vista
        instance = new AdministrarUsuario();
        jtbAdmin = new JTable(new DefaultTableModel(new Object[]{"IdAdmin", "Nombre", "Usuario", "CorreoElectronico"}, 0));
        vista = new FrmAdministrarUsuarios(); // Simular la vista
    }

    @Test
    public void testGetIDadmin() {
        String expectedId = "1";
        instance.setIDadmin(expectedId);
        assertEquals(expectedId, instance.getIDadmin());
        eliminarUsuarioSiExiste("johndoe");
        eliminarUsuarioSiExiste("janedoe"); // Eliminar al finalizar la prueba
    }

    @Test
    public void testSetIDadmin() {
        String idAdmin = "2";
        instance.setIDadmin(idAdmin);
        assertEquals(idAdmin, instance.getIDadmin());
        eliminarUsuarioSiExiste("johndoe");
        eliminarUsuarioSiExiste("janedoe"); // Eliminar al finalizar la prueba
    }

    @Test
    public void testGetNombre() {
        String expectedName = "John Doe";
        instance.setNombre(expectedName);
        assertEquals(expectedName, instance.getNombre());
        eliminarUsuarioSiExiste("johndoe");
        eliminarUsuarioSiExiste("janedoe"); // Eliminar al finalizar la prueba
    }

    @Test
    public void testSetNombre() {
        String nombre = "John Doe";
        instance.setNombre(nombre);
        assertEquals(nombre, instance.getNombre());
        eliminarUsuarioSiExiste("johndoe");
        eliminarUsuarioSiExiste("janedoe"); // Eliminar al finalizar la prueba
    }

    @Test
    public void testGetUsuario() {
        String expectedUser = "johndoe";
        instance.setUsuario(expectedUser);
        assertEquals(expectedUser, instance.getUsuario());
        eliminarUsuarioSiExiste("johndoe");
        eliminarUsuarioSiExiste("janedoe"); // Eliminar al finalizar la prueba    
    }

    @Test
    public void testSetUsuario() {
        String usuario = "johndoe";
        instance.setUsuario(usuario);
        assertEquals(usuario, instance.getUsuario());
        eliminarUsuarioSiExiste("johndoe");
        eliminarUsuarioSiExiste("janedoe"); // Eliminar al finalizar la prueba   
    }

    @Test
    public void testGetContrasena() {
        String expectedPassword = "password123";
        instance.setContrasena(expectedPassword);
        assertEquals(expectedPassword, instance.getContrasena());
        eliminarUsuarioSiExiste("johndoe");
        eliminarUsuarioSiExiste("janedoe"); // Eliminar al finalizar la prueba
    }

    @Test
    public void testSetContrasena() {
        String contrasena = "password123";
        instance.setContrasena(contrasena);
        assertEquals(contrasena, instance.getContrasena());
        eliminarUsuarioSiExiste("johndoe");
        eliminarUsuarioSiExiste("janedoe"); // Eliminar al finalizar la prueba
    }

    @Test
    public void testGetCorreoElectronico() {
        String expectedEmail = "johndoe@example.com";
        instance.setCorreoElectronico(expectedEmail);
        assertEquals(expectedEmail, instance.getCorreoElectronico());
        eliminarUsuarioSiExiste("johndoe");
        eliminarUsuarioSiExiste("janedoe"); // Eliminar al finalizar la prueba
    }

    @Test
    public void testSetCorreoElectronico() {
        String correoElectronico = "johndoe@example.com";
        instance.setCorreoElectronico(correoElectronico);
        assertEquals(correoElectronico, instance.getCorreoElectronico());
        eliminarUsuarioSiExiste("johndoe");
        eliminarUsuarioSiExiste("janedoe"); // Eliminar al finalizar la prueba
    }

    @Test
    public void testGuardar() {
        instance.setNombre("John Doe");
        instance.setUsuario("johndoe");
        instance.setContrasena("password123");
        instance.setCorreoElectronico("johndoe@example.com");

        // Guardar en la base de datos
        instance.guardar();

        // Verificar que el usuario se ha guardado correctamente
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM UsuarioEscritorio WHERE Usuario = ?");
            ps.setString(1, "johndoe");
            ResultSet rs = ps.executeQuery();
            assertTrue(rs.next()); // Debe haber al menos un resultado
            assertEquals("John Doe", rs.getString("Nombre"));
            assertEquals("johndoe@example.com", rs.getString("CorreoElectronico"));
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            eliminarUsuarioSiExiste("johndoe");
            eliminarUsuarioSiExiste("janedoe"); // Eliminar al finalizar la prueba
        }
    }

    @Test
    public void testActualizar() {
        // Primero, guardar un usuario
        instance.setNombre("John Doe");
        instance.setUsuario("johndoe");
        instance.setContrasena("password123");
        instance.setCorreoElectronico("johndoe@example.com");
        instance.guardar();

        // Simular la carga de datos en la tabla
        instance.mostrar(jtbAdmin);
        jtbAdmin.setRowSelectionInterval(0, 0); // Seleccionar la primera fila

        // Simular la actualización
        instance.setNombre("Jane Doe");
        instance.setUsuario("janedoe");
        instance.setCorreoElectronico("janedoe@example.com");
        instance.actualizar(jtbAdmin);

        // Verificar que el usuario se actualiza correctamente en la base de datos
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM UsuarioEscritorio WHERE Usuario = ?");
            ps.setString(1, "janedoe");
            ResultSet rs = ps.executeQuery();
            assertTrue(rs.next()); // Debe haber al menos un resultado
            assertEquals("Jane Doe", rs.getString("Nombre"));
            assertEquals("janedoe@example.com", rs.getString("CorreoElectronico"));
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            eliminarUsuarioSiExiste("johndoe");
            eliminarUsuarioSiExiste("janedoe"); // Eliminar al finalizar la prueba
        }
    }

    @Test
    public void testEliminar() {
        // Primero, guardar un usuario
        instance.setNombre("John Doe");
        instance.setUsuario("johndoe");
        instance.setContrasena("password123");
        instance.setCorreoElectronico("johndoe@example.com");
        instance.guardar();

        // Simular la carga de datos en la tabla
        instance.mostrar(jtbAdmin);
        jtbAdmin.setRowSelectionInterval(0, 0); // Seleccionar la primera fila

        // Ahora, eliminar el usuario
        instance.eliminar(jtbAdmin);

        // Verificar que el usuario se ha eliminado
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM UsuarioEscritorio WHERE Usuario = ?");
            ps.setString(1, "johndoe");
            ResultSet rs = ps.executeQuery();
            assertFalse(rs.next()); // No debe haber resultados
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            eliminarUsuarioSiExiste("johndoe");
            eliminarUsuarioSiExiste("janedoe"); // Eliminar al finalizar la prueba
        }
    }
    // Método auxiliar para eliminar un usuario si existe 

    private void eliminarUsuarioSiExiste(String usuario) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM UsuarioEscritorio WHERE Usuario = ?");
            ps.setString(1, usuario);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
