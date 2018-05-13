package ru.levelp.myapp.web;

import ru.levelp.myapp.dao.PartsDAO;
import ru.levelp.myapp.model.Supplier;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {
    private EntityManagerFactory emf;
    private EntityManager em;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        emf = Persistence.createEntityManagerFactory("ProductionPersistenceUnit");
        em = emf.createEntityManager();

        createTestParts();

        servletContextEvent.getServletContext().setAttribute("indexBean", new IndexBean(em));
        servletContextEvent.getServletContext().setAttribute("addPartBean", new AddPartBean(em));
        servletContextEvent.getServletContext().setAttribute("partsDAO", new PartsDAO(em));
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        em.close();
        emf.close();
    }

    private void createTestParts() {
        PartsDAO dao = new PartsDAO(em);

        em.getTransaction().begin();
        Supplier myCompany = dao.createSupplier("My company");

        for (int i = 0; i < 3; ++i) {
            dao.createPart("000-" + i, "part " + i, myCompany);
        }

        em.getTransaction().commit();
    }
}
