package ru.levelp.myapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.levelp.myapp.dao.PartsDAO;

@Component
public class Initializator {
    @Autowired
    private PartsDAO dao;

    @EventListener
    public void onAppStart(ContextRefreshedEvent event) {
        dao.createInitialData();
    }
}
