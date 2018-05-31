/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmcontracte.model;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mmcontracte.repo.Database;
import mmcontracte.view.FormMain;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Levi
 */
public class ImprimareContract {

    public ImprimareContract(long id) {

        Database db = new Database();
        Contract ct = db.queryContractById(id);

//         String dcpattern = "#0.00#";
        //              DecimalFormat df = new DecimalFormat(dcpattern);        
        HashMap param = new HashMap();

        // TITLU
        SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
        String fDataContract = ft.format(ct.getDataContract());
        String fNrContract = String.valueOf(ct.getNrContract());
        param.put("contractDetalii", "NR. " + fNrContract + " din data de " + fDataContract);

        // PARTILE CONTRACTANTE
        String fDescriere = "Avand sediul in ONCESTI nr 460, judetul MARAMURES, inregistrat la registrul comertului sub J24/194 2008, avand cod unic de inregistrare RO 23179020, reprezentata prin domnul DRAGUS ION, avand functia de ADMINISTRATOR in calitate de FURNIZOR.";
        param.put("furnizorNume", "SC. MARIANA-MARINEL SRL.");
        param.put("furnizorDescriere", fDescriere);

        String beneficiarNume = null;
        String beneficiarDescriere = null;

        if (ct.getTip_contract().equals("PERSOANA JURIDICA")) {
            beneficiarNume = ct.getPj_denumire();
            beneficiarDescriere
                    = "Avand sediul in localitatea " + ct.getPf_localitate() + " strada " + ct.getPj_str() + ", nr. " + ct.getPf_nr() + ", bloc "
                    + ct.getPj_bloc() + ",scara " + ct.getPj_scara() + ",etaj " + ct.getPj_etaj() + ",apartament " + ct.getPj_ap()
                    + ",judetul " + ct.getPj_judet() + " ,inregistrat la registrul comertului sub nr " + ct.getPj_j()
                    + ", avand cod unic de inregistrare " + ct.getPj_cui()
                    + ", reprezentata de " + ct.getPj_reprezentant() + ", avand functia de " + ct.getPj_reprezentant_functie() + ", telefon " + ct.getPj_tel() + " in calitate de BENEFICIAR";
        } else {

            beneficiarNume = ct.getPj_denumire();
            beneficiarDescriere = "Avand sediul in localitatea " + ct.getPf_localitate() + ", strada " + ct.getPf_str() + ", nr. " + ct.getPf_nr() + ", bloc "
                    + ct.getPf_bloc() + ",scara " + ct.getPf_scara() + ",etaj " + ct.getPf_etaj() + ",apartament " + ct.getPf_ap()
                    + ",judetul " + ct.getPf_judet() + ", posesor buletinului cu seria " + ct.getPf_serie_buletin() + " si nr " + ct.getPf_nr_buletin() + ", cod numeric personal " + ct.getPf_cnp() + ", telefon " + ct.getPf_tel() + " in calitate de BENEFICIAR";

        }
        param.put("beneficiarNume", beneficiarNume);
        param.put("beneficiarDescriere", beneficiarDescriere);

        // Obiectul Contractului
        String profil = null, factura_seria = null, factura_nr = null, chitanta_serie = null, chitanta_nr = null, bon_de_casa = null, cont_bancar = null, trezorarie = null;
        String obiectulContractului
                = "1. Obiectul contractului il reprezinta furnizarea de prestari servicii cu TAMPLARIE din PVC cu profil " + profil + "\n"
                + "2. Contractul este valabil numai pe baza de facturi seria " + factura_seria + " nr " + factura_nr + ", achitata print" + "\n"
                + "   [-] Chitanta seria " + chitanta_serie + ", nr " + chitanta_nr + "\n"
                + "   [-] Bon de casa " + bon_de_casa + "\n"
                + "   [-] Cont bancar " + cont_bancar + "\n"
                + "   [-] Trezorarie" + trezorarie + "\n"
                + "3. Contractul este prevazut in 2 exemplare." + "\n"
                + "4. Obiectul contractului priveste confectionarea si montarea tamplariei conform dimensiunilor , deschiderilor si tipului de geam stabilite de comun acord cu beneficiarul la fata locului cu ocazia masuratorilor. " + "\n"
                + "5. Executantul se obliga sa realizeze lucrarea iar beneficiarul sa plateasca pretul convenit de ambele parti la termenele si conditiile din contract." + "\n"
                + "6. Lucrarea se determina in tablou de tamplarie ";

        param.put("obiectulContractului", obiectulContractului);

        // Valorea Contractului
        String valoarea_ron = null, valoarea_euro = null, avans_ron = null, avans_euro = null, rest_ron = null, rest_euro = null;
        String valoareaContractului
                = "1. Pentru lucrarile executate , beneficiarul trebuie sa achite suma de (inclus TVA) " + valoarea_ron + " RON, sau " + valoarea_euro + " EURO " + "\n"
                + "2. Beneficiarul v-a achita avans de " + avans_ron + " RON  sau " + avans_euro + " EURO " + "\n"
                + "3. La la receptia finala a lucrari acesta trebuie sa achite suma de " + rest_ron + " RON  sau " + rest_euro + " EURO.";
        param.put("valoareaContractului", valoareaContractului);

        //TERMENUl DE executie
        String termen = null;
        String termenulDeExecutie
                = "1. Termenul de executie al lucrari este de " + termen + " zile lucratoare. " + "\n"
                + "2. Termenul stabilit este de la data primiri avansului si se implineste in ultima zi lucratoare stabilita ca termen de executie al lucrari." + "\n"
                + "3. Receptia lucrari se va efectua la terminarea montajului pe baza de contractul semnat de ambele parti.";
        param.put("termenulDeExecutie", termenulDeExecutie);

        //Obligatiile Beneficiarului
        String obligatiiBeneficiar
                = "1. Prezenta beneficiarului este obligatorie la efectuarea receptiei pentru evitarea neaintelegerilor. " + "\n"
                + "2. Beneficiarul se obliga sa asigure toate conditiile tehnice privind posibilitatea executari lucrarilor de montaj, cai de acces si energie electrica " + "\n"
                + "3. Beneficiarul este obligat sa plateasca restul datorat furnizorului în timpul stabilit de ambele parti. Pana la achitarea integrala de catre beneficiar a contravalorii produselor executate, acestea raman proprietatea furnizorului." + "\n"
                + "4. Nerespectarea de catre beneficiar a termenelor de plata si depasirea acestora cu 30 de zile, da dreptul furnizorului de a proceda la demontarea si ridicarea produselor care ii apartin.";
        param.put("obligatiiBeneficiar", obligatiiBeneficiar);

        //Obligatiile Furnizorului
        String obligatiiFurnizor
                = "1. Sa execute toate lucrarile prevazute in contract." + "\n"
                + "2. Pentru depasirea termenului stabilit pentru montarea tamplariei se aplica penalitati de 0,1% pe o zi de intarziere din valoarea lucrari." + "\n"
                + "3. In cazul în care, la receptia lucrari se constata unele neconcordante datorate activitatii furnizorului, acesta este obligat sa le remedieze.";
        param.put("obligatiiFurnizor", obligatiiFurnizor);

        //Garantia
        String tamplarie = null, culoare = null, feronarie = null, sticla = null, suprafata = null, geamuri = null, usi = null, pervaze = null, port_de_garaj, montaj = null;
        String plasa_insecte = null;
        String porti_de_garaj = null;

        String garantie
                = "1. Garantia tamplariei este de 2 ani de la data executari montajului " + "\n"
                + "2. Garantia este valabila numai insotita de prezentul contract" + "\n" + "\n" + "\n"
                + "  [ ]  Tamplarie PVC în SISTEM: " + tamplarie + "\n"
                + "  [ ]  Culoare TAMPLARIE: " + culoare + "\n"
                + "  [ ]  Feronerie: " + feronarie + "\n"
                + "  [ ]  Sticla cu urmatoarele structuri: " + sticla + "\n"
                + "  [ ]  Suprafata totala: " + suprafata + "\n"
                + "  [ ]  Geamuri: " + geamuri + "\n"
                + " Usi: " + usi + "\n"
                + "  [ ]  Plase insecte: " + plasa_insecte + "\n"
                + "  [ ]  Pervaze (interior-exterior): " + pervaze + "\n"
                + "  [ ]  Porti de garaj :" + porti_de_garaj + "\n"
                + "  [ ]  Montaj:" + montaj + "\n";
        param.put("garantie", garantie);
        try {

            String source1 = System.getProperty("user.dir") + "\\report\\report1.jrxml";
            //JasperReport jc1 = (JasperReport) JRLoader.loadObject(new File(System.getProperty("user.dir") + "\\report\\report1.jasper"));

            JasperReport jc1 = JasperCompileManager.compileReport(source1); //give your report.jrxml file path
            JasperPrint print1 = JasperFillManager.fillReport(jc1, param, new JREmptyDataSource());

            String source2 = System.getProperty("user.dir") + "\\report\\report2.jrxml";
            JasperReport jc2 = JasperCompileManager.compileReport(source2); //give your report.jrxml file path

            //JasperReport jc2 = (JasperReport) JRLoader.loadObject(new File(System.getProperty("user.dir") + "\\report\\report1.jasper"));
            JasperPrint print2 = JasperFillManager.fillReport(jc2, param, new JREmptyDataSource());

            //    InputStream jasperStream = getClass().getResourceAsStream("d:\\report1.jasper");
            //    //String source2 = System.getProperty("user.dir") + "\\report\\report1.jasper";
            //    JasperReport jc2 = (JasperReport) JRLoader.loadObject(jasperStream);
            //    JasperPrint print2 = JasperFillManager.fillReport(jc2, param, new JREmptyDataSource());
            JasperPrint printMe = multipageLinking(print1, print2);
            JasperViewer.viewReport(printMe, false);

        } catch (JRException ex) {
            Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private JasperPrint multipageLinking(JasperPrint page1, JasperPrint page2) {
        List pages = page2.getPages();
        for (int count = 0; count
                < pages.size(); count++) {
            page1.addPage((JRPrintPage) pages.get(count));
        }

        return page1;
    }

}
