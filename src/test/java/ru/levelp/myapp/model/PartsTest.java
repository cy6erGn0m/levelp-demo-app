package ru.levelp.myapp.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PartsTest {
    private EntityManagerFactory emf;
    private EntityManager em;

    @Before
    public void setup() {
        emf = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        em = emf.createEntityManager();
    }

    @After
    public void end() {
        em.close();
        emf.close();
    }

    @Test
    public void testCreatePart() throws Throwable {
        Part part = new Part("0000-1", "My part");
        em.getTransaction().begin();
        try {
            em.persist(part);
        } catch (Throwable t) {
            em.getTransaction().rollback();
            throw t;
        } finally {
            em.getTransaction().commit();
        }
    }

    @Test
    public void testCreatePartWithSupplier() throws Throwable {
        Part part = new Part("0000-1", "My part");
        Supplier s = new Supplier();
        s.setName("some company");
        part.setSupplier(s);

        em.getTransaction().begin();
        try {
            em.persist(part);
            em.getTransaction().commit();
        } catch (Throwable t) {
            em.getTransaction().rollback();
            throw t;
        }

        Part found = em.find(Part.class, part.getId());
        assertNotNull(found);
        assertNotNull(found.getSupplier());
    }

    @Test
    public void testQuery() throws Throwable {
        testCreatePartWithSupplier();

        String searchString = "0000-1";

        @SuppressWarnings("unchecked")
        List<Part> parts = em.createQuery("from Part where partId = :partId")
                .setParameter("partId", searchString)
                .getResultList();

        assertEquals(1, parts.size());
        Part found = parts.get(0);
        assertEquals(searchString, found.getPartId());
    }

    @Test
    public void testNamedQuery() throws Throwable {
        testCreatePartWithSupplier();

        String searchString = "0000-1";

        @SuppressWarnings("unchecked")
        List<Part> parts = em.createNamedQuery(Part.SEARCH_BY_PART_ID)
                .setParameter("partId", searchString)
                .getResultList();

        assertEquals(1, parts.size());
        Part found = parts.get(0);
        assertEquals(searchString, found.getPartId());
    }
}
