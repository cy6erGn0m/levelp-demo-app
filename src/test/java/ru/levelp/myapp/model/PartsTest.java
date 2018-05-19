package ru.levelp.myapp.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.levelp.myapp.conf.TestConfiguration;
import ru.levelp.myapp.dao.PartsDAO;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PartsTest {
    @Autowired
    private EntityManager em;

    @Autowired
    private PartsDAO dao;

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
