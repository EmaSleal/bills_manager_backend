package com.ventas.facturas.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
//hashcode and equals
@Entity
@Table(name = "facturas")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_factura")
    private Long id;

    @Column(name = "nombre_cliente", nullable = false)
    private String nombreCliente;

    @Column(name = "direccion", nullable = false)
    private String direccion;

    @Column(name = "telefono", nullable = false)
    private String telefono;

    @Column(name = "fecha", nullable = false)
    private String fecha;

    @Column(name = "pagado")
    private boolean pagado;

    @Column(name = "entregado")
    private boolean entregado;

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("factura")
    private List<LineaFactura> lineaFacturas;

    public Long getIdFactura() {
        return id;
    }

    public void setIdFactura(Long idFactura) {
        this.id = idFactura;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


    public List<LineaFactura> getLineaFacturas() {
        return lineaFacturas;
    }

    public void setLineaFacturas(List<LineaFactura> lineaFacturas) {
        this.lineaFacturas = lineaFacturas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Factura factura = (Factura) o;
        return Objects.equals(id, factura.id) && Objects.equals(nombreCliente, factura.nombreCliente) && Objects.equals(direccion, factura.direccion) && Objects.equals(telefono, factura.telefono) && Objects.equals(fecha, factura.fecha);
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        var lineaFacturas = "";
        return "Factura{" +
                "id=" + id +
                ", nombreCliente='" + nombreCliente + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", fecha='" + fecha + '\'' +
                ", lineaFacturas=" + lineaFacturas +
                '}';
    }
}
