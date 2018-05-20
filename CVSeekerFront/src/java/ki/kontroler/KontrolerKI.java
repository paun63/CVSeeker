/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ki.kontroler;

import java.util.List;
import ki.domen.IDomenKI;
import ki.so.AbstractSO;
import ki.so.SOBrisanjeProfila;
import ki.so.SOBrisanjeSifarnika;
import ki.so.SOPrijava;
import ki.so.SORegistracija;
import ki.so.SOResetLozinke;
import ki.so.SOSacuvajSifarnik;
import ki.so.SOTajnoPitanje;
import ki.so.SOUnosProfila;
import ki.so.SOVratiKorisnika;
import ki.so.SOVratiProfile;
import ki.so.SOVratiSifarnike;
import ki.so.SOVratiSveKorisnike;
import ki.so.SOVratiTajnoPitanjeKorisnika;


/**
 *
 * @author P
 */
public class KontrolerKI {
    
    private static KontrolerKI instance;
    
    public static KontrolerKI getInstance() 
    {
        if (instance == null) {
            instance = new KontrolerKI();
        }
        return instance;
    }
    
    public List<IDomenKI> vratiSveProfile()
    {
        AbstractSO so = new SOVratiProfile();       
           
        return ((SOVratiProfile)so).izvrsi();
    }
    
    public IDomenKI vratiProfil(Integer id)
    {
        AbstractSO so = new SOVratiProfile();       
           
        return ((SOVratiProfile)so).izvrsi(id);
    }
    
    public List<IDomenKI> vratiProfileKorisnika(IDomenKI ODKI)
    {
        AbstractSO so = new SOVratiProfile();       
           
        return ((SOVratiProfile)so).izvrsi(ODKI);
    }
    
    public IDomenKI sacuvajProfil(IDomenKI ODKI) //nakon uspesnog unosa vracamo izgeld objekta kao potvrdu, suprotno null
    {
       AbstractSO so = new SOUnosProfila();       
           
       return ((SOUnosProfila)so).izvrsi(ODKI); 
    }
    
    public boolean obrisiProfil(IDomenKI ODKI) //true, false
    {
        AbstractSO so = new SOBrisanjeProfila();       
           
        return ((SOBrisanjeProfila)so).izvrsi(ODKI);       
    }
    
    public String prijava(IDomenKI ODKI) // ako nije null imamo token koji bi valjalo dodeliti nekoj listi ulogovanih korisnika
    {
        AbstractSO so = new SOPrijava();       
           
        return ((SOPrijava)so).izvrsi(ODKI);
    }
    
    public IDomenKI vratiKorisnka(IDomenKI ODKI) // dobijamo kompletan objekat korisnika ili null
    {
        AbstractSO so = new SOVratiKorisnika();       
           
        return ((SOVratiKorisnika)so).izvrsi(ODKI);
    }
    
    public boolean registracija(IDomenKI ODKI) //iz registracije ako je true idemo na login
    {
        AbstractSO so = new SORegistracija();       
           
        return ((SORegistracija)so).izvrsi(ODKI);
    }
    
    public boolean resetLozinke(IDomenKI ODKI) //iz reseta ozinke dobijamo objekat korisnika sa azutiranom lozinkom ili null ukoliko iz nekog razloga nije uspelo
    {
        AbstractSO so = new SOResetLozinke();       
           
        return ((SOResetLozinke)so).izvrsi(ODKI);
    }
    
    public IDomenKI TajnoPitanje() //ili dobijamo random tajno pitanje ili null
    {
        AbstractSO so = new SOTajnoPitanje();       
           
        return ((SOTajnoPitanje)so).izvrsi();
    }
    
    public IDomenKI TajnoPitanjeKorisnika(IDomenKI ODKI) //ili dobijamo tajno pitanje koje je postavljeno korisniku pri registraciji ili null
    {
        AbstractSO so = new SOVratiTajnoPitanjeKorisnika();       
           
        return ((SOVratiTajnoPitanjeKorisnika)so).izvrsi(ODKI);
    }
    
    public List<IDomenKI> vratiSveKorisnike()
    {
        AbstractSO so = new SOVratiSveKorisnike();       
           
        return ((SOVratiSveKorisnike)so).izvrsi();
    }
    
    public List<IDomenKI> vratiSifarnike()
    {
        AbstractSO so = new SOVratiSifarnike();       
           
        return ((SOVratiSifarnike)so).izvrsi();
    }
    
    public IDomenKI sacuvajSifarnik(IDomenKI ODKI) 
    {
       AbstractSO so = new SOSacuvajSifarnik();       
           
       return ((SOSacuvajSifarnik)so).izvrsi(ODKI); 
    }
    
    public boolean obrisiSifarnik(IDomenKI ODKI) 
    {
        AbstractSO so = new SOBrisanjeSifarnika();       
           
        return ((SOBrisanjeSifarnika)so).izvrsi(ODKI);       
    }
    
    
}
