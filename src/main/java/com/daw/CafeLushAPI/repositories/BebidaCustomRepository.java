package com.daw.CafeLushAPI.repositories;

import com.daw.CafeLushAPI.models.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BebidaCustomRepository extends JpaRepository<BebidaCustom, Integer> {
    boolean existsByBebidaBaseAndTamanoBebidaAndEdulcoranteAndTipoLecheAndEspeciaAndCremaEspuma(
            BebidaBase bebidaBase,
            TamanoBebida tamanoBebida,
            Edulcorante edulcorante,
            TipoLeche tipoLeche,
            Especia especia,
            CremaEspuma cremaEspuma
    );

    Optional<BebidaCustom> findByBebidaBaseAndTamanoBebidaAndEdulcoranteAndTipoLecheAndEspeciaAndCremaEspuma(
            BebidaBase bebidaBase,
            TamanoBebida tamano,
            Edulcorante edulcorante,
            TipoLeche tipoLeche,
            Especia especia,
            CremaEspuma cremaEspuma);
}
