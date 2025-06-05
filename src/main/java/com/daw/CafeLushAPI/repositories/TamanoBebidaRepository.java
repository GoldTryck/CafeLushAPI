package com.daw.CafeLushAPI.repositories;

import com.daw.CafeLushAPI.models.entities.TamanoBebida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TamanoBebidaRepository extends JpaRepository<TamanoBebida, Integer> {
    boolean existsByNombre(String nombre);
}