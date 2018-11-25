package com.ias.aves.domain;

import java.io.Serializable;
import javax.json.bind.annotation.JsonbTransient;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tont_aves_pais")
@NamedQueries({
    @NamedQuery(name = AvePais.FIND_ALL , query = "SELECT f FROM AvePais f"),
    @NamedQuery(name = AvePais.FIND_BY_ID , query = "SELECT f FROM AvePais f where f.id.cdAve = :cdAve and f.id.cdPais = :cdPais"),
    @NamedQuery(name = AvePais.FIND_AVES_BY_NAME_AND_ZONE, query = "SELECT f FROM AvePais f WHERE upper(f.ave.dsNombreComun) like :nombre or upper(f.ave.dsNombreCientifico) like :nombre and upper(f.pais.zona.dsNombre) like :zona"),
    @NamedQuery(name = AvePais.FIND_AVES_BY_NAME, query = "SELECT f FROM AvePais f WHERE upper(f.ave.dsNombreComun) like :nombre or upper(f.ave.dsNombreCientifico) like :nombre"),
    @NamedQuery(name = AvePais.FIND_AVES_BY_ZONE, query = "SELECT f FROM AvePais f WHERE upper(f.pais.zona.dsNombre) like :zona")})
public class AvePais implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String FIND_ALL = "AvePais.findAll";
    public static final String FIND_BY_ID= "AvePais.findById";
    public static final String FIND_AVES_BY_NAME_AND_ZONE = "AvePais.findByNameAndZone";
    public static final String FIND_AVES_BY_NAME = "AvePais.findByName";
    public static final String FIND_AVES_BY_ZONE = "AvePais.findByZone";

    @JsonbTransient
    @EmbeddedId
    AvePaisId id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("cdAve")
    @JoinColumn(name = "CDAVE", referencedColumnName = "CDAVE")
    private Ave ave;
 
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("cdPais")
    @JoinColumn(name = "CDPAIS", referencedColumnName = "CDPAIS")
    private Pais pais;

    public AvePaisId getId() {
            return id;
    }

    public void setId(AvePaisId id) {
            this.id = id;
    }

    public Ave getAve() {
            return ave;
    }

    public void setAve(Ave ave) {
            this.ave = ave;
    }

    public Pais getPais() {
            return pais;
    }

    public void setPais(Pais pais) {
            this.pais = pais;
    }

    @Override
    public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((ave == null) ? 0 : ave.hashCode());
            result = prime * result + ((id == null) ? 0 : id.hashCode());
            result = prime * result + ((pais == null) ? 0 : pais.hashCode());
            return result;
    }

    @Override
    public boolean equals(Object obj) {
            if (this == obj)
                    return true;
            if (obj == null)
                    return false;
            if (getClass() != obj.getClass())
                    return false;
            AvePais other = (AvePais) obj;
            if (ave == null) {
                    if (other.ave != null)
                            return false;
            } else if (!ave.equals(other.ave))
                    return false;
            if (id == null) {
                    if (other.id != null)
                            return false;
            } else if (!id.equals(other.id))
                    return false;
            if (pais == null) {
                    if (other.pais != null)
                            return false;
            } else if (!pais.equals(other.pais))
                    return false;
            return true;
    }
    
    
}
