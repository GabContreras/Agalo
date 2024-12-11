/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package custom;

import controlador.CtrlLogin;
import raven.drawer.component.SimpleDrawerBuilder;
import raven.drawer.component.footer.SimpleFooterData;
import raven.drawer.component.header.SimpleHeaderData;
import raven.drawer.component.menu.SimpleMenuOption;
import raven.swing.AvatarIcon;

/**
 *
 * @author informatica1
 */
public class MyDrawerBuilder extends SimpleDrawerBuilder{

    @Override
    public SimpleHeaderData getSimpleHeaderData() {
         String correo = CtrlLogin.correoUsuario;
        return new SimpleHeaderData()
       .setIcon(new AvatarIcon(getClass().getResource("/raven/image/profile.png"), 60, 60, 999))
                .setTitle("AGALO")
                .setDescription("Hola, " + correo);
    }

    @Override
    public SimpleMenuOption getSimpleMenuOption() {
       
        String[][] menus = {
        {"~Menu~"},
        {"Estado"},
        {"Administrar"},
        {"~Herramientas~"},
        {"Empresas"},
        {"Solicitantes"},
        {"Transporte"},
        {"Trabajos"},
        {"Configuracion"},
 
        };
        
        
        String[] icons = {
        "administrar.svg",
        "empresas.svg",
        "manguera.svg",
        "calendar.svg",
        "fire-truck.svg",
        "seguimiento.svg",
        "staticircle.svg",
        "emergencia.svg",
        "mision.svg",
        "informe.svg",
        "logout_1.svg",
        "inicio.svg"
    };
       
        return new SimpleMenuOption()
                .setMenus (menus)
                .setIcons(icons)
                .setBaseIconPath("icons")
                        .setIconScale(0.45f);
    
                        
    }

    @Override
    public SimpleFooterData getSimpleFooterData() {
       return new SimpleFooterData();
    }
    
}
