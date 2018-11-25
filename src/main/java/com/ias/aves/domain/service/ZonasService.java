package com.ias.aves.domain.service;

import java.util.Collection;
import java.util.Collections;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import com.ias.aves.domain.Zona;
import com.ias.utils.data.checks.PersistenceChecks;
import org.slf4j.Logger;

/**
 * ZonasService se utiliza para administrar las operaciones de base de datos para Zonas
 * 
 * @author jnamla
 */
@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class ZonasService {
    
    private EntityManager entityManager;
   
    private Logger logger;
    
    public ZonasService() {
        
    }
    
    @Inject
    public ZonasService(EntityManager entityManager, Logger logger) {
        this.entityManager = entityManager;
        this.logger = logger;
    }
    
    /**
     * Devuelve todas las zonas existentes.
     *
     * @return una coleccion de Zonas
     */
    public Collection<Zona> findAll() {
        logger.info("Encontrar todas las zonas.");
        TypedQuery<Zona> findAll = entityManager.createNamedQuery(Zona.FIND_ALL, Zona.class);
        Collection<Zona> results = Collections.unmodifiableCollection(findAll.getResultList());
        PersistenceChecks.checkNotFoundStatus(results, Zona.class);
        return results;
    }

}
