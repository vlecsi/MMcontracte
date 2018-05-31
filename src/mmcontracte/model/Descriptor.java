/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmcontracte.model;

/**
 *
 * @author Levi
 */
public class Descriptor {
    
    private static Descriptor singleton = new Descriptor();
    
    private Descriptor() { }

   /* Static 'instance' method */
   public static Descriptor getInstance() {
      System.out.println("Descritor lefutott!");
       return singleton;
      
   }

   /* Other methods protected by singleton-ness */
   public static String getVersion() {
      return("12.11.1");
   }
   
   public static String getVersionText() {
      String myVersion = String.format("%s %s", "Ver: ", Descriptor.getVersion());
      return(myVersion);
   }
   
   public static String getProgramTitel(){
       String titel="MM Contracte";
       return(titel+" Ver. "+Descriptor.getVersion());
   }
   
   
   public static String getHost(){
       String host="192.168.1.250";
       return(host);
   }
   
   public static String getPath(){
       String path=System.getProperty("user.dir");
       return path;
   }
   
   
}
