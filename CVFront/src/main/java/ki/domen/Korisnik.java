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
 * @author Paun
 */

public class Korisnik implements Serializable{
    
    private Integer id;
    private String ime;
    private String prezime;
    private String username;
    private String password;
    private Boolean lock;
    private String qstnAns;
    private List<Profil> profilList;
    private Secretqstn qstnId;
    private Rola rolaId;

    public Korisnik() {
    }

    public Korisnik(Integer id) {
        this.id = id;
    }

    public Korisnik(Integer id, String ime, String prezime, String username, String password, String qstnAns) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
        this.qstnAns = qstnAns;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getLock() {
        return lock;
    }

    public void setLock(Boolean lock) {
        this.lock = lock;
    }

    public String getQstnAns() {
        return qstnAns;
    }

    public void setQstnAns(String qstnAns) {
        this.qstnAns = qstnAns;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Korisnik other = (Korisnik) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.lock, other.lock)) {
            return false;
        }
        if (!Objects.equals(this.qstnAns, other.qstnAns)) {
            return false;
        }
        if (!Objects.equals(this.profilList, other.profilList)) {
            return false;
        }
        if (!Objects.equals(this.qstnId, other.qstnId)) {
            return false;
        }
        if (!Objects.equals(this.rolaId, other.rolaId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Korisnik{" + "id=" + id + ", ime=" + ime + ", prezime=" + prezime + ", username=" + username + ", password=" + password + ", lock=" + lock + ", qstnAns=" + qstnAns + ", profilList=" + profilList + ", qstnId=" + qstnId + ", rolaId=" + rolaId + '}';
    }
    
    
    
}
