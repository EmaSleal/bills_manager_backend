package com.ventas.facturas.controller;

import com.ventas.facturas.model.Factura;
import com.ventas.facturas.model.Producto;
import com.ventas.facturas.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor

public class ProductoController {
    private final ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> findAll() {
        var productos = productoService.findAll();
        return productos.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> findById(@PathVariable Long id) {
        var producto = productoService.findById(id);
        return producto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Producto> save(@RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.save(producto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Producto> deleteById( @PathVariable Long id) {
        var producto = productoService.deleteById(id);
        return producto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/saveBatch")
    public ResponseEntity<List<Producto>> saveBatch(@RequestBody List<Producto> productos) {
        if (productos != null && !productos.isEmpty()) {
            List<Producto> productosGuardados = productoService.saveAll(productos);
            return ResponseEntity.ok(productosGuardados);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Producto> update(@RequestBody Producto producto, @PathVariable Long id) {
        var productoEncontrado = productoService.update(producto,id);

        return productoEncontrado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
