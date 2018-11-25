package com.ias.aves.domain.service;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import com.ias.aves.domain.Ave;
import com.ias.utils.data.checks.PersistenceChecks;
import org.slf4j.Logger;

/**
 * Clase usada para encontrar y administrar las aves
 * 
 * @author jnamla
 */
@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class AvesService {
    
    private EntityManager entityManager;
   
    private Logger logger;
    
    public AvesService() {
        
    }
    
    @Inject
    public AvesService(EntityManager entityManager, Logger logger) {
        this.entityManager = entityManager;
        this.logger = logger;
    }
    
    /**
     * Devuelve todas las aves
     *
     * @return a collection of aves
     */
    public Collection<Ave> findAll() {
        logger.info("Encontrar todas las aves.");
        TypedQuery<Ave> findAll = entityManager.createNamedQuery(Ave.FIND_ALL, Ave.class);
        Collection<Ave> results = Collections.unmodifiableCollection(findAll.getResultList());
        PersistenceChecks.checkNotFoundStatus(results, Ave.class);
        return results;
    }
    
    /**
     * Encuentra una ave por su id y devuelve una referencia a ella.
     *
     * @param idAve  El id
     * @return la ave
     */
    public Ave findByIdAve(String idAve) {
        logger.info("Encontrar ave por id {}.", idAve);
        Ave reference = entityManager.find(Ave.class, idAve);
        PersistenceChecks.checkNotFoundStatus(reference, Ave.class);
        return reference;
    }
    
    /**
     * Encuentra una ave por su nombre y devuelve una referencia a ella.
     *
     * @param nombre El nombre
     * @return la ave
     */
    public Collection<Ave> findByName(String nombre) {
        logger.info("Encontrar ave por nombre {}.", nombre);
        TypedQuery<Ave> findByName = entityManager.createNamedQuery(Ave.FIND_BY_NAME, Ave.class)
                .setParameter("nombre", "%" + Objects.requireNonNull(nombre) + "%");
        
        Collection<Ave> results = Collections.unmodifiableCollection(findByName.getResultList());
        
        return results;
    }
    
    /**
     * Crea una nueva ave.
     *
     * @param ave Ave para crear
     */
    public void create(Ave ave) {
        Objects.requireNonNull(ave);
        logger.info("Creando {}.", ave);
        PersistenceChecks.isValidCheck(!this.exists(ave), "Already exists a fixed asset with that information");
        entityManager.persist(ave);
    }

    /**
     * Actualiza ave dado su id.
     *
     * @param idAve id de la ave a buscar
     * @param ave Ave a 
     */
    public void update(String idAve, Ave ave) {
        Objects.requireNonNull(ave);
        logger.info("Actualizando {} con id {}.", new Object[]{ave, idAve});
        PersistenceChecks.isIdConsistentForUpdate(Objects.equals(idAve, ave.getCdAve()), Ave.class);
        Ave reference = entityManager.getReference(Ave.class, Objects.requireNonNull(idAve));
        entityManager.merge(ave);
    }

    /**
     * Elimina ave por su id
     *
     * @param idAve El id del Ave
     */
    public void delete(String idAve) {
        Objects.requireNonNull(idAve);
        logger.info("Eliminando ave con id {}.", idAve);
        Ave reference = entityManager.find(Ave.class, idAve);
        PersistenceChecks.checkNotFoundStatus(reference, Ave.class);
        entityManager.remove(reference);
    }
    
    /**
     * Verificacion de que la ave existe en la base de datos.
     *
     * @param ave campos para validacion de existencia
     * @return verdadero si existe, falso de lo contrario
     */
    public boolean exists(Ave ave) {
        logger.info("Validando existencia de ave con id idAve {}.", ave.getCdAve());
        Ave reference = entityManager.find(Ave.class, ave.getCdAve());
        return !Objects.isNull(reference);
    }
 
}
