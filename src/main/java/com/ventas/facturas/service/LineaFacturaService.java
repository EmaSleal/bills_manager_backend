package com.ventas.facturas.service;

import com.ventas.facturas.model.Factura;
import com.ventas.facturas.model.LineaFactura;

import java.util.List;
import java.util.Optional;

public interface LineaFacturaService {

    Optional<LineaFactura> findById(Long id);

    LineaFactura save(LineaFactura lineaFactura);

    Optional<LineaFactura> deleteById(Long id);

    Optional<List<LineaFactura>> findAll();

    Optional<List<LineaFactura>> saveAll(List<LineaFactura> lineasFactura);


}
