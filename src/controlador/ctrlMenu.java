package controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import vista.frmAdministrarUsuarios;
import vista.frmDashboard;
import vista.frmEmpresa;
import vista.frmMenu;
import vista.frmSolicitantes;
import vista.frmTrabajos;
import javax.swing.JPanel;
import modelo.UsuarioEscritorio;

/**
 *
 * Si se ve un método vacío, consultar con desarrolador encargado
 */
public class CtrlMenu implements MouseListener {

    frmMenu vista;
    frmDashboard frmDashboard;
    frmEmpresa frmEmpresa;
    frmSolicitantes frmSolicitantes;
    frmTrabajos frmTrabajos;
    frmAdministrarUsuarios frmAdministrarUsuarios;

    private UsuarioEscritorio modelo;

    public CtrlMenu(frmMenu vista, frmDashboard frmDashboard, frmAdministrarUsuarios frmAdministrarUsuarios,
            frmEmpresa frmEmpresa, frmSolicitantes frmSolicitantes, frmTrabajos frmTrabajos, UsuarioEscritorio modelo) {
        this.vista = vista;
        this.frmDashboard = frmDashboard;
        this.frmAdministrarUsuarios = frmAdministrarUsuarios;
        this.frmEmpresa = frmEmpresa;
        this.frmSolicitantes = frmSolicitantes;
        this.frmTrabajos = frmTrabajos;
        this.modelo = modelo;

        // Agregar los listeners a los botones
        vista.btnHome.addMouseListener(this);
        vista.btnAdd.addMouseListener(this);
        vista.btnEmpresa.addMouseListener(this);
        vista.btnsolicitantes.addMouseListener(this);
        vista.btnTrabajos.addMouseListener(this);

        // Desactiva el botón "Administrador" si el rol del usuario no coincide con el rol permitido
        desactivarBotonAdministrar();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.btnAdd) {
            if (vista.btnAdd.isEnabled()) {
                // Inicialización perezosa (solo se crea la primera vez)
                if (frmAdministrarUsuarios == null) {
                    frmAdministrarUsuarios = new frmAdministrarUsuarios();
                  //  frmAdministrarUsuarios.initfrmADMIN();
                }
                // Cambiar el panel contenido
                cambiarPanel(vista.jPContenedor, frmAdministrarUsuarios);
            } else {
                JOptionPane.showMessageDialog(vista, "No tienes permiso para acceder a este formulario.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }

        if (e.getSource() == vista.btnHome) {
            if (frmDashboard == null) {
                frmDashboard = new frmDashboard();
                frmDashboard.initDashboard();
            }
            // Cambiar el panel contenido
            cambiarPanel(vista.jPContenedor, frmDashboard);

        }

        if (e.getSource() == vista.btnEmpresa) {
            cambiarPanel(vista.jPContenedor, frmEmpresa);
        }

        if (e.getSource() == vista.btnsolicitantes) {
            cambiarPanel(vista.jPContenedor, frmSolicitantes);
        }

        if (e.getSource() == vista.btnTrabajos) {
            cambiarPanel(vista.jPContenedor, frmTrabajos);
        }
    }

    // Método reutilizable para cambiar el panel
    private void cambiarPanel(JPanel contenedor, JPanel nuevoPanel) {
        contenedor.removeAll();  // Elimina todos los componentes actuales
        contenedor.add(nuevoPanel);  // Añade el nuevo panel
        contenedor.revalidate();  // Refresca el contenedor
        contenedor.repaint();  // Repinta el contenedor
    }

    // Método para desactivar el botón
    private void desactivarBotonAdministrar() {
        int idRol = modelo.getIdRol();
        if (idRol == 1) {
            vista.btnAdd.setEnabled(false);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //No se utilizará, consultar con desarrolador encargado
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //No se utilizará, consultar con desarrolador encargado
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //No se utilizará, consultar con desarrolador encargado
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //No se utilizará, consultar con desarrolador encargado
    }
}
