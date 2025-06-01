package com.daw.CafeLushAPI.services.impl;

import com.daw.CafeLushAPI.dtos.request.OrdenRequest;
import com.daw.CafeLushAPI.dtos.response.OrdenResponse;
import com.daw.CafeLushAPI.models.entities.*;
import com.daw.CafeLushAPI.repositories.*;
import com.daw.CafeLushAPI.services.OrdenService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdenServiceImpl implements OrdenService {

    private final OrdenRepository ordenRepository;
    private final ClienteRepository clienteRepository;
    private final MetodoPagoRepository metodoPagoRepository;
    private final DireccionRepository direccionRepository;
    private final BebidaCustomRepository bebidaCustomRepository;
    private final AlimentoRepository alimentoRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public OrdenResponse crearOrden(OrdenRequest request) {
        final BigDecimal[] total = {BigDecimal.ZERO};
        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));

        MetodoPago metodoPago = metodoPagoRepository.findById(request.getMetodoPagoId())
                .orElse(null); // puede ser null

        Direccion direccion = direccionRepository.findById(request.getDireccionId())
                .orElse(null); // puede ser null

        Orden orden = Orden.builder()
                .cliente(cliente)
                .metodoPago(metodoPago)
                .direccion(direccion)
                .fecha(LocalDateTime.now())
                .estado(Orden.EstadoOrden.pendiente)
                .nota(request.getNota())
                .build();

        Orden finalOrden = ordenRepository.save(orden);

        List<AlimentoOrden> alimentoOrdenes = request.getAlimentos().stream().map(a -> {
            Alimento alimento = alimentoRepository.findById(a.getAlimentoId())
                    .orElseThrow(() -> new EntityNotFoundException("Alimento con id " + a.getAlimentoId() + " no encontrado"));

            BigDecimal subtotal = alimento.getPrecio().multiply(BigDecimal.valueOf(a.getCantidad()));
            total[0] = total[0].add(subtotal);
            return AlimentoOrden.builder()
                    .alimento(alimento)
                    .orden(finalOrden)
                    .cantidad(a.getCantidad())
                    .subtotal(subtotal)
                    .build();
        }).collect(Collectors.toList());


        List<BebidaOrden> bebidaOrdenes = request.getBebidas().stream().map(b -> {
            BebidaCustom bebida = bebidaCustomRepository.findById(b.getBebidaCustomId())
                    .orElseThrow(() -> new EntityNotFoundException("Bebida con id " + b.getBebidaCustomId() + " no encontrada"));

            BigDecimal subtotal = bebida.getPrecio().multiply(BigDecimal.valueOf(b.getCantidad()));
            total[0] = total[0].add(subtotal);

            return BebidaOrden.builder()
                    .bebidaCustom(bebida)
                    .orden(finalOrden)
                    .cantidad(b.getCantidad())
                    .subtotal(subtotal)
                    .build();
        }).collect(Collectors.toList())
        ;

        orden.setTotal(total[0]);
        orden.setAlimentoOrdenes(alimentoOrdenes);
        orden.setBebidaOrdenes(bebidaOrdenes);

        orden = ordenRepository.save(orden); // guardamos con los detalles

        return fromEntity(orden);
    }

    @Override
    public List<OrdenResponse> obtenerTodas() {
        return ordenRepository.findAll().stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public OrdenResponse obtenerPorId(Integer id) {
        Orden orden = ordenRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Orden no encontrada"));
        return fromEntity(orden);
    }

    @Override
    public List<OrdenResponse> obtenerPorCliente(Integer clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));
        return ordenRepository.findByCliente(cliente).stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }


    private OrdenResponse fromEntity(Orden orden) {
        return OrdenResponse.builder()
                .id(orden.getId())
                .clienteNombre(orden.getCliente().getUsuario().getNombre() + " " + orden.getCliente().getUsuario().getPrimerApellido())
                .metodoPago(orden.getMetodoPago() != null ? "TARJETA" : null)
                .direccion(orden.getDireccion() != null ? orden.getDireccion().getColonia() +
                        orden.getDireccion().getCodigoPostal() + " " +
                        orden.getDireccion().getNumero() : null)
                .fecha(orden.getFecha())
                .estado(orden.getEstado().toString())
                .total(orden.getTotal())
                .nota(orden.getNota())
                .alimentos(
                        orden.getAlimentoOrdenes().stream()
                                .map(a -> a.getAlimento().getNombre())
                                .collect(Collectors.toList())
                ).bebidas(
                        orden.getBebidaOrdenes().stream()
                                .map(b -> b.getBebidaCustom().getBebidaBase().getNombre() + " " +
                                        b.getBebidaCustom().getTamanoBebida().getNombre() + " " +
                                        (b.getBebidaCustom().getCremaEspuma() != null ? b.getBebidaCustom().getCremaEspuma().getTipo() : "Sin crema") + " " +
                                        (b.getBebidaCustom().getEspecia() != null ? b.getBebidaCustom().getEspecia().getTipo() : "Sin especia") + " " +
                                        (b.getBebidaCustom().getEdulcorante() != null ? b.getBebidaCustom().getEdulcorante().getTipo() : "Sin edulcorante") + " " +
                                        (b.getBebidaCustom().getTipoLeche() != null ? b.getBebidaCustom().getTipoLeche().getTipo() : "Sin leche"))
                                .collect(Collectors.toList())
                )
                .build();

    }
}
