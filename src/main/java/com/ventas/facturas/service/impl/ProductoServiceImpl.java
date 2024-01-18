package com.ventas.facturas.service.impl;

import com.ventas.facturas.model.Producto;
import com.ventas.facturas.repo.ProductoRepo;
import com.ventas.facturas.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepo productoRepo;

    @Override
    public Optional<Producto> findById(Long id) {
        var producto = productoRepo.findById(id).orElse(null);
        if (producto == null) {
            return Optional.empty();
        }
        return Optional.of(producto);
    }

    @Override
    public Producto save(Producto producto) {
        return productoRepo.save(producto);
    }

    @Override
    public Optional<Producto> deleteById(Long id) {
        var producto = productoRepo.findById(id).orElse(null);
        if (producto == null) {
            return Optional.empty();
        }
        productoRepo.delete(producto);
        return Optional.of(producto);
    }

    @Override
    public Optional<List<Producto>> findAll() {
        return Optional.of(productoRepo.findAll());
    }

    @Override
    public List<Producto> saveAll(List<Producto> productos) {
        return productoRepo.saveAll(productos);
    }

    @Override
    public Optional<Producto> update(Producto producto, Long id) {
        var productoEncontrado = productoRepo.findById(id).orElse(null);
        if (productoEncontrado == null) {
            return Optional.empty();
        }
        productoEncontrado.setNombre(producto.getNombre());
        productoEncontrado.setPrecio(producto.getPrecio());
        productoEncontrado.setDescripcion(producto.getDescripcion());
        productoEncontrado.setEstado(producto.getEstado());
        productoEncontrado.setPresentacion(producto.getPresentacion());
        return Optional.of(productoRepo.save(productoEncontrado));
    }
}
