/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmcontracte;

import mmcontracte.view.FormLogin;
import mmcontracte.model.Descriptor;

/**
 *
 * @author Levi
 */
public class MMContracte {

    public static void main(String[] args) {
	org.apache.log4j.BasicConfigurator.configure();
        org.apache.log4j.Logger.getRootLogger().setLevel(org.apache.log4j.Level.INFO);
        
        // Load Config File
        new Descriptor();

	FormLogin flogin = new FormLogin(null, false);
	flogin.setVisible(true);

    }

}
