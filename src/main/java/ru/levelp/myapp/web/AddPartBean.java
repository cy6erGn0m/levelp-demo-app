package ru.levelp.myapp.web;

import ru.levelp.myapp.model.Supplier;

import javax.persistence.EntityManager;
import java.util.List;

public class AddPartBean {
    private final EntityManager em;

    public AddPartBean(EntityManager em) {
        this.em = em;
    }

    @SuppressWarnings("unchecked")
    public List<Supplier> getSuppliers() {
        return em.createQuery("from Supplier").getResultList();
    }

    public Supplier findSupplier(int id) {
        return em.find(Supplier.class, id);
    }
}
