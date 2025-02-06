package com.pablins.restful_api.repository;

import com.pablins.restful_api.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    /*******************************
     * OBTENER REGISTRO FILTRANDO POR NOMBRE
     ********************************/
    //OPCIÓN 1: Utilizando JPQL. Util para consultas complejas
    @Query("SELECT s " +
            "FROM Store s " +//Usamos el nombre de la entidad (como está en Java)
            "WHERE s.name = :name")
    Optional<Store> findStoreByNameWithJPQL(String name);//Optional porque esperamos que traiga un solo registro o ninguno

    //JPA Query methods: https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
    //OPCIÓN 2: Consulta con Inversión de Control que nos ofrece Spring Data
    Optional<Store> findByName(String name);

    //OPCIÓN 2.1: Consulta IoC ignorando mayus y minus
    Optional<Store> findByNameIgnoreCase(String name);
}
