package com.narteykwamemeshack.store.repositories;

import com.narteykwamemeshack.store.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}