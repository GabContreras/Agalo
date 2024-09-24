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

/**
 *
 * @author Contr
 */
public class AdministrarEmpresas {

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

    public void MostrarEmpresas(JTable jtSolicitudEmpresa) {
        // Creamos una variable de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        // Definimos el modelo de la tabla
        DefaultTableModel modeloDeDatos = new DefaultTableModel();

        modeloDeDatos.setColumnIdentifiers(new Object[]{
            "Id", "Empresa", "Representante", "Correo Electrónico", "Teléfono", "Dirección", "Departamento"
        });

        try {
            // Creamos un Statement
            Statement statement = conexion.createStatement();
            // Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery(
                    "SELECT e.IdEmpleador as Id,e.NombreEmpresa as Empresa, e.NombreRepresentante as Representante, e.CorreoElectronico as Correo,e.NumeroTelefono as Teléfono, e.Direccion as Dirección, d.Nombre AS Departamento FROM Empleador e INNER JOIN DEPARTAMENTO d ON e.IdDepartamento = d.IdDepartamento WHERE e.Estado = 'Pendiente'"
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
            System.out.println("Este es el error en el modelo, método mostrar " + e);
        }
    }

    // Eliminar empresa
    public void rechazarEmpresa(JTable tabla) {
        Connection conexion = ClaseConexion.getConexion();
        int filaSeleccionada = tabla.getSelectedRow();

        if (filaSeleccionada != -1) {
            // Obtenemos el IdEmpleador de la fila seleccionada
            String idEmpleador = tabla.getValueAt(filaSeleccionada, 0).toString(); // Asumiendo que el IdEmpleador está en la primera columna

            try {
                PreparedStatement ps = conexion.prepareStatement("DELETE FROM empleador WHERE IdEmpleador = ?");
                ps.setString(1, idEmpleador);
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Error al rechazar a la empresa: " + e);
            }
        } else {
            System.out.println("No se ha seleccionado ninguna empresa.");
        }
    }

    public void actualizarEstadoActivo(JTable tabla) {
        // Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        // Obtenemos qué fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            // Obtenemos el id de la fila seleccionada
            String idEmpleador = tabla.getValueAt(filaSeleccionada, 0).toString(); //El IdEmpleador está en la primera columna
            try {
                // Ejecutamos la Query
                PreparedStatement updateEmpresa = conexion.prepareStatement("UPDATE empleador SET Estado = 'Activo' WHERE IdEmpleador = ?");
                updateEmpresa.setString(1, idEmpleador);
                updateEmpresa.executeUpdate();
            } catch (Exception e) {
                System.out.println("Este es el error en el método de actualizar: " + e);
            }
        } else {
            System.out.println("No se ha seleccionado ninguna empresa.");
        }
    }

    // Buscar empresa por nombre
    public void buscarEmpresa(JTable jtSolicitudEmpresa, JTextField txtBuscarEmpresa) {
        Connection conexion = ClaseConexion.getConexion();
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.setColumnIdentifiers(new Object[]{
            "Id", "Empresa", "Representante", "Correo Electrónico", "Teléfono",
            "Dirección", "Departamento"
        });

        try {
            PreparedStatement ps = conexion.prepareStatement(
                    "SELECT e.IdEmpleador as Id,e.NombreEmpresa as Empresa, e.NombreRepresentante as Representante, e.CorreoElectronico as Correo Electrónico,e.NumeroTelefono as Teléfono, e.Direccion as Dirección, d.Nombre AS Departamento FROM Empleador e INNER JOIN DEPARTAMENTO d ON e.IdDepartamento = d.IdDepartamento WHERE e.NombreEmpresa LIKE ? || '%' AND e.Estado = 'Pendiente'"
            );
            ps.setString(1, txtBuscarEmpresa.getText());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getInt("Id"), // Asumiendo que IdEmpleador es un entero
                    rs.getString("Empresa"),
                    rs.getString("Representante"),
                    rs.getString("Correo Electrónico"),
                    rs.getString("Teléfono"),
                    rs.getString("Dirección"),
                    rs.getString("Departamento")
                });
            }
            jtSolicitudEmpresa.setModel(modelo);
        } catch (Exception e) {
            System.out.println("Error en buscar empresa: " + e);
        }
    }
}
