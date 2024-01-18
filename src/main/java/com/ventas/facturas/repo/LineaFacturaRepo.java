package com.ventas.facturas.repo;

import com.ventas.facturas.model.LineaFactura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineaFacturaRepo extends JpaRepository<LineaFactura, Long> {
}
