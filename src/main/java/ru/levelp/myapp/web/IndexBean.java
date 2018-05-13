package ru.levelp.myapp.web;

import ru.levelp.myapp.dao.PartsDAO;
import ru.levelp.myapp.model.Part;

import javax.persistence.EntityManager;
import java.util.List;

public class IndexBean {
    private final EntityManager em;
    private final PartsDAO dao;

    public IndexBean(EntityManager em) {
        this.em = em;
        dao = new PartsDAO(em);
    }

    public List<Part> getParts() {
        return new PartsDAO(em).findAllParts();
    }
}
