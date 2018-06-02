package ru.levelp.myapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.levelp.myapp.model.Part;
import ru.levelp.myapp.model.Supplier;

import java.util.List;

@Repository
public interface PartsRepository extends JpaRepository<Part, Integer> {
    List<Part> findByPartId(String partId);

    @Query("select p from Part p where p.supplier = :supplier")
    List<Part> findByExample(@Param("supplier") Supplier supplier);
}
