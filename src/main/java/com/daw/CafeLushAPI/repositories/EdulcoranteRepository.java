package com.daw.CafeLushAPI.repositories;

import com.daw.CafeLushAPI.models.entities.Edulcorante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EdulcoranteRepository extends JpaRepository<Edulcorante, Integer> {
    boolean existsByTipo(String tipo);
}
