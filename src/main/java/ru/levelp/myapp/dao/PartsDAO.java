package ru.levelp.myapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.levelp.myapp.model.Part;
import ru.levelp.myapp.model.Supplier;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class PartsDAO {
    @Autowired
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private PartsRepository repository;

    @Autowired
    private SupplierRepository supplierRepository;

    public Part findByPrimaryKey(int id) {
        return em.find(Part.class, id);
    }

    @Transactional
    public Part createPart(String partId, String title, int supplierId) {
        return createPart(partId, title, em.find(Supplier.class, supplierId));
    }

    @Transactional
    public Part createPart(String partId, String title, Supplier supplier) {
        Part part = new Part(partId, title);
        part.setSupplier(supplier);

        em.persist(part);

        return part;
    }

    public boolean hasSuppliers() {
        return supplierRepository.count() > 0;
    }

    @Transactional
    public Supplier createSupplier(String name) {
        Supplier supplier = new Supplier();
        supplier.setName(name);

        em.persist(supplier);

        return supplier;
    }

    @Transactional
    public void createInitialData() {
        if (!hasSuppliers()) {
            Supplier myCompany = createSupplier("My company");

            for (int i = 0; i < 3; ++i) {
                createPart("000-" + i, "part " + i, myCompany);
            }
        }
    }

    public List<Part> findByPartId(String partId) {
        return repository.findByPartId(partId);
    }

    public List<Part> findAllParts() {
        return repository.findAll();
    }
}
