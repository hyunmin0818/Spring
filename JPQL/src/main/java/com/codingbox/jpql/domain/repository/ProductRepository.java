package com.codingbox.jpql.domain.repository;

import com.codingbox.jpql.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}