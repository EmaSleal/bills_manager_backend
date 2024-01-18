package com.ventas.facturas.service;

import com.ventas.facturas.model.Factura;
import com.ventas.facturas.model.LineaFactura;


import java.util.List;
import java.util.Optional;


public interface FacturaService {

    Optional<Factura> findById(Long id);

    Factura save(Factura factura);

    Optional<Factura> deleteById(Long id);

    Optional<List<Factura>> findAll();

    Optional<Factura> addProduct(Long idFactura, List<LineaFactura> lineasFactura);

    Optional<Factura> update(Factura factura, Long id);
}
