package com.ias.aves.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Embeddable
public class AvePaisId implements Serializable {
	
    private static final long serialVersionUID = 1L;
	
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "CDAVE")
    private String cdAve;
	
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "CDPAIS")
    private String cdPais;

    public AvePaisId() {
        
    }
    
    public AvePaisId(@NotNull @Size(min = 1, max = 3) String cdAve, @NotNull @Size(min = 1, max = 3) String cdPais) {
            super();
            this.cdAve = cdAve;
            this.cdPais = cdPais;
    }

    public String getCdAve() {
            return cdAve;
    }

    public void setCdAve(String cdAve) {
            this.cdAve = cdAve;
    }

    public String getCdPais() {
            return cdPais;
    }

    public void setCdPais(String cdPais) {
            this.cdPais = cdPais;
    }

    @Override
    public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((cdAve == null) ? 0 : cdAve.hashCode());
            result = prime * result + ((cdPais == null) ? 0 : cdPais.hashCode());
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
            AvePaisId other = (AvePaisId) obj;
            if (cdAve == null) {
                    if (other.cdAve != null)
                            return false;
            } else if (!cdAve.equals(other.cdAve))
                    return false;
            if (cdPais == null) {
                    if (other.cdPais != null)
                            return false;
            } else if (!cdPais.equals(other.cdPais))
                    return false;
            return true;
    }

    
}
