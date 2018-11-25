package com.ias.aves.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.json.bind.annotation.JsonbTransient;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "tont_paises")
public class Pais implements Serializable {
	
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "CDPAIS")
    private String cdPais;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DSNOMBRE")
    private String dsNombre;
	
    @JoinColumn(name = "CDZONA")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Zona zona;
	
    @JsonbTransient
    @OneToMany( mappedBy = "pais", fetch = FetchType.LAZY)
    private List<AvePais> aves = new ArrayList<>();
	
    public String getCdPais() {
            return cdPais;
    }

    public void setCdPais(String cdPais) {
            this.cdPais = cdPais;
    }

    public String getDsNombre() {
            return dsNombre;
    }

    public void setDsNombre(String dsNombre) {
            this.dsNombre = dsNombre;
    }

    public Zona getZona() {
            return zona;
    }

    public void setZona(Zona zona) {
            this.zona = zona;
    }

    public List<AvePais> getAves() {
            return aves;
    }

    public void setAves(List<AvePais> aves) {
            this.aves = aves;
    }

    @Override
    public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((cdPais == null) ? 0 : cdPais.hashCode());
            result = prime * result + ((dsNombre == null) ? 0 : dsNombre.hashCode());
            result = prime * result + ((zona == null) ? 0 : zona.hashCode());
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
            Pais other = (Pais) obj;
            if (cdPais == null) {
                    if (other.cdPais != null)
                            return false;
            } else if (!cdPais.equals(other.cdPais))
                    return false;
            if (dsNombre == null) {
                    if (other.dsNombre != null)
                            return false;
            } else if (!dsNombre.equals(other.dsNombre))
                    return false;
            if (zona == null) {
                    if (other.zona != null)
                            return false;
            } else if (!zona.equals(other.zona))
                    return false;
            return true;
    }
	
}
