package com.pablins.restfull_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity//Le decimos a spring que esta clase (entidad) tiene que mapearse a la DB
@Table(name = "stores")//Forma en la que se mapeará y asignamos nombre (Estandar DB debe ser en minusculas y plural).
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder//patrón de diseño builder para construir objetos de esta clase
public class Store {

    //Establemos la PK y la forma como se generará
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Se basa en una columna de base de datos con incremento automático y permite que la base de datos genere un nuevo valor con cada operación de inserción (AUTOINCREMENT)
    private Long id;
    private String name;
    private String floor;
    private String code;
}
