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
public class Contract {

    // Lucururi comune 
    private int Id;
    private int NrContract;
    private String DataContract;
    private String Beneficiar;
    private float ValoareRon;
    private float ValoareEur;
    private float AvansRon;
    private float AvansEur;
    private float RestRon;
    private float RestEur;

    
    //tip contrcat
    private String tip_contract;
    
    //persoana Juridica
    private String pj_denumire;
    private String pj_localitate;
    private String pj_str;
    private String pj_nr;
    private String pj_bloc;
    private String pj_scara;
    private String pj_etaj;
    private String pj_ap;
    private String pj_judet;
    private String pj_j;
    private String pj_cui;
    private String pj_tel;
    
    //persoana Fizica
    private String pf_denumire;
    private String pf_localitate;
    private String pf_str;
    private String pf_nr;
    private String pf_bloc;
    private String pf_scara;
    private String pf_etaj;
    private String pf_ap;
    private String pf_judet;
    private String pf_serie_buletin;
    private String pf_nr_buletin;
    private String pf_cnp;
    private String pf_tel;
    private String profil;
    
    // Alte lucruri
    private String factura_seria;
    private String factura_nr;
    private String factura_emis;
    private String termen_de_executie;
    private String pj_reprezentant;
    private String pj_reprezentant_functie;
    private String chitanta_serie;
    private String chitanta_nr;
    private String bon_de_casa;
    private String banca;
    private String trezorarie;
    private String tamplarie;
    private String culoare;
    private String feronarie;
    private String suprafata;
    private String sticla;
    private String geamuri;
    private String usi;
    private String plasa_insecte;
    private String pervaze;
    private String porti_de_garaj;
    private String montaj;
    
    public float getRestRon() {
	return ValoareRon-AvansRon;
	//return 200;
    }

    public float getRestEur() {
	return ValoareEur-AvansEur;
        //return 100;
    }
    
    
    public String getTip_contract() {
	return tip_contract;
    }

    public void setTip_contract(String tip_contract) {
	this.tip_contract = tip_contract;
    }

    public String getPj_denumire() {
	return pj_denumire;
    }

    public void setPj_denumire(String pj_denumire) {
	this.pj_denumire = pj_denumire;
    }

    public String getPj_localitate() {
	return pj_localitate;
    }

    public void setPj_localitate(String pj_localitate) {
	this.pj_localitate = pj_localitate;
    }

    public String getPj_str() {
	return pj_str;
    }

    public void setPj_str(String pj_str) {
	this.pj_str = pj_str;
    }

    public String getPj_nr() {
	return pj_nr;
    }

    public void setPj_nr(String pj_nr) {
	this.pj_nr = pj_nr;
    }

    public String getPj_bloc() {
	return pj_bloc;
    }

    public void setPj_bloc(String pj_bloc) {
	this.pj_bloc = pj_bloc;
    }

    public String getPj_scara() {
	return pj_scara;
    }

    public void setPj_scara(String pj_scara) {
	this.pj_scara = pj_scara;
    }

    public String getPj_etaj() {
	return pj_etaj;
    }

    public void setPj_etaj(String pj_etaj) {
	this.pj_etaj = pj_etaj;
    }

    public String getPj_ap() {
	return pj_ap;
    }

    public void setPj_ap(String pj_ap) {
	this.pj_ap = pj_ap;
    }

    public String getPj_judet() {
	return pj_judet;
    }

    public void setPj_judet(String pj_judet) {
	this.pj_judet = pj_judet;
    }

    public String getPj_j() {
	return pj_j;
    }

    public void setPj_j(String pj_j) {
	this.pj_j = pj_j;
    }

    public String getPj_cui() {
	return pj_cui;
    }

    public void setPj_cui(String pj_cui) {
	this.pj_cui = pj_cui;
    }

    public String getPj_tel() {
	return pj_tel;
    }

    public void setPj_tel(String pj_tel) {
	this.pj_tel = pj_tel;
    }

    public String getPf_denumire() {
	return pf_denumire;
    }

    public void setPf_denumire(String pf_denumire) {
	this.pf_denumire = pf_denumire;
    }

    public String getPf_localitate() {
	return pf_localitate;
    }

    public void setPf_localitate(String pf_localitate) {
	this.pf_localitate = pf_localitate;
    }

    public String getPf_str() {
	return pf_str;
    }

    public void setPf_str(String pf_str) {
	this.pf_str = pf_str;
    }

    public String getPf_nr() {
	return pf_nr;
    }

    public void setPf_nr(String pf_nr) {
	this.pf_nr = pf_nr;
    }

    public String getPf_bloc() {
	return pf_bloc;
    }

    public void setPf_bloc(String pf_bloc) {
	this.pf_bloc = pf_bloc;
    }

    public String getPf_scara() {
	return pf_scara;
    }

    public void setPf_scara(String pf_scara) {
	this.pf_scara = pf_scara;
    }

    public String getPf_etaj() {
	return pf_etaj;
    }

    public void setPf_etaj(String pf_etaj) {
	this.pf_etaj = pf_etaj;
    }

    public String getPf_ap() {
	return pf_ap;
    }

    public void setPf_ap(String pf_ap) {
	this.pf_ap = pf_ap;
    }

    public String getPf_judet() {
	return pf_judet;
    }

    public void setPf_judet(String pf_judet) {
	this.pf_judet = pf_judet;
    }

    public String getPf_serie_buletin() {
	return pf_serie_buletin;
    }

    public void setPf_serie_buletin(String pf_serie_buletin) {
	this.pf_serie_buletin = pf_serie_buletin;
    }

    public String getPf_nr_buletin() {
	return pf_nr_buletin;
    }

    public void setPf_nr_buletin(String pf_nr_buletin) {
	this.pf_nr_buletin = pf_nr_buletin;
    }

    public String getPf_cnp() {
	return pf_cnp;
    }

    public void setPf_cnp(String pf_cnp) {
	this.pf_cnp = pf_cnp;
    }

    public String getPf_tel() {
	return pf_tel;
    }

    public void setPf_tel(String pf_tel) {
	this.pf_tel = pf_tel;
    }

    public String getProfil() {
	return profil;
    }

    public void setProfil(String profil) {
	this.profil = profil;
    }

    public String getFactura_seria() {
	return factura_seria;
    }

    public void setFactura_seria(String factura_seria) {
	this.factura_seria = factura_seria;
    }

    public String getFactura_nr() {
	return factura_nr;
    }

    public void setFactura_nr(String factura_nr) {
	this.factura_nr = factura_nr;
    }

    public String getFactura_emis() {
	return factura_emis;
    }

    public void setFactura_emis(String factura_emis) {
	this.factura_emis = factura_emis;
    }

    public String getTermen_de_executie() {
	return termen_de_executie;
    }

    public void setTermen_de_executie(String termen_de_executie) {
	this.termen_de_executie = termen_de_executie;
    }

    public String getPj_reprezentant() {
	return pj_reprezentant;
    }

    public void setPj_reprezentant(String pj_reprezentant) {
	this.pj_reprezentant = pj_reprezentant;
    }

    public String getPj_reprezentant_functie() {
	return pj_reprezentant_functie;
    }

    public void setPj_reprezentant_functie(String pj_reprezentant_functie) {
	this.pj_reprezentant_functie = pj_reprezentant_functie;
    }

    public String getChitanta_serie() {
	return chitanta_serie;
    }

    public void setChitanta_serie(String chitanta_serie) {
	this.chitanta_serie = chitanta_serie;
    }

    public String getChitanta_nr() {
	return chitanta_nr;
    }

    public void setChitanta_nr(String chitanta_nr) {
	this.chitanta_nr = chitanta_nr;
    }

    public String getBon_de_casa() {
	return bon_de_casa;
    }

    public void setBon_de_casa(String bon_de_casa) {
	this.bon_de_casa = bon_de_casa;
    }

    public String getBanca() {
	return banca;
    }

    public void setBanca(String banca) {
	this.banca = banca;
    }

    public String getTrezorarie() {
	return trezorarie;
    }

    public void setTrezorarie(String trezorarie) {
	this.trezorarie = trezorarie;
    }

    public String getTamplarie() {
	return tamplarie;
    }

    public void setTamplarie(String tamplarie) {
	this.tamplarie = tamplarie;
    }

    public String getCuloare() {
	return culoare;
    }

    public void setCuloare(String culoare) {
	this.culoare = culoare;
    }

    public String getFeronarie() {
	return feronarie;
    }

    public void setFeronarie(String feronarie) {
	this.feronarie = feronarie;
    }

    public String getSuprafata() {
	return suprafata;
    }

    public void setSuprafata(String suprafata) {
	this.suprafata = suprafata;
    }

    public String getSticla() {
	return sticla;
    }

    public void setSticla(String sticla) {
	this.sticla = sticla;
    }

    public String getGeamuri() {
	return geamuri;
    }

    public void setGeamuri(String geamuri) {
	this.geamuri = geamuri;
    }

    public String getUsi() {
	return usi;
    }

    public void setUsi(String usi) {
	this.usi = usi;
    }

    public String getPlasa_insecte() {
	return plasa_insecte;
    }

    public void setPlasa_insecte(String plasa_insecte) {
	this.plasa_insecte = plasa_insecte;
    }

    public String getPervaze() {
	return pervaze;
    }

    public void setPervaze(String pervaze) {
	this.pervaze = pervaze;
    }

    public String getPorti_de_garaj() {
	return porti_de_garaj;
    }

    public void setPorti_de_garaj(String porti_de_garaj) {
	this.porti_de_garaj = porti_de_garaj;
    }

    public String getMontaj() {
	return montaj;
    }

    public void setMontaj(String montaj) {
	this.montaj = montaj;
    }
    
    

    public int getId() {
	return Id;
    }

    public void setId(int Id) {
	this.Id = Id;
    }

    public int getNrContract() {
	return NrContract;
    }

    public void setNrContract(int NrContract) {
	this.NrContract = NrContract;
    }

    public String getDataContract() {
	return DataContract;
    }

    public void setDataContract(String DataContract) {
	this.DataContract = DataContract;
    }

    public String getBeneficiar() {
	return Beneficiar;
    }

    public void setBeneficiar(String Beneficiar) {
	this.Beneficiar = Beneficiar;
    }

    public float getValoareRon() {
	return ValoareRon;
    }

    public void setValoareRon(float ValoareRon) {
	this.ValoareRon = ValoareRon;
    }

    public float getValoareEur() {
	return ValoareEur;
    }

    public void setValoareEur(float ValoareEur) {
	this.ValoareEur = ValoareEur;
    }

    public float getAvansRon() {
	return AvansRon;
    }

    public void setAvansRon(float AvansRon) {
	this.AvansRon = AvansRon;
    }

    public float getAvansEur() {
	return AvansEur;
    }

    public void setAvansEur(float AvansEur) {
	this.AvansEur = AvansEur;
    }

    //getRestRon();
    //getRestEur();
    public Contract() {

    }

//    public Contract(int Id, int NrContract, String DataContract, String Beneficiar, float ValoareRon, float ValoareEur, float AvansRon, float AvansEur) {
//	this.Id = Id;
//	this.NrContract = NrContract;
//	this.DataContract = DataContract;
//	this.Beneficiar = Beneficiar;
//	this.ValoareRon = ValoareRon;
//	this.ValoareEur = ValoareEur;
//	this.AvansRon = AvansRon;
//	this.AvansEur = AvansEur;
//    }

}
