/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmcontracte;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import mmcontracte.view.FormLogin;

/**
 *
 * @author Levi
 */
public class MMContracte {

    public static void main(String[] args) {
//	try {
//	    UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
//	    //UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
//            //UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
//	} catch (ClassNotFoundException ex) {
//	    Logger.getLogger(MMContracte.class.getName()).log(Level.SEVERE, null, ex);
//	} catch (InstantiationException ex) {
//	    Logger.getLogger(MMContracte.class.getName()).log(Level.SEVERE, null, ex);
//	} catch (IllegalAccessException ex) {
//	    Logger.getLogger(MMContracte.class.getName()).log(Level.SEVERE, null, ex);
//	} catch (UnsupportedLookAndFeelException ex) {
//	    Logger.getLogger(MMContracte.class.getName()).log(Level.SEVERE, null, ex);
//	}

System.out.println("Working Directory = " + System.getProperty("user.dir"));
FormLogin flogin = new FormLogin(null, false);
	flogin.setVisible(true);

    }

}
