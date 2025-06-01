package com.daw.CafeLushAPI.repositories;

import com.daw.CafeLushAPI.models.entities.BebidaBase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BebidaBaseRepository extends JpaRepository<BebidaBase, Integer> {
    boolean existsByNombre(String nombre);
}
