package com.ventas.facturas.service.impl;

import com.ventas.facturas.model.Factura;
import com.ventas.facturas.model.LineaFactura;
import com.ventas.facturas.repo.FacturaRepo;
import com.ventas.facturas.repo.ProductoRepo;
import com.ventas.facturas.service.FacturaService;
import com.ventas.facturas.service.LineaFacturaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FacturaServiceImpl implements FacturaService {

    private final FacturaRepo facturaRepo;

    private final ProductoRepo productoRepo;

    private final LineaFacturaService lineaFacturaService;
    @Override
    public Optional<Factura> findById(Long id) {
        var factura = facturaRepo.findById(id).orElse(null);
        if (factura == null) {
            return Optional.empty();
        }
        return Optional.of(factura);
    }



    @Override
    public Optional<Factura> deleteById(Long id) {
        var factura = facturaRepo.findById(id).orElse(null);
        if (factura == null) {
            return Optional.empty();
        }facturaRepo.deleteById(id);
        return Optional.of(factura);
    }

    @Override
    public Optional<List<Factura>> findAll() {
        return Optional.of(facturaRepo.findAll());
    }

    @Override
    public Factura save(Factura factura) {
        log.info("producto",factura.getLineaFacturas().get(0).getProducto());
        //obtengo la hora y fecha de costa rica actual
        var fecha = java.time.LocalDateTime.now(java.time.ZoneId.of("America/Costa_Rica"));

        factura.setFecha(fecha.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        var facturaGuardada = facturaRepo.save(factura);
        addProduct(facturaGuardada.getIdFactura(), factura.getLineaFacturas());
        return facturaGuardada;
    }

    @Override
    public Optional<Factura> addProduct(Long idFactura, List<LineaFactura> lineasFactura) {
        var factura = facturaRepo.findById(idFactura).orElse(null);

        if (factura == null ) {
            return Optional.empty();
        }
        factura.setLineaFacturas(null);
        lineasFactura.forEach(lineaFactura -> {
            lineaFactura.setFactura(factura);

        });

        lineaFacturaService.saveAll(lineasFactura);

        return Optional.of(facturaRepo.save(factura));
    }

    @Override
    public Optional<Factura> update(Factura factura, Long id) {
        var facturaEncontrada = facturaRepo.findById(id).orElse(null);
        if (facturaEncontrada == null) {
            return Optional.empty();
        }
        facturaEncontrada.setFecha(factura.getFecha());
        facturaEncontrada.setNombreCliente(factura.getNombreCliente());
        facturaEncontrada.setFecha(factura.getFecha());
        facturaEncontrada.setLineaFacturas(factura.getLineaFacturas());
        facturaEncontrada.setEntregado(factura.isEntregado());
        facturaEncontrada.setPagado(factura.isPagado());
        facturaEncontrada.setDireccion(factura.getDireccion());

        return Optional.of(facturaRepo.save(facturaEncontrada));

    }


}
