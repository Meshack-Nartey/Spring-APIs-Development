package com.narteykwamemeshack.store.repositories;

import com.narteykwamemeshack.store.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategoryId(Byte categoryId);
}