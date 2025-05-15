package com.narteykwamemeshack.store.repositories;

import com.narteykwamemeshack.store.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Byte> {
}