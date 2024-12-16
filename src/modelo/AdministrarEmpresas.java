/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class AdministrarEmpresas {

    private static final Logger logger = Logger.getLogger(AdministrarEmpresas.class.getName());

    //Constantes para los select
    private static final String ID_EMPLEADOR = "Id";
    private static final String NOMBRE_EMPRESA = "Empresa";
    private static final String NOMBRE_REPRESENTANTE = "Representante";
    private static final String CORREO_REPRESENTANTE = "Correo";
    private static final String TELEFONO_REPRESENTANTE = "Teléfono";
    private static final String DIRECCION_REPRESENTANTE = "Dirección";
    private static final String DEPARTAMENTO_REPRESENTANTE = "Departamento";

    private String nombreEmpresa;
    private String uuidEmpresa;

    public String getUuidEmpresa() {
        return uuidEmpresa;
    }

    public void setUuidEmpresa(String uuidEmpresa) {
        this.uuidEmpresa = uuidEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public void mostrarEmpresas(JTable jtSolicitudEmpresa) {
        Connection conexion = null;
        Statement statement = null;
        ResultSet rs = null;
        DefaultTableModel modeloDeDatos = new DefaultTableModel();

        modeloDeDatos.setColumnIdentifiers(new Object[]{
            ID_EMPLEADOR, NOMBRE_EMPRESA, NOMBRE_REPRESENTANTE, CORREO_REPRESENTANTE, TELEFONO_REPRESENTANTE, DIRECCION_REPRESENTANTE, DEPARTAMENTO_REPRESENTANTE
        });

        try {
            // Creamos una variable de la clase de conexión
            conexion = ClaseConexion.getConexion();

            // Creamos un Statement
            statement = conexion.createStatement();

            // Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            rs = statement.executeQuery(
                    "SELECT e.IdEmpleador as Id, e.NombreEmpresa as Empresa, e.NombreRepresentante as Representante, "
                    + "e.CorreoElectronico as Correo, e.NumeroTelefono as Teléfono, e.Direccion as Dirección, "
                    + "d.Nombre AS Departamento "
                    + "FROM Empleador e "
                    + "INNER JOIN DEPARTAMENTO d ON e.IdDepartamento = d.IdDepartamento "
                    + "WHERE e.Estado = 'Pendiente'"
            );

            // Recorremos el ResultSet
            while (rs.next()) {
                // Llenamos el modelo por cada vez que recorremos el ResultSet
                modeloDeDatos.addRow(new Object[]{
                    rs.getString("Id"),
                    rs.getString("Empresa"),
                    rs.getString("Representante"),
                    rs.getString("Correo"),
                    rs.getString("Teléfono"),
                    rs.getString("Dirección"),
                    rs.getString("Departamento")
                });
            }

            // Asignamos el nuevo modelo lleno a la tabla
            jtSolicitudEmpresa.setModel(modeloDeDatos);
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

// Eliminar empresa
    public void rechazarEmpresa(JTable tabla) {
        Connection conexion = null;
        PreparedStatement ps = null;

        try {
            conexion = ClaseConexion.getConexion();
            int filaSeleccionada = tabla.getSelectedRow();

            if (filaSeleccionada != -1) {
                // Obtenemos el IdEmpleador de la fila seleccionada
                String idEmpleador = tabla.getValueAt(filaSeleccionada, 0).toString(); // Asumiendo que el IdEmpleador está en la primera columna

                ps = conexion.prepareStatement("DELETE FROM empleador WHERE IdEmpleador = ?");
                ps.setString(1, idEmpleador);
                ps.executeUpdate();
            } else {
                logger.log(Level.WARNING, "No se ha seleccionado ninguna empresa.");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al rechazar a la empresa: ", e);
        } finally {
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

    public void actualizarEstadoActivo(JTable tabla) {
        Connection conexion = null;
        PreparedStatement updateEmpresa = null;

        try {
            // Creamos una variable igual a ejecutar el método de la clase de conexión
            conexion = ClaseConexion.getConexion();

            // Obtenemos qué fila seleccionó el usuario
            int filaSeleccionada = tabla.getSelectedRow();
            if (filaSeleccionada != -1) {
                // Obtenemos el id de la fila seleccionada
                String idEmpleador = tabla.getValueAt(filaSeleccionada, 0).toString(); // El IdEmpleador está en la primera columna
                updateEmpresa = conexion.prepareStatement("UPDATE empleador SET Estado = 'Activo' WHERE IdEmpleador = ?");
                updateEmpresa.setString(1, idEmpleador);
                updateEmpresa.executeUpdate();
            } else {
                logger.log(Level.WARNING, "No se ha seleccionado ninguna empresa.");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Este es el error en el método de actualizar: ", e);
        } finally {
            // Cerrar el PreparedStatement
            if (updateEmpresa != null) {
                try {
                    updateEmpresa.close();
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

    public void buscarEmpresa(JTable jtSolicitudEmpresa, JTextField txtBuscarEmpresa) {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DefaultTableModel modelo = (DefaultTableModel) jtSolicitudEmpresa.getModel(); // Reutiliza el modelo existente

        // Limpia el modelo antes de llenarlo con los nuevos resultados
        modelo.setRowCount(0); // Limpia las filas existentes

        try {
            conexion = ClaseConexion.getConexion();
            ps = conexion.prepareStatement(
                    "SELECT e.IdEmpleador as Id, e.NombreEmpresa as Empresa, e.NombreRepresentante as Representante, "
                    + "e.CorreoElectronico as Correo, e.NumeroTelefono as Teléfono, e.Direccion as Dirección, "
                    + "d.Nombre as Departamento "
                    + "FROM Empleador e "
                    + "INNER JOIN DEPARTAMENTO d ON e.IdDepartamento = d.IdDepartamento "
                    + "WHERE e.NombreEmpresa LIKE ? AND e.Estado = 'Pendiente'"
            );
            ps.setString(1, txtBuscarEmpresa.getText() + "%"); // Agregar el '%' aquí
            rs = ps.executeQuery();

            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getString("Id"),
                    rs.getString("Empresa"),
                    rs.getString("Representante"),
                    rs.getString("Correo"),
                    rs.getString("Teléfono"),
                    rs.getString("Dirección"),
                    rs.getString("Departamento")
                });
            }
            // Asigna el modelo actualizado a la tabla
            jtSolicitudEmpresa.setModel(modelo);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error en buscar empresa: ", e);
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
}
