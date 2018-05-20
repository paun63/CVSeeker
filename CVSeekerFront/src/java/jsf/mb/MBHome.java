/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.mb;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import static jsf.mb.MBPrijava.k1;
import ki.domen.Korisnik;
import ki.domen.Notifikacija;
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
    private Notifikacija notifikacija;
    private Sifarnik sifarnik;
    /*private String heading1;
    private String heading2;
    private String datumOd;
    private String datumDo;
    private String opisStavke;*/

    private List<Profil> listaProfila;
    private List<Korisnik> listaKorisnika;
    private List<Sifarnik> listaSifarnika;
    private List<Notifikacija> listaNotifikacija;
    private Profil p;
    
    String porukaValidacija1 = "";
    String porukaValidacija2 = "";
    
    boolean admin;
    boolean prikazTabele;
    boolean prikazFormeZaUnos;
    boolean prikazTabeleSve;
    boolean prikazTabeleSvihKorisnika;
    boolean prikazTabeleSifarnika;
    boolean prikazTabeleZahteva;
    
    int br = 0;
    boolean uspesnoSacuvanProfil;
    
    private static final DateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
    
    public MBHome() {
      if (!(k1.getRolaId().getId() == 1)) 
      {
        this.prikazTabele = true;  
        this.prikazFormeZaUnos = false;
        this.prikazTabeleSve = false;
        this.prikazTabeleZahteva = false;
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
        this.prikazTabeleZahteva = false;
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
      vratiSveNotifikacije();
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
        if (profil.getSegmentList() == null)
        {
             List<Segment> ls = new LinkedList<>();
              profil.setSegmentList(ls);
        }
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
    
    public void notifikacijaPrihvati(Notifikacija n)
    {
        n.setFlagOdobreno(true);
        n.setFlagPrikazana(true);        
        KontrolerKI.getInstance().SacuvajNotifikaciju(n);
        vratiSveNotifikacije();    
    }
    
    public void notifikacijaOdbij(Notifikacija n)
    {
        n.setFlagOdobreno(false);
        n.setFlagPrikazana(true);
        KontrolerKI.getInstance().SacuvajNotifikaciju(n);
        vratiSveNotifikacije(); 
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
    public void jasperProfil(Profil p) throws IOException
    {
        
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("http://localhost:8080/profil/jasper/"+p.getId());
    }
    
    public void jasperProfil2(Profil p) throws IOException
    {   
        if (p.getKorisnikId().getId() == k1.getId())
        {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect("http://localhost:8080/profil/jasper/"+p.getId());
            return;
        }
        boolean mozeValidacija = false;
        Notifikacija nTemp = new Notifikacija();
        List<Notifikacija> listaNotifikacija2 = (List<Notifikacija>)(List<?>)KontrolerKI.getInstance().vratiSveNotifikacije();
        for(Notifikacija n : listaNotifikacija2){
               if((n.getVlasnikZahteva() == k1.getId()) && (n.getValsnikProfila()== p.getKorisnikId().getId())
                && (n.getProfil()== p.getId()) )
               {
                   mozeValidacija = true;
                   nTemp = n;
               }                
            }   
        if(mozeValidacija){                       
            if (KontrolerKI.getInstance().ValidirajNotifikaciju(nTemp))
            {
                ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                context.redirect("http://localhost:8080/profil/jasper/"+p.getId());
            }
            else
            {
                porukaValidacija1 = "* Nije odobren pristup CV-ju korisnika.";
            }
        }
        else
        {
            Notifikacija n = new Notifikacija(0, p.getId(), p.getKorisnikId().getId(), k1.getId(), new Timestamp(System.currentTimeMillis()));           
            n.setFlagPrikazana(false);
            n.setFlagOdobreno(false);
            KontrolerKI.getInstance().SacuvajNotifikaciju(n);
            porukaValidacija2 = "* Poslat je zahtev. Ceka se odobrenje.";
        }
    }
    
    public void removeProfil(Profil p)
    {
        KontrolerKI.getInstance().obrisiProfil(p);
        vratiSveProfileZaKorisnika();
        //listaProfila.remove(p);
    }
    
    public void izmenaProfila(Profil pr)
    {
        profil = pr;
        postaviFormuZaUnos();
    }
    
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
    
    public void vratiSveNotifikacije()
    {
        listaNotifikacija = (List<Notifikacija>)(List<?>)KontrolerKI.getInstance().vratiNotifikacijeKorisnika(k1);   
        if(listaNotifikacija != null)
        {
            listaNotifikacija = new LinkedList<>(listaNotifikacija);
        }        
    }
    
    public void postaviFormuZaCVListu()
    {
      vratiSveProfileZaKorisnika();
      this.prikazTabele = true;  
      this.prikazFormeZaUnos = false;
      this.prikazTabeleSve = false;
      this.prikazTabeleZahteva = false;
      this.prikazTabeleSvihKorisnika = false;
      this.prikazTabeleSifarnika = false;
    }
    
    public void postaviFormuZaUnos()
    {
      profil = new Profil();
      //stavkaList = new LinkedList<Stavka>();
      this.prikazTabele = false;  
      this.prikazFormeZaUnos = true;
      this.prikazTabeleSve = false;
      this.prikazTabeleZahteva = false;
      this.prikazTabeleSvihKorisnika = false;
      this.prikazTabeleSifarnika = false;
    }
    
    public void postaviFormuZaSve()
    {
      vratiSveProfile();
      this.prikazTabele = false;  
      this.prikazFormeZaUnos = false;
      this.prikazTabeleSve = true;
      this.prikazTabeleZahteva = false;
      this.prikazTabeleSvihKorisnika = false;
      this.prikazTabeleSifarnika = false;
    }
    
    public void postaviFormuZaZahteve()
    {
      this.prikazTabele = false;  
      this.prikazFormeZaUnos = false;
      this.prikazTabeleSve = false;
      this.prikazTabeleZahteva = true;
      this.prikazTabeleSvihKorisnika = false;
      this.prikazTabeleSifarnika = false;
    }
    
    public void postaviFormuZaPrikazKorisnika()
    {
      vratiSveKorisnike();
      this.prikazTabele = false;  
      this.prikazFormeZaUnos = false;
      this.prikazTabeleSve = false;
      this.prikazTabeleZahteva = false;
      this.prikazTabeleSvihKorisnika = true;
      this.prikazTabeleSifarnika = false;
    }
    
    public void postaviFormuZaPrikazSifarnika()
    {
      vratiSveSifarnike();
      this.prikazTabele = false;  
      this.prikazFormeZaUnos = false;
      this.prikazTabeleSve = false;
      this.prikazTabeleZahteva = false;
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
        Date date = new Date();
        profil.setDatum(sdf.format(date));
        KontrolerKI.getInstance().sacuvajProfil(profil);
        postaviFormuZaCVListu();
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

    public Notifikacija getNotifikacija() {
        return notifikacija;
    }

    public void setNotifikacija(Notifikacija notifikacija) {
        this.notifikacija = notifikacija;
    }

    public Sifarnik getSifarnik() {
        return sifarnik;
    }

    public void setSifarnik(Sifarnik sifarnik) {
        this.sifarnik = sifarnik;
    }

    public String getPorukaValidacija1() {
        return porukaValidacija1;
    }

    public void setPorukaValidacija1(String porukaValidacija1) {
        this.porukaValidacija1 = porukaValidacija1;
    }

    public String getPorukaValidacija2() {
        return porukaValidacija2;
    }

    public void setPorukaValidacija2(String porukaValidacija2) {
        this.porukaValidacija2 = porukaValidacija2;
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
         
    /*public void saveMessage() {
        
        for (Notifikacija n : listaNotifikacija) {
            FacesContext context = FacesContext.getCurrentInstance();
            //Korisnik vlasnikZahteva = new Korisnik(n.getVlasnikZahteva());
            //vlasnikZahteva = (Korisnik) KontrolerKI.getInstance().vratiKorisnka(vlasnikZahteva);
            context.addMessage(null, new FacesMessage("Korisnik:" + n.getVlasnikZahteva() ,  "Traži pristup Vašem profilu broj: " + n.getProfil()));
        }
        
        
    }*/

    public List<Notifikacija> getListaNotifikacija() {
        return listaNotifikacija;
    }

    public boolean isPrikazTabeleZahteva() {
        return prikazTabeleZahteva;
    }

    public void setListaNotifikacija(List<Notifikacija> listaNotifikacija) {
        this.listaNotifikacija = listaNotifikacija;
    }

    public void setPrikazTabeleZahteva(boolean prikazTabeleZahteva) {
        this.prikazTabeleZahteva = prikazTabeleZahteva;
    }
    
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index.xhtml?faces-redirect=true";
    }
      
    
}
