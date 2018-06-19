/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmcontracte.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Levi
 */
public class Descriptor {

    private static String dbUsername;
    private static String dbPassword;
    private static String dbDatabase;
    private static String dbHost;
    private static String dbPort;
    private static int enableCompile;
    private static int enableAutoPdf;

    public static boolean getEnableCompile() {
        if (enableCompile == 1) {
            return true;
        } else {
            return false;
        }

    }

    public static boolean getEnableAutoPdf() {

        if (enableAutoPdf == 1) {
            return true;
        } else {
            return false;
        }

    }

    public static String getDbPort() {
        return Descriptor.dbPort;
    }
    private final String fileName = "config.ini";

    public Descriptor() {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(fileName));
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Config file nou found !", "MMContracte - Exceptie Fatala !", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Config file error !", "MMContracte - Exceptie Fatala !", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        Descriptor.dbUsername = prop.getProperty("user");
        Descriptor.dbPassword = prop.getProperty("password");
        Descriptor.dbDatabase = prop.getProperty("database");
        Descriptor.dbHost = prop.getProperty("host");
        Descriptor.dbPort = prop.getProperty("port");
        try {
            Descriptor.enableCompile = Integer.parseInt(prop.getProperty("EnableCompile"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Config parse error !", "MMContracte - Exceptie Fatala !", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        try {
            Descriptor.enableAutoPdf = Integer.parseInt(prop.getProperty("EnableAutoPdf"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Config parse error !", "MMContracte - Exceptie Fatala !", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        System.out.println("Kesz betoltve");
    }


    /* Other methods protected by singleton-ness */
    public static String getVersion() {
        return ("12.11.1");
    }

    public static String getVersionText() {
        String myVersion = String.format("%s %s", "Ver: ", Descriptor.getVersion());
        return (myVersion);
    }

    public static String getProgramTitel() {
        String titel = "MM Contracte";
        return (titel + " Ver. " + Descriptor.getVersion());
    }

    public static String getDbHost() {
        return Descriptor.dbHost;
    }

    public static String getDbDatabase() {
        return Descriptor.dbDatabase;

    }

    public static String getDbUsername() {
        return Descriptor.dbUsername;
    }

    public static String getDbPassword() {
        return Descriptor.dbPassword;

    }

    public static String getPath() {
        String path = System.getProperty("user.dir");
        return path;
    }

}
