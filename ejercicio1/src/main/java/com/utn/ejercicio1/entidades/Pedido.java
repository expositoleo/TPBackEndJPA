
package com.utn.ejercicio1.entidades;

import com.utn.ejercicio1.enumeracion.Estado;
import com.utn.ejercicio1.enumeracion.TipoDeEnvio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pedido implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Estado estado;
    private Date fecha = new Date();
    @Enumerated(EnumType.STRING)
    private TipoDeEnvio tipoEnvio;
    private double total;

    // RELACIÓN FACTURA
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "factura_id")
    @Builder.Default
    private Factura factura = null;

    public void mostrarFactura() {
        System.out.println("Factura del pedido Nro "+ id +":");
        System.out.println("Número: " + factura.getNumero() + ", Fecha: " + factura.getFecha() + ", Total: " + factura.getTotal());
    }


    // RELACIÓN DETALLES PEDIDO
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido_id")
    @Builder.Default
    private List<DetallePedido> detalles = new ArrayList<>();

    public void agregarDetalle(DetallePedido detalle){
        detalles.add(detalle);
    }


    public void mostrarDetalle() {
        System.out.println("Detalles de Pedido Nro " + id + ":");
        for (DetallePedido detalle : detalles) {
            System.out.println("Cantidad: " + detalle.getCantidad() + ", Subtotal: " + detalle.getSubtotal());
        }
    }


}


