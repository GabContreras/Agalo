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

    //Constantes para los select
    private static final String ID_ADMIN = "IdAdmin";
    private static final String NOMBRE_ADMIN = "Nombre";
    private static final String USUARIO_ADMIN = "Usuario";
    private static final String CORREO_ADMIN = "CorreoElectronico";

    public String getIDadmin() {
        return idAdmin;
    }

    public void setIDadmin(String idAdmin) {
        this.idAdmin = idAdmin;
    }
    private String idAdmin;
    private String nombre;
    private String usuario;
    private String contrasena;
    private String correoElectronico;

    // Getters y setters
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

    // Guardar en la base de datos
    public void guardar() {
        Connection conexion = null;
        PreparedStatement addAdmin = null;

        try {
            conexion = ClaseConexion.getConexion();
            addAdmin = conexion.prepareStatement(
                    "INSERT INTO UsuarioEscritorio (Nombre, Usuario, Contrasena, CorreoElectronico, IdRol) VALUES (?, ?, ?, ?, ?)"
            );
            addAdmin.setString(1, getNombre());
            addAdmin.setString(2, getUsuario());
            addAdmin.setString(3, getContrasena());
            addAdmin.setString(4, getCorreoElectronico());
            addAdmin.setInt(5, 1);

            addAdmin.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error en el método Guardar: ", ex);
        } finally {
            // Cerrar el PreparedStatement
            if (addAdmin != null) {
                try {
                    addAdmin.close();
                } catch (SQLException e) {
                    logger.log(Level.SEVERE, ERR_PREP, e);
                }
            }
            // Cerrar la conexión
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    logger.log(Level.SEVERE, ERR_CONEX, e);
                }
            }
        }
    }

    public void mostrar(JTable jtbAdmin) {
        Connection conexion = null;
        Statement statement = null;
        ResultSet rs = null;
        DefaultTableModel modeloDeDatos = new DefaultTableModel();

        modeloDeDatos.setColumnIdentifiers(new Object[]{
            ID_ADMIN, NOMBRE_ADMIN, USUARIO_ADMIN, CORREO_ADMIN
        });

        try {
            conexion = ClaseConexion.getConexion();
            statement = conexion.createStatement();

            rs = statement.executeQuery(
                    "SELECT IdAdmin, Nombre, Usuario, CorreoElectronico FROM UsuarioEscritorio WHERE IdRol = 1"
            );

            // Recorremos el ResultSet
            while (rs.next()) {
                // Llenamos el modelo por cada vez que recorremos el ResultSet
                modeloDeDatos.addRow(new Object[]{
                    rs.getInt(ID_ADMIN),
                    rs.getString(NOMBRE_ADMIN),
                    rs.getString(USUARIO_ADMIN),
                    rs.getString(CORREO_ADMIN)
                });
            }

            // Asignamos el nuevo modelo lleno a la tabla
            jtbAdmin.setModel(modeloDeDatos);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error en el método Mostrar: ", e);
        } finally {
            // Cerrar el ResultSet
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    logger.log(Level.SEVERE, "Error al cerrar el ResultSet: ", e);
                }
            }
            // Cerrar el Statement
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.log(Level.SEVERE, "Error al cerrar el Statement: ", e);
                }
            }
            // Cerrar la conexión
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    logger.log(Level.SEVERE, ERR_CONEX, e);
                }
            }
        }
    }

// Actualizar usuario en la base de datos
    public void actualizar(JTable jtbAdmin) {
        Connection conexion = null;
        PreparedStatement updateUser = null;

        // Obtener la fila seleccionada
        int filaSeleccionada = jtbAdmin.getSelectedRow();

        if (filaSeleccionada != -1) {
            // Obtener el ID del administrador de la fila seleccionada
            String miUUId = jtbAdmin.getValueAt(filaSeleccionada, 0).toString();

            try {
                conexion = ClaseConexion.getConexion();
                // Sentencia SQL para actualizar
                String sql = "UPDATE UsuarioEscritorio SET Nombre = ?, Usuario = ?, Contrasena = ?, CorreoElectronico = ? WHERE IdAdmin = ?";
                updateUser = conexion.prepareStatement(sql);

                // Asignar los nuevos valores al PreparedStatement
                updateUser.setString(1, getNombre());
                updateUser.setString(2, getUsuario());
                updateUser.setString(3, getContrasena());
                updateUser.setString(4, getCorreoElectronico());
                updateUser.setString(5, miUUId);  // Aquí se pasa el ID como último parámetro

                // Ejecutar la actualización
                updateUser.executeUpdate();

                logger.log(Level.INFO, "Usuario actualizado correctamente.");
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Error al actualizar el usuario: ", e);
            } finally {
                // Cerrar el PreparedStatement
                if (updateUser != null) {
                    try {
                        updateUser.close();
                    } catch (SQLException e) {
                        logger.log(Level.SEVERE, ERR_PREP, e);
                    }
                }
                // Cerrar la conexión
                if (conexion != null) {
                    try {
                        conexion.close();
                    } catch (SQLException e) {
                        logger.log(Level.SEVERE, ERR_CONEX, e);
                    }
                }
            }
        } else {
            logger.log(Level.SEVERE, ERROR_FILAS);
        }
    }

// Actualizar usuario en la base de datos sin cambiar la contraseña
    public void actualizarSinContrasena(JTable jtbAdmin) {
        Connection conexion = null;
        PreparedStatement updateUser = null;

        // Obtener la fila seleccionada
        int filaSeleccionada = jtbAdmin.getSelectedRow();

        if (filaSeleccionada != -1) {
            // Obtener el ID del administrador de la fila seleccionada
            String miUUId = jtbAdmin.getValueAt(filaSeleccionada, 0).toString();

            try {
                conexion = ClaseConexion.getConexion();
                // Sentencia SQL para actualizar
                String sql = "UPDATE UsuarioEscritorio SET Nombre = ?, Usuario = ?, CorreoElectronico = ? WHERE IdAdmin = ?";
                updateUser = conexion.prepareStatement(sql);

                // Asignar los nuevos valores al PreparedStatement
                updateUser.setString(1, getNombre());
                updateUser.setString(2, getUsuario());
                updateUser.setString(3, getCorreoElectronico());
                updateUser.setString(4, miUUId);  // Aquí se pasa el ID como último parámetro

                // Ejecutar la actualización
                updateUser.executeUpdate();

                logger.log(Level.INFO, "Usuario actualizado correctamente.");
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Error al actualizar el usuario: ", e);
            } finally {
                // Cerrar el PreparedStatement
                if (updateUser != null) {
                    try {
                        updateUser.close();
                    } catch (SQLException e) {
                        logger.log(Level.SEVERE, ERR_PREP, e);
                    }
                }
                // Cerrar la conexión
                if (conexion != null) {
                    try {
                        conexion.close();
                    } catch (SQLException e) {
                        logger.log(Level.SEVERE, ERR_CONEX, e);
                    }
                }
            }
        } else {
            logger.log(Level.SEVERE, ERROR_FILAS);
        }
    }

// Eliminar usuario
    public void eliminar(JTable jtbAdmin) {
        Connection conexion = null;
        PreparedStatement deleteEstudiante = null;

        // Obtenemos qué fila seleccionó el usuario
        int filaSeleccionada = jtbAdmin.getSelectedRow();

        if (filaSeleccionada != -1) {
            // Obtenemos el IdAdmin de la fila seleccionada
            String miId = jtbAdmin.getValueAt(filaSeleccionada, 0).toString();

            try {
                conexion = ClaseConexion.getConexion();
                String sql = "DELETE FROM UsuarioEscritorio WHERE IdAdmin = ?";
                deleteEstudiante = conexion.prepareStatement(sql);
                deleteEstudiante.setString(1, miId);
                deleteEstudiante.executeUpdate();
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Este es el error en el método de eliminar: ", e);
            } finally {
                // Cerrar el PreparedStatement
                if (deleteEstudiante != null) {
                    try {
                        deleteEstudiante.close();
                    } catch (SQLException e) {
                        logger.log(Level.SEVERE, ERR_PREP, e);
                    }
                }
                // Cerrar la conexión
                if (conexion != null) {
                    try {
                        conexion.close();
                    } catch (SQLException e) {
                        logger.log(Level.SEVERE, ERR_CONEX, e);
                    }
                }
            }
        } else {
            logger.log(Level.SEVERE, ERROR_FILAS);
        }
    }

    public void buscarUsuario(JTable jtbAdmin, JTextField txtBuscarUsuario) {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DefaultTableModel modelo = (DefaultTableModel) jtbAdmin.getModel(); // Reutiliza el modelo existente

        // Limpia el modelo antes de llenarlo con los nuevos resultados
        modelo.setRowCount(0); // Limpia las filas existentes

        try {
            conexion = ClaseConexion.getConexion();
            ps = conexion.prepareStatement(
                    "SELECT IdAdmin, Nombre, Usuario, CorreoElectronico FROM UsuarioEscritorio WHERE IdRol = 1 AND Nombre LIKE ?"
            );
            ps.setString(1, txtBuscarUsuario.getText() + "%"); // Agregar el '%' aquí
            rs = ps.executeQuery();

            // Recorremos el ResultSet
            while (rs.next()) {
                // Llenamos el modelo por cada vez que recorremos el ResultSet
                modelo.addRow(new Object[]{
                    rs.getInt(ID_ADMIN),
                    rs.getString(NOMBRE_ADMIN),
                    rs.getString(USUARIO_ADMIN),
                    rs.getString(CORREO_ADMIN)
                });
            }

            // Asignamos el nuevo modelo lleno a la tabla
            jtbAdmin.setModel(modelo);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error en buscar Usuario: ", e);
        } finally {
            // Cerrar el ResultSet
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    logger.log(Level.SEVERE, "Error al cerrar el ResultSet: ", e);
                }
            }
            // Cerrar el PreparedStatement
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    logger.log(Level.SEVERE, ERR_PREP, e);
                }
            }
            // Cerrar la conexión
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    logger.log(Level.SEVERE, ERR_CONEX, e);
                }
            }
        }
    }

// Cargar los datos del usuario seleccionado desde la tabla
    public void cargarDatosTabla(FrmAdministrarUsuarios vista) {
        int filaSeleccionada = vista.jtbAdmin.getSelectedRow();

        if (filaSeleccionada != -1) {
            // Obtener los valores de las columnas correspondientes
            String name = vista.jtbAdmin.getValueAt(filaSeleccionada, 1).toString(); // Columna Nombre
            String user = vista.jtbAdmin.getValueAt(filaSeleccionada, 2).toString(); // Columna Usuario
            String correo = vista.jtbAdmin.getValueAt(filaSeleccionada, 3).toString(); // Columna CorreoElectronico

            // Asignar los valores a los campos de texto en la vista
            vista.txtNombreAdmin.setText(name);
            vista.txtUsuarioAdmin.setText(user);
            vista.txtCorreoAdmin.setText(correo);
        } else {
            logger.log(Level.SEVERE, ERROR_FILAS);
        }
    }
}
