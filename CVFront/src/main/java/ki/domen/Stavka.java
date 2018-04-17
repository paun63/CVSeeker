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

public class Stavka implements Serializable {

    private Integer id;
    private String datumOd;
    private String datumDo;
    private String heading1;
    private String heading2;
    private String opis;
    private Segment segmentId;

    public Stavka() {
    }

    public Stavka(Integer id) {
        this.id = id;
    }

    public Stavka(Integer id, String datumOd) {
        this.id = id;
        this.datumOd = datumOd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(String datumOd) {
        this.datumOd = datumOd;
    }

    public String getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(String datumDo) {
        this.datumDo = datumDo;
    }

    public String getHeading1() {
        return heading1;
    }

    public void setHeading1(String heading1) {
        this.heading1 = heading1;
    }

    public String getHeading2() {
        return heading2;
    }

    public void setHeading2(String heading2) {
        this.heading2 = heading2;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Segment getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(Segment segmentId) {
        this.segmentId = segmentId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Stavka other = (Stavka) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.datumOd, other.datumOd)) {
            return false;
        }
        if (!Objects.equals(this.datumDo, other.datumDo)) {
            return false;
        }
        if (!Objects.equals(this.heading1, other.heading1)) {
            return false;
        }
        if (!Objects.equals(this.heading2, other.heading2)) {
            return false;
        }
        if (!Objects.equals(this.opis, other.opis)) {
            return false;
        }
        if (!Objects.equals(this.segmentId, other.segmentId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Stavka{" + "id=" + id + ", datumOd=" + datumOd + ", datumDo=" + datumDo + ", heading1=" + heading1 + ", heading2=" + heading2 + ", opis=" + opis + ", segmentId=" + segmentId + '}';
    }
    
}
