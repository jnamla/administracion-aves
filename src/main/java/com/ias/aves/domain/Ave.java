package com.ias.aves.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.json.bind.annotation.JsonbTransient;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tont_aves")
@NamedQueries({
    @NamedQuery(name = Ave.FIND_ALL , query = "SELECT f FROM Ave f"),
    @NamedQuery(name = Ave.FIND_BY_NAME, query = "SELECT f FROM Ave f WHERE f.dsNombreComun like :nombre or f.dsNombreCientifico like :nombre")})
public class Ave implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "Ave.findAll";
    public static final String FIND_BY_NAME = "Ave.findByName";
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "CDAVE")
    private String cdAve;
	
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DSNOMBRE_COMUN")
    private String dsNombreComun;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "DSNOMBRE_CIENTIFICO")
    private String dsNombreCientifico;
    
    @JsonbTransient
    @OneToMany( mappedBy = "ave", fetch = FetchType.LAZY)
    private List<AvePais> paises = new ArrayList<>();
    
    public String getCdAve() {
        return cdAve;
    }

    public void setCdAve(String cdAve) {
        this.cdAve = cdAve;
    }

    public String getDsNombreComun() {
        return dsNombreComun;
    }

    public void setDsNombreComun(String dsNombreComun) {
        this.dsNombreComun = dsNombreComun;
    }

    public String getDsNombreCientifico() {
        return dsNombreCientifico;
    }

    public void setDsNombreCientifico(String dsNombreCientifico) {
        this.dsNombreCientifico = dsNombreCientifico;
    }

    public List<AvePais> getPaises() {
        return paises;
    }

    public void setPaises(List<AvePais> paises) {
        this.paises = paises;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cdAve == null) ? 0 : cdAve.hashCode());
        result = prime * result + ((dsNombreCientifico == null) ? 0 : dsNombreCientifico.hashCode());
        result = prime * result + ((dsNombreComun == null) ? 0 : dsNombreComun.hashCode());
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
        Ave other = (Ave) obj;
        if (cdAve == null) {
                if (other.cdAve != null)
                        return false;
        } else if (!cdAve.equals(other.cdAve))
                return false;
        if (dsNombreCientifico == null) {
                if (other.dsNombreCientifico != null)
                        return false;
        } else if (!dsNombreCientifico.equals(other.dsNombreCientifico))
                return false;
        if (dsNombreComun == null) {
                if (other.dsNombreComun != null)
                        return false;
        } else if (!dsNombreComun.equals(other.dsNombreComun))
                return false;
        return true;
    }

    
}
