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
import modelo.AdministrarSolicitantes;
import vista.frmSolicitantes;

/**
 *
 * @author Contr
 */
public class CtrlAdministrarSolicitantes implements MouseListener, KeyListener {

    private AdministrarSolicitantes modelo;
    private frmSolicitantes vista;

    public CtrlAdministrarSolicitantes(AdministrarSolicitantes modelo, frmSolicitantes vista) {
        this.modelo = modelo;
        this.vista = vista;

        // Agregar listeners a los botones
        vista.btnRestringirSolicitante.addMouseListener(this); // Botón para restringir solicitante
        vista.txtBuscarSolicitante.addKeyListener(this); // Campo de búsqueda

        // Cargar los datos al inicializar
        modelo.MostrarSolicitantes(vista.jtSolicitantes); // Asegúrate de que este método esté en tu modelo
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.btnRestringirSolicitante) {
            System.err.println("Ddaasd");
            int filaSeleccionada = vista.jtSolicitantes.getSelectedRow();
            if (filaSeleccionada != -1) {
                // Asumiendo que el ID del solicitante está en la primera columna (índice 0)
                modelo.restringirSolicitante(vista.jtSolicitantes);
                modelo.MostrarSolicitantes(vista.jtSolicitantes); // Actualizar la tabla después de la modificación
                JOptionPane.showMessageDialog(vista, "Solicitante restringido exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        /**
         *
         * Metodos vacíos están así porque el programa lo pide
         */
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        /**
         *
         * Metodos vacíos están así porque el programa lo pide
         */
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        /**
         *
         * Metodos vacíos están así porque el programa lo pide
         */
    }

    @Override
    public void mouseExited(MouseEvent e) {
        /**
         *
         * Metodos vacíos están así porque el programa lo pide
         */
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getSource() == vista.txtBuscarSolicitante) {
            modelo.buscarSolicitante(vista.jtSolicitantes, vista.txtBuscarSolicitante);
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        /**
         *
         * Metodos vacíos están así porque el programa lo pide
         */
    }

    @Override
    public void keyReleased(KeyEvent e) {
        /**
         *
         * Metodos vacíos están así porque el programa lo pide
         */
    }
}
