package controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import vista.FrmAdministrarUsuarios;
import vista.FrmDashboard;
import vista.FrmEmpresa;
import vista.FrmMenu;
import vista.FrmSolicitantes;
import vista.FrmTrabajos;
import javax.swing.JPanel;
import modelo.UsuarioEscritorio;

/**
 *
 * Si se ve un método vacío, consultar con desarrolador encargado
 */
public class CtrlMenu implements MouseListener {

    FrmMenu vista;
    FrmDashboard frmDashboard;
    FrmEmpresa frmEmpresa;
    FrmSolicitantes frmSolicitantes;
    FrmTrabajos frmTrabajos;
    FrmAdministrarUsuarios frmAdministrarUsuarios;

    private UsuarioEscritorio modelo;

    public CtrlMenu(FrmMenu vista, FrmDashboard frmDashboard, FrmAdministrarUsuarios frmAdministrarUsuarios,
            FrmEmpresa frmEmpresa, FrmSolicitantes frmSolicitantes, FrmTrabajos frmTrabajos, UsuarioEscritorio modelo) {
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
                    frmAdministrarUsuarios = new FrmAdministrarUsuarios();
                }
                // Cambiar el panel contenido
                cambiarPanel(vista.jPContenedor, frmAdministrarUsuarios);
            } else {
                JOptionPane.showMessageDialog(vista, "No tienes permiso para acceder a este formulario.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }

        if (e.getSource() == vista.btnHome) {
            if (frmDashboard == null) {
                frmDashboard = new FrmDashboard();
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
