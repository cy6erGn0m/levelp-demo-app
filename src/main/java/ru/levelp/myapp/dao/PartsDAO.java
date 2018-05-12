package ru.levelp.myapp.dao;

import ru.levelp.myapp.model.Part;
import ru.levelp.myapp.model.Supplier;

import javax.persistence.EntityManager;
import java.util.List;

public class PartsDAO {
    private final EntityManager em;

    public PartsDAO(EntityManager em) {
        this.em = em;
    }

    public Part findByPrimaryKey(int id) {
        return em.find(Part.class, id);
    }

    public Part createPart(String partId, String title, Supplier supplier) {
        Part part = new Part(partId, title);
        part.setSupplier(supplier);

        em.persist(part);

        return part;
    }

    public Supplier createSupplier(String name) {
        Supplier supplier = new Supplier();
        supplier.setName(name);

        em.persist(supplier);

        return supplier;
    }

    @SuppressWarnings("unchecked")
    public List<Part> findByPartId(String partId) {
        return em.createNamedQuery(Part.SEARCH_BY_PART_ID)
                .setParameter("partId", partId)
                .getResultList();
    }
}
