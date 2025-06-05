package com.daw.CafeLushAPI.repositories;

import com.daw.CafeLushAPI.models.entities.Alimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlimentoRepository extends JpaRepository<Alimento, Integer> {}
