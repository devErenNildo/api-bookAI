package com.erenildo.bookai.repository;

import com.erenildo.bookai.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoRepository extends JpaRepository<Role, Integer> {
}
