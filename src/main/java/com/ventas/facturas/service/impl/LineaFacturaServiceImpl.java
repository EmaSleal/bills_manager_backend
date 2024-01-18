package com.ventas.facturas.service.impl;

import com.ventas.facturas.model.Factura;
import com.ventas.facturas.model.LineaFactura;
import com.ventas.facturas.repo.LineaFacturaRepo;
import com.ventas.facturas.service.LineaFacturaService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LineaFacturaServiceImpl implements LineaFacturaService {

    private final LineaFacturaRepo lineaFacturaRepo;

    @Override
    public Optional<LineaFactura> findById(Long id) {
        return lineaFacturaRepo.findById(id);
    }

    @Override
    public LineaFactura save(LineaFactura lineaFactura) {
        return lineaFacturaRepo.save(lineaFactura);
    }

    @Override
    public Optional<LineaFactura> deleteById(Long id) {
        var lineaFactura = lineaFacturaRepo.findById(id);
        if (lineaFactura.isPresent()) {
            lineaFacturaRepo.deleteById(id);
            return lineaFactura;
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<LineaFactura>> findAll() {
        return Optional.of(lineaFacturaRepo.findAll());
    }

    @Override
    public Optional<List<LineaFactura>> saveAll(List<LineaFactura> lineasFactura) {
        return Optional.of(lineaFacturaRepo.saveAll(lineasFactura));
    }
}
