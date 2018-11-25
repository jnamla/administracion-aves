package com.ias.aves.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.json.bind.annotation.JsonbTransient;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tont_zonas")
@NamedQueries({
    @NamedQuery(name = Zona.FIND_ALL , query = "SELECT f FROM Zona f")})
public class Zona implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String FIND_ALL = "Zona.findAll";
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "CDZONA")
    private String cdZona;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "DSNOMBRE")
    private String dsNombre;

    @JsonbTransient
    @OneToMany( mappedBy = "zona", fetch=FetchType.LAZY )
    private List<Pais> paises = new ArrayList<>();
    
    public String getCdZona() {
            return cdZona;
    }

    public void setCdZona(String cdZona) {
            this.cdZona = cdZona;
    }

    public String getDsNombre() {
            return dsNombre;
    }

    public void setDsNombre(String dsNombre) {
            this.dsNombre = dsNombre;
    }

    public List<Pais> getPaises() {
            return paises;
    }

    public void setPaises(List<Pais> paises) {
            this.paises = paises;
    }

    @Override
    public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((cdZona == null) ? 0 : cdZona.hashCode());
            result = prime * result + ((dsNombre == null) ? 0 : dsNombre.hashCode());
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
            Zona other = (Zona) obj;
            if (cdZona == null) {
                    if (other.cdZona != null)
                            return false;
            } else if (!cdZona.equals(other.cdZona))
                    return false;
            if (dsNombre == null) {
                    if (other.dsNombre != null)
                            return false;
            } else if (!dsNombre.equals(other.dsNombre))
                    return false;
            return true;
    }

    
}