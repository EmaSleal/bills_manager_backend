package com.ventas.facturas.service;


import com.ventas.facturas.model.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public interface ProductoService {

    Optional<Producto> findById(Long id);

    Producto save(Producto producto);

    Optional<Producto> deleteById(Long id);

    Optional<List<Producto>> findAll();

    List<Producto> saveAll(List<Producto> productos);

    Optional<Producto> update(Producto producto, Long id);
}
