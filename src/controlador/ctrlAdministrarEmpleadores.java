/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import modelo.AdministrarEmpleadores;
import vista.frmEmpresa;

/**
 *
 * @author Contr
 */
public class CtrlAdministrarEmpleadores implements MouseListener, KeyListener {

    private AdministrarEmpleadores modelo;
    private frmEmpresa vista;
    
    public CtrlAdministrarEmpleadores(AdministrarEmpleadores modelo, frmEmpresa vista) {
        this.modelo = modelo;
        this.vista = vista;

        // Agregar listeners a los botones
        vista.btnRestringirEmpleador.addMouseListener(this); // Botón para restringir empleador
        vista.txtBuscarEmpleador.addKeyListener(this); // Campo de búsqueda

        // Cargar los datos al inicializar
        modelo.MostrarEmpleadores(vista.jtEmpresa); // Asegúrate de que este método esté en tu modelo
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.btnRestringirEmpleador) {
            int filaSeleccionada = vista.jtEmpresa.getSelectedRow();
            if (filaSeleccionada != -1) {
                // Asumiendo que el ID del solicitante está en la primera columna (índice 0)
                modelo.restringirEmpleador(vista.jtEmpresa);
                modelo.MostrarEmpleadores(vista.jtEmpresa); // Actualizar la tabla después de la modificación
                JOptionPane.showMessageDialog(vista, "Empleador restringido exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        /**
         *
         * Metodos vacíos están así porque se descartó su uso o se implementará
         * en un futuro
         */
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        /**
         *
         * Metodos vacíos están así porque se descartó su uso o se implementará
         * en un futuro
         */
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        /**
         *
         * Metodos vacíos están así porque se descartó su uso o se implementará
         * en un futuro
         */
    }

    @Override
    public void mouseExited(MouseEvent e) {
        /**
         *
         * Metodos vacíos están así porque se descartó su uso o se implementará
         * en un futuro
         */
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getSource() == vista.txtBuscarEmpleador) {
            modelo.buscarEmpleador(vista.jtEmpresa, vista.txtBuscarEmpleador);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        /**
         *
         * Metodos vacíos están así porque se descartó su uso o se implementará
         * en un futuro
         */
    }

    @Override
    public void keyReleased(KeyEvent e) {
        /**
         *
         * Metodos vacíos están así porque se descartó su uso o se implementará
         * en un futuro
         */
    }

}
