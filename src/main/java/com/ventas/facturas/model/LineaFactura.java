package com.ventas.facturas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
@Table(name = "linea_factura")
public class LineaFactura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonIgnoreProperties("lineaFacturas")
    @ManyToOne
    @JoinColumn(name = "idProducto")
    private Producto producto;

    @JsonIgnoreProperties("lineaFacturas")
    @ManyToOne
    @JoinColumn(name = "idFactura")
    private Factura factura;

    private int cantidad;

    private Double precio;

    private Double subtotal;



    public Long getId() {
        return id;
    }



    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "LineaFactura{" +
                "id=" + id +

                ", cantidad=" + cantidad +
                ", precio=" + precio +
                ", subtotal=" + subtotal +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineaFactura that = (LineaFactura) o;
        return cantidad == that.cantidad && Objects.equals(id, that.id) && Objects.equals(producto, that.producto) && Objects.equals(factura, that.factura) && Objects.equals(precio, that.precio) && Objects.equals(subtotal, that.subtotal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, producto, factura, cantidad, precio, subtotal);
    }
}
