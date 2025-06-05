package com.daw.CafeLushAPI.config;

import com.daw.CafeLushAPI.models.entities.CremaEspuma;
import com.daw.CafeLushAPI.models.entities.Edulcorante;
import com.daw.CafeLushAPI.models.entities.Especia;
import com.daw.CafeLushAPI.models.entities.TipoLeche;
import com.daw.CafeLushAPI.repositories.CremaEspumaRepository;
import com.daw.CafeLushAPI.repositories.EdulcoranteRepository;
import com.daw.CafeLushAPI.repositories.EspeciaRepository;
import com.daw.CafeLushAPI.repositories.TipoLecheRepository;

import com.daw.CafeLushAPI.services.ComplementoService;
import com.daw.CafeLushAPI.services.impl.ComplementoServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ComplementoConfig {

    @Bean
    public ComplementoService<Edulcorante> edulcoranteService(EdulcoranteRepository repo) {
        return new ComplementoServiceImpl<>(repo, Edulcorante::new);
    }

    @Bean
    public ComplementoService<TipoLeche> tipoLecheService(TipoLecheRepository repo) {
        return new ComplementoServiceImpl<>(repo, TipoLeche::new);
    }
    @Bean
    public ComplementoService<Especia> especiaService(EspeciaRepository repo) {
        return new ComplementoServiceImpl<>(repo, Especia::new);
    }
    @Bean
    public ComplementoService<CremaEspuma> especiaComplementoService(CremaEspumaRepository repo) {
        return new ComplementoServiceImpl<>(repo, CremaEspuma::new);
    }
}
