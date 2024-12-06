/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tests;

import org.junit.Test;
import java.sql.Connection;
import static org.junit.Assert.*;
import modelo.ClaseConexion;

/**
 *
 * @author Datum-Redsoft
 */
public class ClaseConexiontest {

    @Test
    public void testGetConexion() {
        // Intentar obtener una conexión
        Connection conexion = ClaseConexion.getConexion();

        // Verificar que la conexión no sea nula
        assertNotNull("La conexión no debe ser nula", conexion);

        // Cerrar la conexión si se ha establecido
        if (conexion != null) {
            try {
                conexion.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
