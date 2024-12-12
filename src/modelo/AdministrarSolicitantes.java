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
        // Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        // Obtenemos qué fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            // Obtenemos el id de la fila seleccionada
            String idSolicitante = tabla.getValueAt(filaSeleccionada, 0).toString(); // El IdSolicitante está en la primera columna
            try {
                // Ejecutamos la Query
                PreparedStatement updateSolicitante = conexion.prepareStatement("UPDATE solicitante SET EstadoCuenta = 'Restringido' WHERE IdSolicitante = ?");
                updateSolicitante.setString(1, idSolicitante);
                updateSolicitante.executeUpdate();
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Este es el error en el método de restringir: ", e);
            }
        } else {
            logger.log(Level.WARNING, "No se ha seleccionado ningún solicitante.");
        }
    }

    public void buscarSolicitante(JTable jtbAdmin, JTextField txtBuscarSolicitante) {
        Connection conexion = ClaseConexion.getConexion();
        DefaultTableModel modelo = (DefaultTableModel) jtbAdmin.getModel(); // Reutiliza el modelo existente

        // Limpia el modelo antes de llenarlo con los nuevos resultados
        modelo.setRowCount(0); // Limpia las filas existentes

        try {
            PreparedStatement ps = conexion.prepareStatement(
                    "SELECT s.IdSolicitante as Id, s.Nombre as NombreSolicitante, s.CorreoElectronico as Correo, "
                    + "s.Telefono as Teléfono, s.Direccion as Dirección, d.Nombre AS NombreDepartamento "
                    + "FROM Solicitante s "
                    + "INNER JOIN DEPARTAMENTO d ON s.IdDepartamento = d.IdDepartamento "
                    + "WHERE s.EstadoCuenta = 'Activo' AND s.Nombre LIKE ? " // Especifica la tabla para el nombre
            );
            ps.setString(1, txtBuscarSolicitante.getText() + "%"); // Agregar el '%' aquí
            ResultSet rs = ps.executeQuery();

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
        }
    }

    public void MostrarSolicitantes(JTable jtSolicitudSolicitante) {
        // Creamos una variable de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        // Definimos el modelo de la tabla
        DefaultTableModel modeloDeDatos = new DefaultTableModel();

        modeloDeDatos.setColumnIdentifiers(new Object[]{
            ID_SOLICITANTE, NOMBRE_SOLICITANTE, CORREO_SOLICITANTE, TELEFONO_SOLICITANTE, DIRECCION_SOLICITANTE, DEPARTAMENTO_SOLICITANTE
        });

        try {
            // Creamos un Statement
            Statement statement = conexion.createStatement();
            // Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery(
                    "SELECT s.IdSolicitante as Id, s.Nombre as NombreSolicitante, s.CorreoElectronico as Correo, "
                    + "s.Telefono as Teléfono, s.Direccion as Dirección, d.Nombre AS NombreDepartamento "
                    + "FROM Solicitante s "
                    + "INNER JOIN DEPARTAMENTO d ON s.IdDepartamento = d.IdDepartamento "
                    + "WHERE s.EstadoCuenta = 'Activo'" // Agregamos la condición aquí
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
        }
    }
}
