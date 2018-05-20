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

public class Secretqstn implements Serializable {


    private Integer id;
    private String opis;
    private String opisUS;
    //private List<Korisnik> korisnikList;

    public Secretqstn() {
    }

    public Secretqstn(Integer id) {
        this.id = id;
    }

    public Secretqstn(Integer id, String opis, String opisUS) {
        this.id = id;
        this.opis = opis;
        this.opisUS = opisUS;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getOpisUS() {
        return opisUS;
    }

    public void setOpisUS(String opisUS) {
        this.opisUS = opisUS;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Secretqstn other = (Secretqstn) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.opis, other.opis)) {
            return false;
        }
        if (!Objects.equals(this.opisUS, other.opisUS)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Secretqstn{" + "id=" + id + ", opis=" + opis + ", opisUS=" + opisUS + '}';
    }
    
}
