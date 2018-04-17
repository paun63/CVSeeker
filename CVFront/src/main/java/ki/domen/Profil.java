/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ki.domen;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author P
 */

public class Profil implements Serializable {

    private Integer id;
    private String datum;
    private String naziv;
    private String opis;
    private Korisnik korisnikId;
    private List<Segment> segmentList;

    public Profil() {
    }

    public Profil(Integer id) {
        this.id = id;
    }

    public Profil(Integer id, String datum) {
        this.id = id;
        this.datum = datum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Korisnik getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Korisnik korisnikId) {
        this.korisnikId = korisnikId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Profil other = (Profil) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.datum, other.datum)) {
            return false;
        }
        if (!Objects.equals(this.naziv, other.naziv)) {
            return false;
        }
        if (!Objects.equals(this.opis, other.opis)) {
            return false;
        }
        if (!Objects.equals(this.korisnikId, other.korisnikId)) {
            return false;
        }
        if (!Objects.equals(this.segmentList, other.segmentList)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Profil{" + "id=" + id + ", datum=" + datum + ", naziv=" + naziv + ", opis=" + opis + ", korisnikId=" + korisnikId + ", segmentList=" + segmentList + '}';
    }
    
}

