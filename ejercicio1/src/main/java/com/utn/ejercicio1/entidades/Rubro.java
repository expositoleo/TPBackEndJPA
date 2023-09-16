
package com.utn.ejercicio1.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rubro implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String denominacion;

    //RELACIÓN PRODUCTO
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "rubro_id")
    @Builder.Default
    private List<Producto> productos = new ArrayList<>();
    public void agregarProducto(Producto producto){
        productos.add(producto);
    }

    public void mostrarProductos() {
        System.out.println("Productos del rubro: " + denominacion + ":");
        for (Producto producto : productos) {
            System.out.println("Tipo: " + producto.getTipo() + ", Tiempo Estimado de preparación: " + producto.getTiempoEstimadoCocina() + ", denominación: " + producto.getDenominacion() + ", precio compra y venta: $" + producto.getPrecioCompra() + " - $" + producto.getPrecioVenta() + ", stock actual y mínimo: " + producto.getStockActual() + " - " + producto.getStockMinimo());
        }
    }


}
