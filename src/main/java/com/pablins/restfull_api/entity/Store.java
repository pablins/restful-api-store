package com.pablins.restfull_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity//Le decimos a spring que esta clase (entidad) tiene que mapearse a la DB
@Table(name = "tb_stores")//Forma en la que se mapeará y asignamos nombre (Estandar DB debe ser en minusculas y plural).
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder//patrón de diseño builder para construir objetos de esta clase
public class Store {

    //Establemos la PK y la forma como se generará
    @Id
    //Se basa en una columna de base de datos con incremento automático y permite que la base de datos genere un nuevo valor con cada operación de inserción (AUTOINCREMENT)
    /*@GeneratedValue: Representa a un campo autogereado (secuencial),
    equivalente a un campo identity de una sentencia SQL.*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Validamos que el nombre no esté vacío
    //personlizamos el mensaje (springboot nos ofrece unos genéricos, por ejemplo para este caso sería 'no debe estar vacío' y el idioma lo tomaría de acuerdo a nuestro sistema)
    @NotBlank(message = "Please add the name")
    private String name;
    private String floor;

    //Validamos el tamaño mínimo y máximo. Dejamos el mensaje por default
    //@Length(min = 4, max = 10)
    //Podemos especificar varias veces el Length y tener mensajes personalizados
    @Length(min = 4, message = "The field must be at least {min} characters. Dijo Pablo")
    @Length(max = 10, message = "The field must be less than {max} characters. Dijo Pablo")
    private String code;
}
