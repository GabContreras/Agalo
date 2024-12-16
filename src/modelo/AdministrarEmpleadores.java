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
public class AdministrarEmpleadores {

    private static final Logger logger = Logger.getLogger(AdministrarEmpleadores.class.getName());

    private static final String ERR_CONEX = "Error al cerrar la conexión: ";

    //Constantes para los select               
    private static final String ID_EMPLEADOR = "Id";
    private static final String NOMBRE_EMPRESA = "Empresa";
    private static final String NOMBRE_REPRESENTANTE = "Representante";
    private static final String CORREO_REPRESENTANTE = "Correo";
    private static final String TELEFONO_REPRESENTANTE = "Teléfono";
    private static final String DIRECCION_REPRESENTANTE = "Dirección";
    private static final String DEPARTAMENTO_REPRESENTANTE = "Departamento";

    

    private String correoEmpleador;
    private String uuidEmpleador;

    public String getCorreoEmpleador() {
        return correoEmpleador;
    }

    public void setCorreoEmpleador(String correoEmpleador) {
        this.correoEmpleador = correoEmpleador;
    }

    public String getUuidEmpleador() {
        return uuidEmpleador;
    }

    public void setUuidEmpleador(String uuidEmpleador) {
        this.uuidEmpleador = uuidEmpleador;
    }

    public void restringirEmpleador(JTable tabla) {
        Connection conexion = null;
        PreparedStatement updateEmpleador = null;

        try {
            // Creamos una variable igual a ejecutar el método de la clase de conexión
            conexion = ClaseConexion.getConexion();

            // Obtenemos qué fila seleccionó el usuario
            int filaSeleccionada = tabla.getSelectedRow();
            if (filaSeleccionada != -1) {
                // Obtenemos el id de la fila seleccionada
                String ID = tabla.getValueAt(filaSeleccionada, 0).toString(); // El IdEmpleador está en la primera columna

                // Preparamos la consulta
                String sql = "UPDATE Empleador SET Estado = 'Restringido' WHERE IdEmpleador = ?";
                updateEmpleador = conexion.prepareStatement(sql);
                updateEmpleador.setString(1, ID);
                updateEmpleador.executeUpdate();
            } else {
                logger.log(Level.WARNING, "No se ha seleccionado ningún Empleador.");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al ejecutar la actualización del empleador: ", e);
        } finally {
            // Cerrar el PreparedStatement
            if (updateEmpleador != null) {
                try {
                    updateEmpleador.close();
                } catch (SQLException e) {
                    logger.log(Level.SEVERE, "Error al cerrar el PreparedStatement: ", e);
                }
            }
            // Cerrar la conexión
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    logger.log(Level.SEVERE,ERR_CONEX, e);
                }
            }
        }
    }

    public void buscarEmpleador(JTable jtbAdmin, JTextField txtBuscarEmpleador) {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conexion = ClaseConexion.getConexion();
            DefaultTableModel modelo = (DefaultTableModel) jtbAdmin.getModel(); // Reutiliza el modelo existente

            // Limpia el modelo antes de llenarlo con los nuevos resultados
            modelo.setRowCount(0); // Limpia las filas existentes

            ps = conexion.prepareStatement(
                    "SELECT e.IdEmpleador as Id, e.NombreEmpresa as Empresa, e.NombreRepresentante as Representante, "
                    + "e.CorreoElectronico as Correo, e.NumeroTelefono as Teléfono, e.Direccion as Dirección, "
                    + "d.Nombre AS Departamento "
                    + "FROM Empleador e "
                    + "INNER JOIN DEPARTAMENTO d ON e.IdDepartamento = d.IdDepartamento "
                    + "WHERE e.Estado = 'Activo' AND e.NombreRepresentante LIKE ?"
            ); // Asegúrate de que el nombre de la columna sea correcto
            ps.setString(1, txtBuscarEmpleador.getText() + "%"); // Agregar el '%' aquí
            rs = ps.executeQuery();

            // Recorremos el ResultSet
            while (rs.next()) {
                // Llenamos el modelo por cada vez que recorremos el ResultSet
                modelo.addRow(new Object[]{
                    rs.getString(ID_EMPLEADOR), // Asegúrate de usar el nombre correcto de la columna
                    rs.getString(NOMBRE_EMPRESA),
                    rs.getString(NOMBRE_REPRESENTANTE),
                    rs.getString(CORREO_REPRESENTANTE),
                    rs.getString(TELEFONO_REPRESENTANTE),
                    rs.getString(DIRECCION_REPRESENTANTE),
                    rs.getString(DEPARTAMENTO_REPRESENTANTE)
                });
            }

            // Asignamos el nuevo modelo lleno a la tabla
            jtbAdmin.setModel(modelo);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error en buscar Empleador: ", e);
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
                    logger.log(Level.SEVERE, ERR_CONEX, e);
                }
            }
        }
    }

    public void mostrarEmpleadores(JTable jtSolicitudEmpleador) {
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
                    + "WHERE e.Estado = 'Activo'"
            );

            // Recorremos el ResultSet
            while (rs.next()) {
                // Llenamos el modelo por cada vez que recorremos el ResultSet
                modeloDeDatos.addRow(new Object[]{
                    rs.getString(ID_EMPLEADOR), // Asegúrate de usar el nombre correcto de la columna
                    rs.getString(NOMBRE_EMPRESA),
                    rs.getString(NOMBRE_REPRESENTANTE),
                    rs.getString(CORREO_REPRESENTANTE),
                    rs.getString(TELEFONO_REPRESENTANTE),
                    rs.getString(DIRECCION_REPRESENTANTE),
                    rs.getString(DEPARTAMENTO_REPRESENTANTE)
                });
            }

            // Asignamos el nuevo modelo lleno a la tabla
            jtSolicitudEmpleador.setModel(modeloDeDatos);
        } catch (SQLException e) {
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
                    logger.log(Level.SEVERE, ERR_CONEX, e);
                }
            }
        }
    }
}
