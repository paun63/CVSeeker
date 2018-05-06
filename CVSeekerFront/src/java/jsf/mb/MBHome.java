/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.mb;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.LinkedList;

import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import static jsf.mb.MBPrijava.k1;
import ki.domen.Korisnik;
import ki.domen.Profil;
import ki.domen.Segment;
import ki.domen.Sifarnik;
import ki.domen.Stavka;
import ki.kontroler.KontrolerKI;
/*
import ki.jasper.Jasper;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
*/

/**
 *
 * @author Paun
 */
@Named(value = "mbHome")
@SessionScoped
public class MBHome implements Serializable{
    
    private Profil profil;
    /*private String naziv;
    private String datum;   
    private String opisProfila;*/
    private List<Segment> segmentList;
    
    private Segment seg;
    /*private String heading;
    private String opis;*/
    private List<Stavka> stavkaList;
    /*
    private List<Stavka> stavkaListRI;
    private List<Stavka> stavkaListE;
    private List<Stavka> stavkaListDA;
    */
    
    private Stavka stavka; 
    private Sifarnik sifarnik;
    /*private String heading1;
    private String heading2;
    private String datumOd;
    private String datumDo;
    private String opisStavke;*/

    private List<Profil> listaProfila;
    private List<Korisnik> listaKorisnika;
    private List<Sifarnik> listaSifarnika;
    private Profil p;
    
    boolean admin;
    boolean prikazTabele;
    boolean prikazFormeZaUnos;
    boolean prikazTabeleSve;
    boolean prikazTabeleSvihKorisnika;
    boolean prikazTabeleSifarnika;
    
    int br = 0;
    boolean uspesnoSacuvanProfil;
    
    public MBHome() {
      if (!(k1.getRolaId().getId() == 1)) 
      {
        this.prikazTabele = true;  
        this.prikazFormeZaUnos = false;
        this.prikazTabeleSve = false;
        this.prikazTabeleSvihKorisnika = false;
        this.prikazTabeleSifarnika = false;
        this.admin = false;
      }
      else
      {
        vratiSveKorisnike();
        this.prikazTabele = false;  
        this.prikazFormeZaUnos = false;
        this.prikazTabeleSve = false;
        this.prikazTabeleSvihKorisnika = true;
        this.prikazTabeleSifarnika = false;
        this.admin = true;
      }
              
      if (profil == null)
      {
          profil = new Profil();
          
          if (profil.getSegmentList() == null)
          {
              List<Segment> ls = new LinkedList<>();
              profil.setSegmentList(ls);
              
              for (Segment s : ls) {
                  if(s.getStavkaList() == null)
                  {
                      List<Stavka> lstav = new LinkedList<>();
                      s.setStavkaList(lstav);
                  }
              }
          }
      }
      //vratiSveProfile();  
      vratiSveSifarnike();
      vratiSveProfileZaKorisnika();
    }
    
    public void addStavka()
    {
       Stavka stav = new Stavka();
       if (seg.getStavkaList() == null)
       {
           List<Stavka> lstav = new LinkedList<>();
           seg.setStavkaList(lstav);
       }
       seg.getStavkaList().add(stav);
      
    }
    
    public void removeStavka(Stavka s)
    {
        seg.getStavkaList().remove(s);
    }
    
    public void segmentAdd()
    {
        Segment s = new Segment();
        profil.getSegmentList().add(s);
    }
    
    public void segmentRemove(Segment s)
    {
        profil.getSegmentList().remove(s);
    }
    
    public void sifarnikAdd()
    {
        Sifarnik s = new Sifarnik();
        listaSifarnika.add(s);
    }
    
    public void sifarnikRemove(Sifarnik s)
    {
        KontrolerKI.getInstance().obrisiSifarnik(s);
        listaSifarnika.remove(s);
    }
    
    public void sacuvajSifarnik()
    {
        for (Sifarnik s : listaSifarnika) {
        KontrolerKI.getInstance().sacuvajSifarnik(s);        
        }
        vratiSveSifarnike();
        
    }
    
    public void aktivirajKorisnika(Korisnik k)
    {
        k.setLock(false);
        KontrolerKI.getInstance().registracija(k);
    }
    
    public void blokirajKorisnika(Korisnik k)
    {
        k.setLock(true);
        KontrolerKI.getInstance().registracija(k); 
    }
    
    public void potvrdaStavki()
    {
        int pom = profil.getSegmentList().indexOf(seg);
        
        if (pom != 0)
        {
           profil.getSegmentList().get(pom).setStavkaList(seg.getStavkaList());
        }
        
        seg = null;
    }
    /*
    public void addStavkaRI()
    {
        stavka = new Stavka(1, "2017.01.01");
        stavkaListRI.add(stavka);        
    }
    
    public void deleteStavkaRI()
    {
        stavkaListRI.remove(stavkaListRI.size()-1);
    }
    
    public void addStavkaE()
    {
        stavka = new Stavka(1, "2017.01.01");
        stavkaListE.add(stavka);        
    }
    
    public void deleteStavkaE()
    {
        stavkaListE.remove(stavkaListE.size()-1);
    }
    
    public void addStavkaDA()
    {
        stavka = new Stavka(1, "2017.01.01");
        stavkaListDA.add(stavka);        
    }
    
    public void deleteStavkaDA()
    {
        stavkaListDA.remove(stavkaListDA.size()-1);
    }
    */
    
    /*
    public void sacuvajProfil() {
        //validacija();
        if (br == 0) {
            
            
            //p.setQstnId(secretQstnId);
            
            //rolaId = new Rola(2);
            //k.setRolaId(rolaId);
            
            //uspesnoSacuvanProfil = KontrolerKI.getInstance().sacuvajProfil(p); 

        }
        
    }
    */
    
    public void vratiSveProfile()
    {
        listaProfila = (List<Profil>)(List<?>)KontrolerKI.getInstance().vratiSveProfile();   
        listaProfila = new LinkedList<>(listaProfila);
    }
    
    public void vratiSveProfileZaKorisnika()
    {
        //globalni korisnik k ekser za sada
        //Korisnik k = new Korisnik(1);
        k1.getUsername();
        listaProfila = (List<Profil>)(List<?>)KontrolerKI.getInstance().vratiProfileKorisnika(k1);
    }
    
    public void vratiSveKorisnike()
    {
        listaKorisnika = (List<Korisnik>)(List<?>)KontrolerKI.getInstance().vratiSveKorisnike();   
        listaKorisnika = new LinkedList<>(listaKorisnika);
    }
    
    public void vratiSveSifarnike()
    {
        listaSifarnika = (List<Sifarnik>)(List<?>)KontrolerKI.getInstance().vratiSifarnike();   
        listaSifarnika = new LinkedList<>(listaSifarnika);
    }
    
    public void postaviFormuZaCVListu()
    {
      vratiSveProfileZaKorisnika();
      this.prikazTabele = true;  
      this.prikazFormeZaUnos = false;
      this.prikazTabeleSve = false;
      this.prikazTabeleSvihKorisnika = false;
      this.prikazTabeleSifarnika = false;
    }
    
    public void postaviFormuZaUnos()
    {
      this.prikazTabele = false;  
      this.prikazFormeZaUnos = true;
      this.prikazTabeleSve = false;
      this.prikazTabeleSvihKorisnika = false;
      this.prikazTabeleSifarnika = false;
    }
    
    public void postaviFormuZaSve()
    {
      vratiSveProfile();
      this.prikazTabele = false;  
      this.prikazFormeZaUnos = false;
      this.prikazTabeleSve = true;
      this.prikazTabeleSvihKorisnika = false;
      this.prikazTabeleSifarnika = false;
    }
    
    public void postaviFormuZaPrikazKorisnika()
    {
      vratiSveKorisnike();
      this.prikazTabele = false;  
      this.prikazFormeZaUnos = false;
      this.prikazTabeleSve = false;
      this.prikazTabeleSvihKorisnika = true;
      this.prikazTabeleSifarnika = false;
    }
    
    public void postaviFormuZaPrikazSifarnika()
    {
      vratiSveSifarnike();
      this.prikazTabele = false;  
      this.prikazFormeZaUnos = false;
      this.prikazTabeleSve = false;
      this.prikazTabeleSvihKorisnika = false;
      this.prikazTabeleSifarnika = true;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    public Segment getSeg() {
        return seg;
    }

    public void setSeg(Segment segment) {
        this.seg = segment;
    }


    /*
    public List<Stavka> getStavkaListRI() {
        return stavkaListRI;
    }

    public void setStavkaListRI(List<Stavka> stavkaListRI) {
        this.stavkaListRI = stavkaListRI;
    }

    public List<Stavka> getStavkaListE() {
        return stavkaListE;
    }

    public void setStavkaListE(List<Stavka> stavkaListE) {
        this.stavkaListE = stavkaListE;
    }

    public List<Stavka> getStavkaListDA() {
        return stavkaListDA;
    }

    public void setStavkaListDA(List<Stavka> stavkaListDA) {
        this.stavkaListDA = stavkaListDA;
    }
    */
    
    
    public Stavka getStavka() {
        return stavka;
    }

    public void setStavka(Stavka stavka) {
        this.stavka = stavka;
    }

    public Profil getP() {
        return p;
    }

    public void setP(Profil p) {
        this.p = p;
    }
    
    
    public void sacuvajProfil()
    {
        profil.setKorisnikId(MBPrijava.k1);
        KontrolerKI.getInstance().sacuvajProfil(profil);
    }
    
    public void izmenaProfila()
    {
        
    }

    public List<Profil> getListaProfila() {
        return listaProfila;
    }

    public void setListaProfila(List<Profil> listaProfila) {
        this.listaProfila = listaProfila;
    }

    public List<Korisnik> getListaKorisnika() {
        return listaKorisnika;
    }

    public void setListaKorisnika(List<Korisnik> listaKorisnika) {
        this.listaKorisnika = listaKorisnika;
    }

    public boolean isPrikazTabeleSvihKorisnika() {
        return prikazTabeleSvihKorisnika;
    }

    public void setPrikazTabeleSvihKorisnika(boolean prikazTabeleSvihKorisnika) {
        this.prikazTabeleSvihKorisnika = prikazTabeleSvihKorisnika;
    }

    public int getBr() {
        return br;
    }

    public void setBr(int br) {
        this.br = br;
    }

    public boolean isUspesnoSacuvanProfil() {
        return uspesnoSacuvanProfil;
    }

    public void setUspesnoSacuvanProfil(boolean uspesnoSacuvanProfil) {
        this.uspesnoSacuvanProfil = uspesnoSacuvanProfil;
    }

    public boolean isPrikazTabele() {
        return prikazTabele;
    }

    public void setPrikazTabele(boolean prikazTabele) {
        this.prikazTabele = prikazTabele;
    }

    public boolean isPrikazFormeZaUnos() {
        return prikazFormeZaUnos;
    }

    public void setPrikazFormeZaUnos(boolean prikazFormeZaUnos) {
        this.prikazFormeZaUnos = prikazFormeZaUnos;
    }

    public boolean isPrikazTabeleSve() {
        return prikazTabeleSve;
    }

    public void setPrikazTabeleSve(boolean prikazTabeleSve) {
        this.prikazTabeleSve = prikazTabeleSve;
    }

    public List<Stavka> getStavkaList() {
        return stavkaList;
    }

    public void setStavkaList(List<Stavka> stavkaList) {
        this.stavkaList = stavkaList;
    }

    public List<Segment> getSegmentList() {
        return segmentList;
    }

    public void setSegmentList(List<Segment> segmentList) {
        this.segmentList = segmentList;
    }
    
    
     
    
    //public void jasperProfil() throws JRException
    //{
            /*Jasper jasper = new Jasper();
            jasper.fill(listaProfila);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);
 
        //Exporting the report as a PDF
 
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());*/
    //}

    public List<Sifarnik> getListaSifarnika() {
        return listaSifarnika;
    }

    public void setListaSifarnika(List<Sifarnik> listaSifarnika) {
        this.listaSifarnika = listaSifarnika;
    }

    public boolean isPrikazTabeleSifarnika() {
        return prikazTabeleSifarnika;
    }

    public void setPrikazTabeleSifarnika(boolean prikazTabeleSifarnika) {
        this.prikazTabeleSifarnika = prikazTabeleSifarnika;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
      
    
}
