package com.daw.CafeLushAPI.repositories;

import com.daw.CafeLushAPI.models.entities.TipoLeche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoLecheRepository extends JpaRepository<TipoLeche, Integer> {
}
