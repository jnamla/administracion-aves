package com.ias.aves.domain.service;

import com.ias.aves.domain.Ave;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import com.ias.utils.data.transformation.Transformer;
import org.slf4j.LoggerFactory;

/**
 * JPA unit test para avesService.
 * @author jnamla
 */
public class AvesServiceTest {
    
    private AvesService avesService;
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    
    public AvesServiceTest() {
    }
    
    @Before
    public void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("avesTestPU");
        entityManager = entityManagerFactory.createEntityManager();
        avesService = new AvesService(entityManager, LoggerFactory.getLogger(AvesServiceTest.class));
    }
    
    @After
    public void tearDown() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }
    
    /**
     * Prueba de findAll method, of class AveService.
     */
    @Test
    public void testFindAll() {
        Collection<Ave> result = avesService.findAll();
        assertThat(result.isEmpty(), is(false));
    }
    
    /**
     * Prueba de findByIdAve method, of class AveService.
     */
    @Test
    public void testFindByIdAve() {
        Ave expResult;
        expResult = Transformer.jsonToObjetFromInputStream(getClass().getResourceAsStream("/data/ave/json/entity.json"), Ave.class);
        Ave result = avesService.findByIdAve(expResult.getCdAve());
        assertThat(result, equalTo(expResult));
    }
    
//    @Test
//    public void create() {
//        Ave toCreate, created;
//        toCreate = Transformer.jsonToObjetFromInputStream(getClass().getResourceAsStream("/data/ave/json/entity_create.json"), Ave.class);
//        entityManager.getTransaction().begin();
//        avesService.create(toCreate);
//        entityManager.getTransaction().commit();
//        created = avesService.findByIdAve(toCreate.getCdAve());
//        assertThat(created, is(notNullValue()));
//    }
    
    @Test (expected = InternalServerErrorException.class)
    public void createExistent() {
        Ave toCreate;
        toCreate = Transformer.jsonToObjetFromInputStream(getClass().getResourceAsStream("/data/ave/json/entity_create_existent.json"), Ave.class);
        avesService.create(toCreate);
    }
    
    @Test
    public void update() {
        Ave toUpdate, updated;
        toUpdate = Transformer.jsonToObjetFromInputStream(getClass().getResourceAsStream("/data/ave/json/entity_update.json"), Ave.class);
        avesService.update(toUpdate.getCdAve(), toUpdate);
        updated = avesService.findByIdAve(toUpdate.getCdAve());
        assertThat(updated.getDsNombreComun(), equalTo(toUpdate.getDsNombreComun()));
        assertThat(updated.getDsNombreCientifico(), equalTo(toUpdate.getDsNombreCientifico()));
    }
    
    @Test (expected = BadRequestException.class)
    public void updateInconsistentId() {
        Ave toUpdate;
        toUpdate = Transformer.jsonToObjetFromInputStream(getClass().getResourceAsStream("/data/ave/json/entity_update.json"), Ave.class);
        avesService.update(toUpdate.getCdAve()+1, toUpdate);
    }
    
//    @Test
//    public void delete() {
//        Ave toDeleteJson, toDelete, deleted;
//        toDeleteJson = Transformer.jsonToObjetFromInputStream(getClass().getResourceAsStream("/data/ave/json/entity_delete.json"), Ave.class);
//        toDelete = avesService.findByIdAve(toDeleteJson.getCdAve());
//        entityManager.getTransaction().begin();
//        avesService.delete(toDelete.getCdAve());
//        entityManager.getTransaction().commit();
//        try {
//           deleted = avesService.findByIdAve(toDelete.getCdAve()); 
//        } catch (EntityNotFoundException exception) {
//           
//        }
//    }
    
    @Test (expected = EntityNotFoundException.class)
    public void deleteNonExistent() {
        avesService.delete("-1");
    }
}
