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
import java.sql.PreparedStatement;
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

        System.out.println("Cauta:" + cauta);

        if (cauta != null) {
            SQL = "select * from clienti where pj_denumire LIKE '%" + cauta + "%' or pf_denumire LIKE '%" + cauta + "%' order by data_contract desc";
        } else {
            SQL = "select * from clienti order by data_contract desc";
        }

        try {
            connection = DriverManager.getConnection(url, username, password);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            contracte = new ArrayList();
            while (rs.next()) {
                Contract contract = new Contract();
                contract.setId(rs.getInt("id"));
                contract.setNrContract(rs.getInt("nr_contract"));
                contract.setDataContract(rs.getDate("data_contract"));
                contract.setTip_contract(rs.getString("tip_contract"));

                String beneficiar = null;
                if ("PERSOANA FIZICA".equals(rs.getString("tip_contract"))) {
                    beneficiar = rs.getString("pf_denumire");
                }
                if ("PERSOANA JURIDICA".equals(rs.getString("tip_contract"))) {
                    beneficiar = rs.getString("pj_denumire");
                }
                if (beneficiar == null) {
                    beneficiar = "";
                };
                contract.setBeneficiar(beneficiar);

                //setari valoare plata
                contract.setValoareRon(rs.getFloat("valoare_ron"));
                contract.setValoareEur(rs.getFloat("valoare_euro"));

                //setari avans plata
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

        Contract contract = new Contract();
        final String SQL;
        SQL = "select * from clienti where id=" + id + " order by data_contract desc";

        try {
            connection = DriverManager.getConnection(url, username, password);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                System.out.println("keres contrcat :>  ");
                contract.setId(rs.getInt("id"));
                contract.setNrContract(rs.getInt("nr_contract"));
                contract.setDataContract(rs.getDate("data_contract"));
                contract.setTip_contract(rs.getString("tip_contract"));

                String beneficiar = null;
                if ("PERSOANA FIZICA".equals(rs.getString("tip_contract"))) {
                    beneficiar = rs.getString("pf_denumire");

                }
                if ("PERSOANA JURIDICA".equals(rs.getString("tip_contract"))) {
                    beneficiar = rs.getString("pj_denumire");
                }

                if (beneficiar == null) {
                    beneficiar = "";
                }
                contract.setBeneficiar(beneficiar);

                //setari persoana juridica
                //-------------------------------------
                contract.setPj_denumire(rs.getString("pj_denumire"));
                contract.setPj_j(rs.getString("pj_j"));
                contract.setPj_cui(rs.getString("pj_cui"));

                contract.setPj_localitate(rs.getString("pj_localitate"));
                contract.setPj_judet(rs.getString("pj_judet"));
                contract.setPj_str(rs.getString("pj_str"));
                contract.setPj_nr(rs.getString("pj_nr"));
                contract.setPj_bloc(rs.getString("pj_bloc"));
                contract.setPj_scara(rs.getString("pj_scara"));
                contract.setPj_etaj(rs.getString("pj_etaj"));

                contract.setPj_ap(rs.getString("pj_ap"));

                contract.setPj_reprezentant(rs.getString("pj_reprezentant"));
                contract.setPj_reprezentant_functie(rs.getString("pj_reprezentant_functie"));
                contract.setPj_tel(rs.getString("pj_tel"));

                //setari persoana fizica
                //-------------------------------------
                contract.setPf_denumire(rs.getString("pf_denumire"));
                contract.setPf_localitate(rs.getString("pf_localitate"));
                contract.setPf_judet(rs.getString("pf_judet"));
                contract.setPf_str(rs.getString("pf_str"));
                contract.setPf_nr(rs.getString("pf_nr"));
                contract.setPf_bloc(rs.getString("pf_bloc"));
                contract.setPf_scara(rs.getString("pf_scara"));
                contract.setPf_etaj(rs.getString("pf_etaj"));
                contract.setPf_ap(rs.getString("pf_ap"));

                contract.setPf_serie_buletin(rs.getString("pf_serie_buletin"));
                contract.setPf_nr_buletin(rs.getString("pf_nr_buletin"));
                contract.setPf_cnp(rs.getString("pf_cnp"));
                contract.setPf_tel(rs.getString("pf_tel"));

                //setari Obiect contract
                contract.setProfil(rs.getString("profil"));

                //setari factura
                contract.setFactura_seria(rs.getString("factura_seria"));
                contract.setFactura_nr(rs.getString("factura_nr"));
                contract.setFactura_emis(rs.getString("factura_emis"));

                //setari achitat prin
                contract.setChitanta_serie(rs.getString("chitanta_serie"));
                contract.setChitanta_nr(rs.getString("chitanta_nr"));
                contract.setBon_de_casa(rs.getString("bon_de_casa"));
                contract.setBanca(rs.getString("banca"));
                contract.setTrezorarie(rs.getString("trezorarie"));

                //setari termen de executie
                contract.setTermen_de_executie(rs.getString("termen_de_executie"));

                //setari valoare plata
                contract.setValoareRon(rs.getFloat("valoare_ron"));
                contract.setValoareEur(rs.getFloat("valoare_euro"));

                //setari avans plata
                contract.setAvansRon(rs.getFloat("avans_ron"));
                contract.setAvansEur(rs.getFloat("avans_euro"));

                // setari rest de plata  Autocalulated !!!
                //contract.setRestRon(rs.getFloat("rest_ron"));
                //contract.setRestEur(rs.getFloat("rest_euro"));
                //setari garantie
                contract.setTamplarie(rs.getString("tamplarie"));
                contract.setCuloare(rs.getString("culoare"));
                contract.setFeronarie(rs.getString("feronarie"));
                contract.setSuprafata(rs.getString("suprafata"));
                contract.setSticla(rs.getString("sticla"));
                contract.setGeamuri(rs.getString("geamuri"));
                contract.setUsi(rs.getString("usi"));
                contract.setPlasa_insecte(rs.getString("plasa_insecte"));
                contract.setPervaze(rs.getString("pervaze"));
                contract.setPorti_de_garaj(rs.getString("porti_de_garaj"));
                contract.setMontaj(rs.getString("montaj"));

            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQl HIBA!");
        }
        System.out.println("keres contrcat :" + contract.getBeneficiar());
        return (contract);
    }

    public boolean deleteContractById(long id) {
        try {
            connection = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = connection.prepareStatement("DELETE from clienti WHERE id = ?");
            ps.setLong(1, id);
            ps.execute();
            ps.close();
          
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQL HIBA!");
        }
        
        

        return (true);
    }

    public boolean updateContractById(long id, Contract contract) {

        //Contract contract = new Contract();
      //  final String SQL;
      //  SQL = "update clienti where id=1";

        try {
            
            
            connection = DriverManager.getConnection(url, username, password);

            //UPDATE  persoana juridica
            PreparedStatement ps = connection.prepareStatement("UPDATE clienti SET pj_denumire=?,pj_j=?, pj_cui=?,pj_localitate=?,pj_judet=?,pj_str=?,pj_nr=?,pj_bloc=?,pj_scara=?,pj_etaj=?,pj_ap=?,pj_reprezentant=?,pj_reprezentant_functie=?,pj_tel=? WHERE id = ?");
            ps.setString(1, contract.getPj_denumire());
            ps.setString(2,contract.getPj_j());
            ps.setString(3,contract.getPj_cui());
            ps.setString(4,contract.getPj_localitate());
            ps.setString(5,contract.getPj_judet());
            ps.setString(6,contract.getPj_str());
            ps.setString(7,contract.getPj_nr());
            ps.setString(8,contract.getPj_bloc());
            ps.setString(9,contract.getPj_scara());
            ps.setString(10,contract.getPj_etaj());
            ps.setString(11,contract.getPj_ap());
            ps.setString(12,contract.getPj_reprezentant());
            ps.setString(13,contract.getPj_reprezentant_functie());
            ps.setString(14,contract.getPj_tel());
            ps.setLong(15, id);
            ps.executeUpdate();
            ps.close();

            
            //UPDATE  persoana fizica
            PreparedStatement ps1 = connection.prepareStatement("UPDATE clienti SET pf_denumire=?,pf_localitate=?,pf_judet=?,pf_str=?,pf_nr=?,pf_bloc=?,pf_scara=?,pf_etaj=?,pf_ap=?,pf_serie_buletin=?,pf_nr_buletin=?,pf_cnp=?,pf_tel=? WHERE id = ?");
            ps1.setString(1,contract.getPf_denumire());
            ps1.setString(2,contract.getPf_localitate());
            ps1.setString(3,contract.getPf_judet());
            ps1.setString(4,contract.getPf_str());
            ps1.setString(5,contract.getPf_nr());
            ps1.setString(6,contract.getPf_bloc());
            ps1.setString(7,contract.getPf_scara());
            ps1.setString(8,contract.getPf_etaj());
            ps1.setString(9,contract.getPf_ap());
            ps1.setString(10,contract.getPf_serie_buletin());
            ps1.setString(11,contract.getPf_nr_buletin());
            ps1.setString(12,contract.getPf_cnp());
            ps1.setString(13,contract.getPf_tel());
            ps1.setLong(14, id);
            ps1.executeUpdate();
            ps1.close();
            
            //UPDATE REST
            PreparedStatement ps2 = connection.prepareStatement("UPDATE clienti SET profil=?,factura_seria=?,factura_nr=?,factura_emis=?,chitanta_serie=?,chitanta_nr=?,bon_de_casa=?,banca=?,trezorarie=?,termen_de_executie=?,valoare_ron=?,valoare_euro=?,avans_ron=?,avans_euro=?,tamplarie=?,culoare=?,feronarie=?,suprafata=?,sticla=?,geamuri=?,usi=?,plasa_insecte=?,pervaze=?,porti_de_garaj=?,montaj=? WHERE id = ?");
            ps2.setString(1,contract.getProfil());
            ps2.setString(2,contract.getFactura_seria());
            ps2.setString(3,contract.getFactura_nr());
            ps2.setString(4,contract.getFactura_emis());
            ps2.setString(5,contract.getChitanta_serie());
            ps2.setString(6,contract.getChitanta_nr());
            ps2.setString(7,contract.getBon_de_casa());
            ps2.setString(8,contract.getBanca());
            ps2.setString(9,contract.getTrezorarie());
            ps2.setString(10,contract.getTermen_de_executie());
            ps2.setFloat(11,contract.getValoareRon());
            ps2.setFloat(12,contract.getValoareEur());
            ps2.setFloat(13,contract.getAvansRon());
            ps2.setFloat(14,contract.getAvansEur());
            ps2.setString(15,contract.getTamplarie());
            ps2.setString(16,contract.getCuloare());
            ps2.setString(17,contract.getFeronarie());
            ps2.setString(18,contract.getSuprafata());
            ps2.setString(19,contract.getSticla());
            ps2.setString(20,contract.getGeamuri());
            ps2.setString(21,contract.getUsi());
            ps2.setString(22,contract.getPlasa_insecte());
            ps2.setString(23,contract.getPervaze());
            ps2.setString(24,contract.getPorti_de_garaj());
            ps2.setString(25,contract.getMontaj());
            ps2.setLong(26, id);
            ps2.executeUpdate();
            ps2.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return(false);
        }

        return (true);
    }

    public long insertContract(Contract contract) {

        
        try {
            
            connection = DriverManager.getConnection(url, username, password);

            //UPDATE  persoana juridica
            PreparedStatement ps = connection.prepareStatement("INSERT INTO clienti (pj_denumire,pj_j,pj_cui,pj_localitate,pj_judet,pj_str,pj_nr,pj_bloc,pj_scara,pj_etaj,pj_ap,pj_reprezentant,pj_reprezentant_functie,pj_tel) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1,contract.getPj_denumire());
            ps.setString(2,contract.getPj_j());
            ps.setString(3,contract.getPj_cui());
            ps.setString(4,contract.getPj_localitate());
            ps.setString(5,contract.getPj_judet());
            ps.setString(6,contract.getPj_str());
            ps.setString(7,contract.getPj_nr());
            ps.setString(8,contract.getPj_bloc());
            ps.setString(9,contract.getPj_scara());
            ps.setString(10,contract.getPj_etaj());
            ps.setString(11,contract.getPj_ap());
            ps.setString(12,contract.getPj_reprezentant());
            ps.setString(13,contract.getPj_reprezentant_functie());
            ps.setString(14,contract.getPj_tel());
            ps.execute();
            ps.close();
        
        
        }catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return(1);
        }
        
        return (10);
    }

}
