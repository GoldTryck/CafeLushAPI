package com.daw.CafeLushAPI.repositories;

import com.daw.CafeLushAPI.models.entities.Cliente;
import com.daw.CafeLushAPI.models.entities.Orden;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdenRepository extends JpaRepository<Orden, Integer> {
    List<Orden> findByCliente(Cliente cliente);

}
