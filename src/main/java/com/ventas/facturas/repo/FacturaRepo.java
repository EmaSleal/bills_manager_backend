package com.ventas.facturas.repo;

import com.ventas.facturas.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepo extends JpaRepository<Factura, Long> {
}
