package ru.levelp.myapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.levelp.myapp.dao.PartsDAO;
import ru.levelp.myapp.model.Part;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class IndexBean {
    private final EntityManager em;
    private final PartsDAO dao;

    public IndexBean(@Autowired EntityManager em, @Autowired PartsDAO dao) {
        this.em = em;
        this.dao = dao;
    }

    public List<Part> getParts() {
        return dao.findAllParts();
    }
}
