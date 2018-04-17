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

public class Segment implements Serializable {

    private Integer id;
    private String heading;
    private String opis;
    private List<Stavka> stavkaList;
    private Profil profilId;

    public Segment() {
    }

    public Segment(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Segment other = (Segment) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.heading, other.heading)) {
            return false;
        }
        if (!Objects.equals(this.opis, other.opis)) {
            return false;
        }
        if (!Objects.equals(this.stavkaList, other.stavkaList)) {
            return false;
        }
        if (!Objects.equals(this.profilId, other.profilId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Segment{" + "id=" + id + ", heading=" + heading + ", opis=" + opis + ", stavkaList=" + stavkaList + ", profilId=" + profilId + '}';
    }

}
