package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import vista.FrmAdministrarUsuarios;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AdministrarUsuario {

    private static final Logger logger = Logger.getLogger(AdministrarUsuario.class.getName());

    private static final String ERROR_FILAS = "No se ha seleccionado ninguna fila.";
    private static final String ERR_PREP = "Error al cerrar el PreparedStatement: ";
    private static final String ERR_CONEX = "Error al cerrar la conexión: ";

    private static final String ID_ADMIN = "IdAdmin";
    private static final String NOMBRE_ADMIN = "Nombre";
    private static final String USUARIO_ADMIN = "Usuario";
    private static final String CORREO_ADMIN = "CorreoElectronico";

    private String idAdmin;
    private String nombre;
    private String usuario;
    private String contrasena;
    private String correoElectronico;

    // Getters y setters
    public String getIDadmin() {
        return idAdmin;
    }

    public void setIDadmin(String idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public void guardar() {
        String sql = "INSERT INTO UsuarioEscritorio (Nombre, Usuario, Contrasena, CorreoElectronico, IdRol) VALUES (?, ?, ?, ?, ?)";
        try (Connection conexion = ClaseConexion.getConexion(); PreparedStatement addAdmin = conexion.prepareStatement(sql)) {

            addAdmin.setString(1, getNombre());
            addAdmin.setString(2, getUsuario());
            addAdmin.setString(3, getContrasena());
            addAdmin.setString(4, getCorreoElectronico());
            addAdmin.setInt(5, 1); // Asegúrate de establecer IdRol a 1
            addAdmin.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error en el método Guardar: ", ex);
        }
    }

    public void mostrar(JTable jtbAdmin) {
        String sql = "SELECT IdAdmin, Nombre, Usuario, CorreoElectronico FROM UsuarioEscritorio WHERE IdRol = 1";
        DefaultTableModel modeloDeDatos = new DefaultTableModel();
        modeloDeDatos.setColumnIdentifiers(new Object[]{ID_ADMIN, NOMBRE_ADMIN, USUARIO_ADMIN, CORREO_ADMIN});

        try (Connection conexion = ClaseConexion.getConexion(); Statement statement = conexion.createStatement(); ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                modeloDeDatos.addRow(new Object[]{
                    rs.getInt(ID_ADMIN),
                    rs.getString(NOMBRE_ADMIN),
                    rs.getString(USUARIO_ADMIN),
                    rs.getString(CORREO_ADMIN)
                });
            }
            jtbAdmin.setModel(modeloDeDatos);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error en el método Mostrar: ", e);
        }
    }

    public void actualizar(JTable jtbAdmin) {
        int filaSeleccionada = jtbAdmin.getSelectedRow();
        if (filaSeleccionada == -1) {
            logger.log(Level.SEVERE, ERROR_FILAS);
            return;
        }

        String miUUId = jtbAdmin.getValueAt(filaSeleccionada, 0).toString();
        String sql = "UPDATE UsuarioEscritorio SET Nombre = ?, Usuario = ?, Contrasena = ?, CorreoElectronico = ? WHERE IdAdmin = ?";

        try (Connection conexion = ClaseConexion.getConexion(); PreparedStatement updateUser = conexion.prepareStatement(sql)) {

            updateUser.setString(1, getNombre());
            updateUser.setString(2, getUsuario());
            updateUser.setString(3, getContrasena());
            updateUser.setString(4, getCorreoElectronico()
            );
            updateUser.setString(5, miUUId);
            updateUser.executeUpdate();
            logger.log(Level.INFO, "Usuario actualizado correctamente.");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al actualizar el usuario: ", e);
        }
    }

    public void actualizarSinContrasena(JTable jtbAdmin) {
        int filaSeleccionada = jtbAdmin.getSelectedRow();
        if (filaSeleccionada == -1) {
            logger.log(Level.SEVERE, ERROR_FILAS);
            return;
        }

        String miUUId = jtbAdmin.getValueAt(filaSeleccionada, 0).toString();
        String sql = "UPDATE UsuarioEscritorio SET Nombre = ?, Usuario = ?, CorreoElectronico = ? WHERE IdAdmin = ?";

        try (Connection conexion = ClaseConexion.getConexion(); PreparedStatement updateUser = conexion.prepareStatement(sql)) {

            updateUser.setString(1, getNombre());
            updateUser.setString(2, getUsuario());
            updateUser.setString(3, getCorreoElectronico());
            updateUser.setString(4, miUUId);
            updateUser.executeUpdate();
            logger.log(Level.INFO, "Usuario actualizado correctamente.");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al actualizar el usuario: ", e);
        }
    }

    public void eliminar(JTable jtbAdmin) {
        int filaSeleccionada = jtbAdmin.getSelectedRow();
        if (filaSeleccionada == -1) {
            logger.log(Level.SEVERE, ERROR_FILAS);
            return;
        }

        String miId = jtbAdmin.getValueAt(filaSeleccionada, 0).toString();
        String sql = "DELETE FROM UsuarioEscritorio WHERE IdAdmin = ?";

        try (Connection conexion = ClaseConexion.getConexion(); PreparedStatement deleteEstudiante = conexion.prepareStatement(sql)) {

            deleteEstudiante.setString(1, miId);
            deleteEstudiante.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error en el método de eliminar: ", e);
        }
    }

    public void buscarUsuario(JTable jtbAdmin, JTextField txtBuscarUsuario) {
        String sql = "SELECT IdAdmin, Nombre, Usuario, CorreoElectronico FROM UsuarioEscritorio WHERE IdRol = 1 AND Nombre LIKE ?";
        DefaultTableModel modelo = (DefaultTableModel) jtbAdmin.getModel();
        modelo.setRowCount(0);

        try (Connection conexion = ClaseConexion.getConexion(); PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, txtBuscarUsuario.getText() + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    modelo.addRow(new Object[]{
                        rs.getInt(ID_ADMIN),
                        rs.getString(NOMBRE_ADMIN),
                        rs.getString(USUARIO_ADMIN),
                        rs.getString(CORREO_ADMIN)
                    });
                }
            }
            jtbAdmin.setModel(modelo);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error en buscar Usuario: ", e);
        }
    }

    public void cargarDatosTabla(FrmAdministrarUsuarios vista) {
        int filaSeleccionada = vista.jtbAdmin.getSelectedRow();
        if (filaSeleccionada == -1) {
            logger.log(Level.SEVERE, ERROR_FILAS);
            return;
        }

        String name = vista.jtbAdmin.getValueAt(filaSeleccionada, 1).toString();
        String user = vista.jtbAdmin.getValueAt(filaSeleccionada, 2).toString();
        String correo = vista.jtbAdmin.getValueAt(filaSeleccionada, 3).toString();

        vista.txtNombreAdmin.setText(name);
        vista.txtUsuarioAdmin.setText(user);
        vista.txtCorreoAdmin.setText(correo);
    }
}
