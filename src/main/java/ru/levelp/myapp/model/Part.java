package ru.levelp.myapp.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "part_id", unique = true, nullable = false)
    private String partId;

    @Column
    private String title;

    @ManyToOne(cascade = CascadeType.PERSIST, optional = false)
    private Supplier supplier;

    @Column
    private boolean legacy;

    public Part() {
    }

    public Part(String partId, String title) {
        setPartId(partId);
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
        if (partId == null) throw new IllegalArgumentException("partId shouldn't be null");
        if (partId.length() < 3) throw new IllegalArgumentException("partId shouldn't be empty");

        this.partId = partId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public boolean isLegacy() {
        return legacy;
    }

    public void setLegacy(boolean legacy) {
        this.legacy = legacy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Part part = (Part) o;
        return id == part.id &&
                legacy == part.legacy &&
                Objects.equals(partId, part.partId) &&
                Objects.equals(title, part.title) &&
                Objects.equals(supplier, part.supplier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, partId, legacy, title, supplier);
    }
}
