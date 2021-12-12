package com.dreyer.saletaxes.jpa.repository;

import com.dreyer.saletaxes.jpa.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductEntityRepository extends JpaRepository<ProductEntity, Long> {
}
