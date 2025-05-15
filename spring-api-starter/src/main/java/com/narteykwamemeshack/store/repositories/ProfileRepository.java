package com.narteykwamemeshack.store.repositories;

import com.narteykwamemeshack.store.entities.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
}