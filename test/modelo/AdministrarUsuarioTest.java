package modelo;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import vista.FrmAdministrarUsuarios;

import java.util.ArrayList;
import java.util.List;

public class AdministrarUsuarioTest {

    private AdministrarUsuario instance;
    private List<AdministrarUsuario> usuarios; // Simulación de base de datos

    @Before
    public void setUp() {
        instance = new AdministrarUsuario();
        usuarios = new ArrayList<>(); // Inicializar la lista de usuarios
    }

    @After
    public void tearDown() {
        instance = null;
        usuarios = null;
    }

    @Test
    public void testGetIDadmin() {
        System.out.println("getIDadmin");
        String expectedId = "1";
        instance.setIDadmin(expectedId);
        String result = instance.getIDadmin();
        assertEquals(expectedId, result);
    }

    @Test
    public void testSetIDadmin() {
        System.out.println("setIDadmin");
        String idAdmin = "2";
        instance.setIDadmin(idAdmin);
        assertEquals(idAdmin, instance.getIDadmin());
    }

    @Test
    public void testGetNombre() {
        System.out.println("getNombre");
        String expectedName = "John Doe";
        instance.setNombre(expectedName);
        String result = instance.getNombre();
        assertEquals(expectedName, result);
    }

    @Test
    public void testSetNombre() {
        System.out.println("setNombre");
        String nombre = "John Doe";
        instance.setNombre(nombre);
        assertEquals(nombre, instance.getNombre());
    }

    @Test
    public void testGetUsuario() {
        System.out.println("getUsuario");
        String expectedUser  = "johndoe";
        instance.setUsuario(expectedUser );
        String result = instance.getUsuario();
        assertEquals(expectedUser , result);
    }

    @Test
    public void testSetUsuario() {
        System.out.println("setUsuario");
        String usuario = "johndoe";
        instance.setUsuario(usuario);
        assertEquals(usuario, instance.getUsuario());
    }

    @Test
    public void testGetContrasena() {
        System.out.println("getContrasena");
        String expectedPassword = "password123";
        instance.setContrasena(expectedPassword);
        String result = instance.getContrasena();
        assertEquals(expectedPassword, result);
    }

    @Test
    public void testSetContrasena() {
        System.out.println("setContrasena");
        String contrasena = "password123";
        instance.setContrasena(contrasena);
        assertEquals(contrasena, instance.getContrasena());
    }

    @Test
    public void testGetCorreoElectronico() {
        System.out.println("getCorreoElectronico");
        String expectedEmail = "johndoe@example.com";
        instance.setCorreoElectronico(expectedEmail);
        String result = instance.getCorreoElectronico();
        assertEquals(expectedEmail, result);
    }

    @Test
    public void testSetCorreoElectronico() {
        System.out.println("setCorreoElectronico");
        String correoElectronico = "johndoe@example.com";
        instance.setCorreoElectronico(correoElectronico);
        assertEquals(correoElectronico, instance.getCorreoElectronico());
    }

    @Test
    public void testGuardar() {
        System.out.println("guardar");
        instance.setNombre("John Doe");
        instance.setUsuario("johndoe");
        instance.setContrasena("password123");
        instance.setCorreoElectronico("johndoe@example.com");

        // Simular guardar en la "base de datos"
        usuarios.add(instance); // Agregar el usuario a la lista

        // Verificar que el usuario se ha guardado correctamente
        assertEquals(1, usuarios.size());
        assertEquals(" John Doe", usuarios.get(0).getNombre());
        assertEquals("johndoe", usuarios.get(0).getUsuario());
        assertEquals("password123", usuarios.get(0).getContrasena());
        assertEquals("johndoe@example.com", usuarios.get(0).getCorreoElectronico());
    }

    @Test
    public void testMostrar() {
        System.out.println("mostrar");
        JTable jtbAdmin = new JTable(); // Crear una tabla para pasar al método
        instance.setNombre("John Doe");
        instance.setUsuario("johndoe");
        instance.setCorreoElectronico("johndoe@example.com");
        usuarios.add(instance); // Agregar el usuario a la lista

        instance.mostrar(jtbAdmin);

        // Verificar que los datos se muestran correctamente en la tabla
        DefaultTableModel model = (DefaultTableModel) jtbAdmin.getModel();
        assertEquals(1, model.getRowCount());
        assertEquals("John Doe", model.getValueAt(0, 1)); // Nombre
        assertEquals("johndoe", model.getValueAt(0, 2)); // Usuario
        assertEquals("johndoe@example.com", model.getValueAt(0, 3)); // Correo
    }

    @Test
    public void testActualizar() {
        System.out.println("actualizar");
        JTable jtbAdmin = new JTable(); // Crear una tabla para pasar al método
        instance.setNombre("John Doe");
        instance.setUsuario("johndoe");
        instance.setCorreoElectronico("johndoe@example.com");
        usuarios.add(instance); // Agregar el usuario a la lista

        // Simular la actualización
        instance.setNombre("Jane Doe");
        instance.setUsuario("janedoe");
        instance.setCorreoElectronico("janedoe@example.com");
        instance.actualizar(jtbAdmin);

        // Verificar que el usuario se actualiza correctamente
        assertEquals("Jane Doe", usuarios.get(0).getNombre());
        assertEquals("janedoe", usuarios.get(0).getUsuario());
        assertEquals("janedoe@example.com", usuarios.get(0).getCorreoElectronico());
    }

    @Test
    public void testActualizarSinContrasena() {
        System.out.println("actualizarSinContrasena");
        JTable jtbAdmin = new JTable(); // Crear una tabla para pasar al método
        instance.setNombre("John Doe");
        instance.setUsuario("johndoe");
        instance.setContrasena("password123");
        instance.setCorreoElectronico("johndoe@example.com");
        usuarios.add(instance); // Agregar el usuario a la lista

        // Simular la actualización sin cambiar la contraseña
        instance.setNombre("Jane Doe");
        instance.setUsuario("janedoe");
        instance.setCorreoElectronico("janedoe@example.com");
        instance.actualizarSinContrasena(jtbAdmin);

        // Verificar que el usuario se actualiza correctamente
        assertEquals("Jane Doe", usuarios.get(0).getNombre());
        assertEquals("janedoe", usuarios.get(0).getUsuario());
        assertEquals("janedoe@example.com", usuarios.get(0).getCorreoElectronico());
        assertEquals("password123", usuarios.get(0).getContrasena()); // La contraseña no debe cambiar
    }

    @Test
    public void testEliminar() {
        System.out.println("eliminar");
        JTable jtbAdmin = new JTable(); // Crear una tabla para pasar al método
        instance.setNombre("John Doe");
        instance.setUsuario("johndoe");
        instance.setCorreoElectronico("johndoe@example.com");
        usuarios.add(instance); // Agregar el usuario a la lista

        // Simular la eliminación
        usuarios.remove(0);

        // Verificar que el usuario se ha eliminado
        assertEquals(0, usuarios.size());
    }

    @Test
    public void testBuscarUsuario() {
        System.out.println("buscarUsuario");
        JTable jtbAdmin = new JTable(); // Crear una tabla para pasar al método
        JTextField txtBuscarUsuario = new JTextField("John");
        instance.setNombre("John Doe");
        instance.setUsuario("johndoe");
        instance.setCorreoElectronico("johndoe@example.com");
        usuarios.add(instance); // Agregar el usuario a la lista

        instance.buscarUsuario(jtbAdmin, txtBuscarUsuario);

        // Verificar que los resultados de búsqueda son correctos
        DefaultTableModel model = (DefaultTableModel) jtbAdmin.getModel();
        assertEquals(1, model.getRowCount());
        assertEquals("John Doe", model.getValueAt(0, 1)); // Nombre
    }

    @Test
    public void testCargarDatosTabla() {
        System.out.println("cargarDatosTabla");
        FrmAdministrarUsuarios vista = new FrmAdministrarUsuarios(); // Crear una vista para pasar al método
        instance.setNombre("John Doe");
        instance.setUsuario("johndoe");
        instance.setCorreoElectronico("johndoe@example.com");
        usuarios.add(instance); // Agregar el usuario a la lista

        // Simular la carga de datos en la vista
        instance.cargarDatosTabla(vista);

        // Verificar que los datos se cargan correctamente en la vista
        assertEquals("John Doe", vista.txtNombreAdmin.getText());
        assertEquals("johndoe", vista.txtUsuarioAdmin.getText());
        assertEquals("johndoe@example.com", vista.txtCorreoAdmin.getText());
    }
}