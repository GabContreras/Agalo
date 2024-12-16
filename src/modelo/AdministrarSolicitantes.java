/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Contr
 */
public class AdministrarSolicitantes {

    private static final Logger logger = Logger.getLogger(AdministrarSolicitantes.class.getName());

//Constantes para los select
    private static final String ID_SOLICITANTE = "Id";
    private static final String NOMBRE_SOLICITANTE = "NombreSolicitante";
    private static final String CORREO_SOLICITANTE = "Correo";
    private static final String TELEFONO_SOLICITANTE = "Teléfono";
    private static final String DIRECCION_SOLICITANTE = "Dirección";
    private static final String DEPARTAMENTO_SOLICITANTE = "NombreDepartamento";

    public String getCorreoSolicitante() {
        return correoSolicitante;
    }

    public void setCorreoSolicitante(String correoSolicitante) {
        this.correoSolicitante = correoSolicitante;
    }

    public String getUuidSolicitante() {
        return uuidSolicitante;
    }

    public void setUuidSolicitante(String uuidSolicitante) {
        this.uuidSolicitante = uuidSolicitante;
    }

    private String correoSolicitante;
    private String uuidSolicitante;

    public void restringirSolicitante(JTable tabla) {
        Connection conexion = null;
        PreparedStatement updateSolicitante = null;

        try {
            // Creamos una variable igual a ejecutar el método de la clase de conexión
            conexion = ClaseConexion.getConexion();

            // Obtenemos qué fila seleccionó el usuario
            int filaSeleccionada = tabla.getSelectedRow();
            if (filaSeleccionada != -1) {
                // Obtenemos el id de la fila seleccionada
                String idSolicitante = tabla.getValueAt(filaSeleccionada, 0).toString(); // El IdSolicitante está en la primera columna
                // Ejecutamos la Query
                updateSolicitante = conexion.prepareStatement("UPDATE solicitante SET EstadoCuenta = 'Restringido' WHERE IdSolicitante = ?");
                updateSolicitante.setString(1, idSolicitante);
                updateSolicitante.executeUpdate();
            } else {
                logger.log(Level.WARNING, "No se ha seleccionado ningún solicitante.");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Este es el error en el método de restringir: ", e);
        } finally {
            // Cerrar el PreparedStatement
            if (updateSolicitante != null) {
                try {
                    updateSolicitante.close();
                } catch (SQLException e) {
                    logger.log(Level.SEVERE, "Error al cerrar el PreparedStatement: ", e);
                }
            }
            // Cerrar la conexión
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    logger.log(Level.SEVERE, "Error al cerrar la conexión: ", e);
                }
            }
        }
    }

    public void buscarSolicitante(JTable jtbAdmin, JTextField txtBuscarSolicitante) {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DefaultTableModel modelo = (DefaultTableModel) jtbAdmin.getModel(); // Reutiliza el modelo existente

        // Limpia el modelo antes de llenarlo con los nuevos resultados
        modelo.setRowCount(0); // Limpia las filas existentes

        try {
            conexion = ClaseConexion.getConexion();
            ps = conexion.prepareStatement(
                    "SELECT s.IdSolicitante as Id, s.Nombre as NombreSolicitante, s.CorreoElectronico as Correo, "
                    + "s.Telefono as Teléfono, s.Direccion as Dirección, d.Nombre AS NombreDepartamento "
                    + "FROM Solicitante s "
                    + "INNER JOIN DEPARTAMENTO d ON s.IdDepartamento = d.IdDepartamento "
                    + "WHERE s.EstadoCuenta = 'Activo' AND s.Nombre LIKE ?"
            );
            ps.setString(1, txtBuscarSolicitante.getText() + "%"); // Agregar el '%' aquí
            rs = ps.executeQuery();

            // Recorremos el ResultSet
            while (rs.next()) {
                // Llenamos el modelo por cada vez que recorremos el ResultSet
                modelo.addRow(new Object[]{
                    rs.getString(ID_SOLICITANTE),
                    rs.getString(NOMBRE_SOLICITANTE),
                    rs.getString(CORREO_SOLICITANTE),
                    rs.getString(TELEFONO_SOLICITANTE),
                    rs.getString(DIRECCION_SOLICITANTE),
                    rs.getString(DEPARTAMENTO_SOLICITANTE)
                });
            }

            // Asignamos el nuevo modelo lleno a la tabla
            jtbAdmin.setModel(modelo);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error en buscar Solicitante: ", e);
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
                    logger.log(Level.SEVERE, "Error al cerrar el PreparedStatement: ", e);
                }
            }
            // Cerrar la conexión
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    logger.log(Level.SEVERE, "Error al cerrar la conexión: ", e);
                }
            }
        }
    }

    public void mostrarSolicitantes(JTable jtSolicitudSolicitante) {
        Connection conexion = null;
        Statement statement = null;
        ResultSet rs = null;
        DefaultTableModel modeloDeDatos = new DefaultTableModel();

        modeloDeDatos.setColumnIdentifiers(new Object[]{
            ID_SOLICITANTE, NOMBRE_SOLICITANTE, CORREO_SOLICITANTE, TELEFONO_SOLICITANTE, DIRECCION_SOLICITANTE, DEPARTAMENTO_SOLICITANTE
        });

        try {
            // Creamos una variable de la clase de conexión
            conexion = ClaseConexion.getConexion();

            // Creamos un Statement
            statement = conexion.createStatement();

            // Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            rs = statement.executeQuery(
                    "SELECT s.IdSolicitante as Id, s.Nombre as NombreSolicitante, s.CorreoElectronico as Correo, "
                    + "s.Telefono as Teléfono, s.Direccion as Dirección, d.Nombre AS NombreDepartamento "
                    + "FROM Solicitante s "
                    + "INNER JOIN DEPARTAMENTO d ON s.IdDepartamento = d.IdDepartamento "
                    + "WHERE s.EstadoCuenta = 'Activo'"
            );

            // Recorremos el ResultSet
            while (rs.next()) {
                // Llenamos el modelo por cada vez que recorremos el ResultSet
                modeloDeDatos.addRow(new Object[]{
                    rs.getString(ID_SOLICITANTE),
                    rs.getString(NOMBRE_SOLICITANTE),
                    rs.getString(CORREO_SOLICITANTE),
                    rs.getString(TELEFONO_SOLICITANTE),
                    rs.getString(DIRECCION_SOLICITANTE),
                    rs.getString(DEPARTAMENTO_SOLICITANTE)
                });
            }

            // Asignamos el nuevo modelo lleno a la tabla
            jtSolicitudSolicitante.setModel(modeloDeDatos);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Este es el error en el modelo, método mostrar ", e);
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
                    logger.log(Level.SEVERE, "Error al cerrar la conexión: ", e);
                }
            }
        }
    }
}
