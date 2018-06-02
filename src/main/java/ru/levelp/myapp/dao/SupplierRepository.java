package ru.levelp.myapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.levelp.myapp.model.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}
