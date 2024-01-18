package com.ventas.facturas.controller;

import com.ventas.facturas.model.Factura;
import com.ventas.facturas.service.FacturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facturas")
@RequiredArgsConstructor
public class FacturaController {

    @Autowired
    private  FacturaService facturaService;

    @GetMapping
    public ResponseEntity<List<Factura>> findAll() {
        var facturas = facturaService.findAll();
        return facturas.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Factura> findById(@PathVariable Long id) {
        var factura = facturaService.findById(id);
        return factura.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Factura> save(@RequestBody Factura factura) {
        return ResponseEntity.ok(facturaService.save(factura));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Factura> deleteById(@PathVariable Long id) {
        var factura = facturaService.deleteById(id);
        return factura.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /*@PostMapping("/{idFactura}")
    public ResponseEntity<Factura> addProduct(@PathVariable Long idFactura, @PathVariable Long idProducto) {
        var factura = facturaService.addProduct(idFactura);
        return factura.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }*/

    @PutMapping("{id}")
    public ResponseEntity<Factura> update(@RequestBody Factura factura, @PathVariable Long id) {
        var facturaEncontrada = facturaService.update(factura,id);

        return facturaEncontrada.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


}
