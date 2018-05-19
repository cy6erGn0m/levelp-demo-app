package ru.levelp.myapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.levelp.myapp.model.Supplier;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class AddPartBean {
    private final EntityManager em;

    public AddPartBean(@Autowired EntityManager em) {
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
