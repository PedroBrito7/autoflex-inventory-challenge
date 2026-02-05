package com.autoflex.inventory.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "product_composition")
public class ProductComposition extends PanacheEntityBase {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "product_id")
    public Product product;

    @ManyToOne
    @JoinColumn(name = "raw_material_id")
    public RawMaterial rawMaterial;

    @Column(name = "required_quantity")
    public Integer requiredQuantity;
}