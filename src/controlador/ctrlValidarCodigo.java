/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JOptionPane;
import modelo.UsuarioEscritorio;
import vista.frmCambiarContrasena;
import vista.frmIngresoCorreo;
import vista.frmValidarCodigo;

public class ctrlValidarCodigo implements ActionListener {

    private frmValidarCodigo vistaValidacion;

    public ctrlValidarCodigo(frmValidarCodigo vista) {
        this.vistaValidacion = vista;
        this.vistaValidacion.btnValidar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaValidacion.btnValidar) {
            try {
                int codigoIngresado = Integer.parseInt(this.vistaValidacion.txtCodigo.getText());
                if (codigoIngresado == ctrlIngresoCorreo.numeroAleatorio) {
                    frmCambiarContrasena.initFrmCambiarContrasena();
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
