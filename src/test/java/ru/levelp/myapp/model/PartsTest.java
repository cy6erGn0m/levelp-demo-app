package ru.levelp.myapp.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.levelp.myapp.dao.PartsDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PartsTest {
    private EntityManagerFactory emf;
    private EntityManager em;
    private PartsDAO dao;

    @Before
    public void setup() {
        emf = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        em = emf.createEntityManager();
        dao = new PartsDAO(em);
    }

    @After
    public void end() {
        em.close();
        emf.close();
    }

    @Test
    public void testCreatePartWithSupplier() throws Throwable {
        Part part;

        em.getTransaction().begin();
        try {
            part = dao.createPart("0000-1", "some part", dao.createSupplier("some company"));
            em.getTransaction().commit();
        } catch (Throwable t) {
            em.getTransaction().rollback();
            throw t;
        }

        Part found = dao.findByPrimaryKey(part.getId());
        assertNotNull(found);
        assertNotNull(found.getSupplier());
    }

    @Test
    public void testSearchByPartId() throws Throwable {
        testCreatePartWithSupplier();

        String searchString = "0000-1";

        List<Part> parts = dao.findByPartId(searchString);

        assertEquals(1, parts.size());
        Part found = parts.get(0);
        assertEquals(searchString, found.getPartId());
    }
}
