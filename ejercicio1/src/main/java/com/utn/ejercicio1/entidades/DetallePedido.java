
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
public class DetallePedido implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int cantidad;
    private double subtotal;

    //RELACIÓN PRODUCTO
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "producto_id")
    @Builder.Default
    private Producto producto = new Producto();

    public void mostrarProducto(){
        System.out.println("Producto del Detalle Pedido: " + this.getProducto().getDenominacion() + ". Tipo: " + getProducto().getTipo() + ", precio : $" + getProducto().getPrecioVenta());
    }
}
