package ru.levelp.myapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.levelp.myapp.dao.PartsRepository;

import java.util.stream.Collectors;

@RestController
public class PartsSearchController {
    @Autowired
    private PartsRepository repository;

    @RequestMapping("/parts/findByPartId")
    @ResponseBody
    public PartSearchResult findByPartId(@RequestParam("part-id") String partId) {
        return new PartSearchResult(repository.findByPartId(partId).stream()
                .map(part -> new PartSearchResultRow(part.getPartId(), part.getTitle()))
                .collect(Collectors.toList()));
    }
}
