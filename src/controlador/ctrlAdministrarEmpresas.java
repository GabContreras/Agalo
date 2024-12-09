package controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import modelo.AdministrarEmpresas; // Asegúrate de tener este modelo
import vista.frmDashboard; // Asegúrate de importar la vista correcta

public class CtrlAdministrarEmpresas implements MouseListener, KeyListener {

    // 1- Mandar a llamar a las otras capas (modelo y vista)
    private AdministrarEmpresas modelo;
    private frmDashboard vista;

    // 2- Crear el constructor
    public CtrlAdministrarEmpresas(AdministrarEmpresas modelo, frmDashboard vista) {
        this.modelo = modelo;
        this.vista = vista;

        // Agregar listeners a los botones
        vista.btnAceptarSolicitud.addMouseListener(this);
        vista.btnRechazarSolicitud.addMouseListener(this);
        vista.txtBuscarEmpresa.addKeyListener(this);

        // Cargar los datos al inicializar
        modelo.MostrarEmpresas(vista.jtSolicitudEmpresa); // Asegúrate de que este método esté en tu modelo
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.btnAceptarSolicitud) {
            System.err.println("Ddaasd");
            int filaSeleccionada = vista.jtSolicitudEmpresa.getSelectedRow();
            if (filaSeleccionada != -1) {
                // Asumiendo que el ID de la empresa está en la primera columna (índice 0)
                modelo.actualizarEstadoActivo(vista.jtSolicitudEmpresa);
                modelo.MostrarEmpresas(vista.jtSolicitudEmpresa); // Actualizar la tabla después de la modificación
                JOptionPane.showMessageDialog(vista, "Empresa aceptada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (e.getSource() == vista.btnRechazarSolicitud) {
            modelo.rechazarEmpresa(vista.jtSolicitudEmpresa);
            modelo.MostrarEmpresas(vista.jtSolicitudEmpresa); // Actualizar la tabla después de la eliminación
            JOptionPane.showMessageDialog(vista, "Empresa rechazada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        /**
         *
         * Metodos vacíos están así porque se implementará en un futuro
         */
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        /**
         *
         * Metodos vacíos están así porque se implementará en un futuro
         */
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        /**
         *
         * Metodos vacíos están así porque se implementará en un futuro
         */
    }

    @Override
    public void mouseExited(MouseEvent e) {
        /**
         *
         * Metodos vacíos están así porque se implementará en un futuro
         */
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getSource() == vista.txtBuscarEmpresa) {
            modelo.buscarEmpresa(vista.jtSolicitudEmpresa, vista.txtBuscarEmpresa);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        /**
         *
         * Metodos vacíos están así porque se implementará en un futuro
         */
    }

    @Override
    public void keyReleased(KeyEvent e) {
        /**
         *
         * Metodos vacíos están así porque se implementará en un futuro
         */
    }
}
