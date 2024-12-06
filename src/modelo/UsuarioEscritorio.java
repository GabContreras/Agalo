package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Jero
 */
public class UsuarioEscritorio {
// Instancia de logger para sustituir los system out 
    private static final Logger logger = Logger.getLogger(UsuarioEscritorio.class.getName());

    public int getIdRol() {
        return IdRol;
    }

    public void setIdRol(int IdRol) {
        this.IdRol = IdRol;
    }

    private String Nombre;
    private String Usuario;
    private String Correo;
    private String Contrasena;
    private int IdRol;  // Rol del usuario

    // Getters y Setters
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String Contrasena) {
        this.Contrasena = Contrasena;
    }

    public void GuardarUsuario() throws SQLException {
        Connection conexion = ClaseConexion.getConexion();
        if (conexion == null) {
            throw new SQLException("No se pudo establecer conexión con la base de datos.");
        }
        PreparedStatement addUsuarioEscritorio = null;
        PreparedStatement checkSuperAdmin = null;
        ResultSet rs = null;

        try {
            // Verificar si ya existe un superadmin
            String sqlCheckSuperAdmin = "SELECT COUNT(*) FROM UsuarioEscritorio WHERE idrol = 2";
            checkSuperAdmin = conexion.prepareStatement(sqlCheckSuperAdmin);
            rs = checkSuperAdmin.executeQuery();
            rs.next();

            if (rs.getInt(1) > 0) {
                // Si ya existe un superadmin, lanzar una excepción
                throw new SQLException("Ya existe un Super Admin registrado, para poder tener una cuenta con los privilegios necesarios, comunicarse con la empresa.");
            } else {
                // Si no existe, insertar el nuevo usuario
                String sqlInsert = "INSERT INTO UsuarioEscritorio (Nombre, Usuario, CorreoElectronico, Contrasena, idrol) VALUES (?, ?, ?, ?, ?)";
                addUsuarioEscritorio = conexion.prepareStatement(sqlInsert);
                addUsuarioEscritorio.setString(1, getNombre());
                addUsuarioEscritorio.setString(2, getUsuario());
                addUsuarioEscritorio.setString(3, getCorreo());
                addUsuarioEscritorio.setString(4, getContrasena());
                addUsuarioEscritorio.setInt(5, 2); // Asignar rol 2 (superadmin)
                addUsuarioEscritorio.executeUpdate();
                System.out.println("Usuario guardado correctamente.");
            }

        } catch (SQLException ex) {
            // Manejo de errores de la base de datos
            throw new SQLException(ex.getMessage());
        }
    }

    public int obtenerRol(String correo, String contrasena) {
        String query = "SELECT idRol FROM UsuarioEscritorio WHERE CorreoElectronico = ? AND Contrasena = ?";
        try (Connection conexion = ClaseConexion.getConexion(); PreparedStatement pst = conexion.prepareStatement(query)) {
            pst.setString(1, correo);
            pst.setString(2, contrasena);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("IdRol");
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error en la consulta SQL:", e);
        }
        return -1;  // Retorna -1 si no se encuentra el Usuario
    }

    // Método para iniciar sesión
    public boolean iniciarSesion() {
        Connection conexion = ClaseConexion.getConexion();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean resultado = false;

        try {
            String sql = "SELECT * FROM UsuarioEscritorio WHERE CorreoElectronico = ? AND Contrasena = ?";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, getCorreo());
            statement.setString(2, getContrasena());

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                this.IdRol = resultSet.getInt("IdRol"); // Obtener el rol
                resultado = true;
            }

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error en el método iniciarSesion:", ex);

        } finally {
            // Cerrar recursos
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar recursos: " + ex.getMessage());
            }
        }

        return resultado;
    }

    public void actualizar_contra(String correo, String contrasena) {
        Connection con = null;
        PreparedStatement query = null;
        ResultSet rs = null;

        try {
            con = ClaseConexion.getConexion();

            // Primero, verificamos si el correo existe en la base de datos
            String sqlSelect = "SELECT COUNT(*) FROM UsuarioEscritorio WHERE correoelectronico = ?";
            query = con.prepareStatement(sqlSelect);
            query.setString(1, correo);

            rs = query.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                // El correo existe, ahora actualizamos la contraseña
                String sqlUpdate = "UPDATE UsuarioEscritorio SET contrasena = ? WHERE correoelectronico = ?";
                query = con.prepareStatement(sqlUpdate);
                query.setString(1, contrasena);
                query.setString(2, correo);

                int filasActualizadas = query.executeUpdate();
                if (filasActualizadas > 0) {
                    System.out.println("Contraseña actualizada correctamente.");
                } else {
                    System.out.println("Error al actualizar la contraseña.");
                }
            } else {
                // No se encontró el correo
                System.out.println("No se encontró el usuario con ese correo.");
            }

        } catch (Exception e) {

            logger.log(Level.SEVERE, "Error al actualizar la contraseña:", e);

        }
    }

    public boolean verificarCorreoExistente(String correo) {
        try {
            Connection con = ClaseConexion.getConexion();
            String sql = "SELECT COUNT(*) FROM UsuarioEscritorio WHERE correoelectronico = ?";
            PreparedStatement query = con.prepareStatement(sql);
            query.setString(1, correo);

            ResultSet rs = query.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Retorna true si existe al menos un registro
            }
        } catch (Exception e) {
            System.out.println("Error al verificar el correo: " + e.getMessage());
        }
        return false; // Retorna false si no se encontró el correo
    }

}
