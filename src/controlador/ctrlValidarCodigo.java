/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import vista.FrmCambiarContrasena;
import vista.FrmValidarCodigo;

public class CtrlValidarCodigo implements ActionListener {

    private final FrmValidarCodigo vistaValidacion;

    public CtrlValidarCodigo(FrmValidarCodigo vista) {
        this.vistaValidacion = vista;
        this.vistaValidacion.btnValidar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaValidacion.btnValidar) {
            try {
                int codigoIngresado = Integer.parseInt(this.vistaValidacion.txtCodigo.getText());
                if (codigoIngresado == CtrlIngresoCorreo.numeroAleatorio) {
                    FrmCambiarContrasena.initFrmCambiarContrasena();
                    this.vistaValidacion.dispose();
                } else {
                    JOptionPane.showMessageDialog(vistaValidacion, "El código ingresado es incorrecto.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vistaValidacion, "Por favor, ingrese un código válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
