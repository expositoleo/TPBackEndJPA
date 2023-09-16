
package com.utn.ejercicio1.entidades;

import com.utn.ejercicio1.enumeracion.Estado;
import com.utn.ejercicio1.enumeracion.FormaDePago;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Factura implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numero;
    private Date fecha = new Date();
    private double descuento;
    @Enumerated(EnumType.STRING)
    private FormaDePago formaPago;
    private int total;

}
