package com.ias.aves.domain.service;

import com.ias.aves.domain.AvePais;
import com.ias.aves.domain.AvePaisId;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import com.ias.utils.data.checks.PersistenceChecks;
import org.slf4j.Logger;

/**
 * Servicio para encontrar y administar entidades AvePais
 * 
 * @author jnamla
 */
@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class AvePaisService {
    
    private EntityManager entityManager;
   
    private Logger logger;
    
    public AvePaisService() {
        
    }
    
    @Inject
    public AvePaisService(EntityManager entityManager, Logger logger) {
        this.entityManager = entityManager;
        this.logger = logger;
    }
    
    /**
     * Devuelve todas las avePais existentes en base de datos.
     *
     * @return a collection of avePais
     */
    public Collection<AvePais> findAll() {
        logger.info("Encontrar todos los avePais.");
        TypedQuery<AvePais> findAll = entityManager.createNamedQuery(AvePais.FIND_ALL, AvePais.class);
        Collection<AvePais> results = Collections.unmodifiableCollection(findAll.getResultList());
        PersistenceChecks.checkNotFoundStatus(results, AvePais.class);
        return results;
    }
    
    /**
     * Devuelve todas las avePais existentes por nombre y zona.
     *
     * @param nombre Busca ave por nombre
     * @param zona Busca ave por zona
     * 
     * @return a collection of avePaiss
     */
    public Collection<AvePais> findByNameAndZone(String nombre, String zona) {
        logger.info("Buscar ave por nombre y zona.");
        TypedQuery<AvePais> findAll = entityManager.createNamedQuery(AvePais.FIND_AVES_BY_NAME_AND_ZONE, AvePais.class)
                .setParameter("nombre", "%" + Objects.requireNonNull(nombre).toUpperCase() + "%" )
                .setParameter("zona", "%" + Objects.requireNonNull(zona).toUpperCase()+ "%");
        Collection<AvePais> results = Collections.unmodifiableCollection(findAll.getResultList());
        PersistenceChecks.checkNotFoundStatus(results, AvePais.class);
        return results;
    }
    
    /**
     * Devuelve La lista de avePais.
     *
     * @param nombreAve Busca aves por su nombre cientifico o comun 
     * 
     * @return a collection of avePais
     */
    public Collection<AvePais> findByNombreAve(String nombreAve) {
        logger.info("Buscar ave por nombre {}.", nombreAve);
        TypedQuery<AvePais> findAll = entityManager.createNamedQuery(AvePais.FIND_AVES_BY_NAME, AvePais.class)
                .setParameter("nombre", "%" + Objects.requireNonNull(nombreAve).toUpperCase() + "%" );
        Collection<AvePais> results = Collections.unmodifiableCollection(findAll.getResultList());
        PersistenceChecks.checkNotFoundStatus(results, AvePais.class);
        return results;
    }
    
    /**
     * Devuelve La lista de avePais por zona.
     *
     * @param zona Busca aves por su zona en la que esta registrado
     * 
     * @return a collection of avePais
     */
    public Collection<AvePais> findByZona(String zona) {
        logger.info("Buscar ave por zona {}.", zona);
        TypedQuery<AvePais> findAll = entityManager.createNamedQuery(AvePais.FIND_AVES_BY_ZONE, AvePais.class)
                .setParameter("zona", "%" + Objects.requireNonNull(zona).toUpperCase()+ "%");
        Collection<AvePais> results = Collections.unmodifiableCollection(findAll.getResultList());
        PersistenceChecks.checkNotFoundStatus(results, AvePais.class);
        return results;
    }
    
    /**
     * Busca AvePais por Id y devuelve referencia .
     *
     * @param avePais avePais parameters to check
     * @return avePais if exists, otherwise null
     */
    public AvePais findById(AvePais avePais) {
        logger.info("Buscando avePais con ave {} y pais {}.", new Object[]{avePais.getAve(), avePais.getPais()});
        TypedQuery<AvePais> result = entityManager.createNamedQuery(AvePais.FIND_BY_ID, AvePais.class)
                .setParameter("cdAve", Objects.requireNonNull(avePais.getId().getCdAve()))
                .setParameter("cdPais", Objects.requireNonNull(avePais.getId().getCdPais()));
        try {
           return result.getSingleResult(); 
        } catch (Exception e) {
            return null;
        }
    }
 
}
