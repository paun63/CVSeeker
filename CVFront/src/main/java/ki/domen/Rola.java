/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ki.domen;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author P
 */

public class Rola implements Serializable {


    private Integer id;
    private String naziv;
    //private List<Korisnik> korisnikList;

    public Rola() {
    }

    public Rola(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rola other = (Rola) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.naziv, other.naziv)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Rola{" + "id=" + id + ", naziv=" + naziv + '}';
    }

}