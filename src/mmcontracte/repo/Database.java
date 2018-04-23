/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmcontracte.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mmcontracte.model.Contract;

/**
 *
 * @author Levi
 */
public class Database {

    private String url = "jdbc:mysql://corasoft.go.ro:3306/prog_contracte";
    private String username = "root";
    private String password = "levi";
    private Connection connection = null;

    public boolean testConnection() {
	System.out.println("Loading driver...");
	try {
	    Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException ex) {
	    System.out.println("Nu gasessc CLASA com.mysql.jdbc.Driver");
	    return (false);
	}
	System.out.println("Driver loaded!");
	System.out.println("Connecting database...");
	try {
	    connection = DriverManager.getConnection(url, username, password);
	    connection.close();
	} catch (SQLException ex) {
	    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
	    return (false);
	}
	System.out.println("Database connected!");
	return (true);
    }

    public ArrayList<Contract> queryContracte(String cauta) {
	ArrayList<Contract> contracte = null;
	
	final String SQL;
	
	System.out.println("Cauta:"+cauta);
	
        if (cauta!=null){
	    SQL="select * from clienti where pj_denumire LIKE '%"+cauta+"%' or pf_denumire LIKE '%"+cauta+"%' order by data_contract desc";
	}else{ 
	    SQL="select * from clienti order by data_contract desc";
	}
	
	try {
	    connection = DriverManager.getConnection(url, username, password);
	    Statement stmt = connection.createStatement();
	    ResultSet rs = stmt.executeQuery(SQL);
	    contracte = new ArrayList();
	    while (rs.next()) {
		Contract contract=new Contract();
		contract.setId(rs.getInt("id"));
		contract.setNrContract(rs.getInt("nr_contract"));
		contract.setDataContract(rs.getString("data_contract"));
		contract.setTip_contract(rs.getString("tip_contract"));
		
		String beneficiar=null;
		if ("PERSOANA FIZICA".equals(rs.getString("tip_contract"))){
		    beneficiar=rs.getString("pf_denumire");
                    
	        }
		if ("PERSOANA JURIDICA".equals(rs.getString("tip_contract"))){
		    beneficiar=rs.getString("pj_denumire");
	        }
                
                if (beneficiar ==null) { beneficiar="";};
		contract.setBeneficiar(beneficiar);
		contract.setValoareRon(rs.getFloat("valoare_ron"));
		contract.setValoareEur(rs.getFloat("valoare_euro"));
		contract.setAvansRon(rs.getFloat("avans_ron"));
		contract.setAvansEur(rs.getFloat("avans_euro"));

	
		
			
			
		contracte.add(contract);
		
	    }
	    rs.close();
	    stmt.close();
	    connection.close();
	} catch (SQLException ex) {
	    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
	    System.out.println("SQl HIBA!");
	}
	return (contracte);
    }

    public Contract queryContractById(String id) {
	
        Contract contract=new Contract();
	final String SQL;
        SQL="select * from clienti where id="+id+" order by data_contract desc";
	
	try {
	    connection = DriverManager.getConnection(url, username, password);
	    Statement stmt = connection.createStatement();
	    ResultSet rs = stmt.executeQuery(SQL);
	    while (rs.next()) {
		System.out.println("keres contrcat :>  ");
                contract.setId(rs.getInt("id"));
		contract.setNrContract(rs.getInt("nr_contract"));
		contract.setDataContract(rs.getString("data_contract"));
		contract.setTip_contract(rs.getString("tip_contract"));
		
		String beneficiar=null;
		if ("PERSOANA FIZICA".equals(rs.getString("tip_contract"))){
		    beneficiar=rs.getString("pf_denumire");
                    
	        }
		if ("PERSOANA JURIDICA".equals(rs.getString("tip_contract"))){
		    beneficiar=rs.getString("pj_denumire");
	        }
                
                if (beneficiar ==null) { beneficiar="";};
		contract.setBeneficiar(beneficiar);
		contract.setValoareRon(rs.getFloat("valoare_ron"));
		contract.setValoareEur(rs.getFloat("valoare_euro"));
		contract.setAvansRon(rs.getFloat("avans_ron"));
		contract.setAvansEur(rs.getFloat("avans_euro"));
	    }
	    rs.close();
	    stmt.close();
	    connection.close();
	} catch (SQLException ex) {
	    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
	    System.out.println("SQl HIBA!");
	}
        System.out.println("keres contrcat :"+contract.getBeneficiar());
	return (contract);
    }

    
    
    
    public boolean deleteId(String tablename) {

	return (true);
    }

    
    public boolean deleteMyId(String tablename) {

	return (true);
    }

}
