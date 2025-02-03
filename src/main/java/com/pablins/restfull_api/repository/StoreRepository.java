package com.pablins.restfull_api.repository;

import com.pablins.restfull_api.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    //Obtener registro por su nombre usando JPQL. Util para consultas complejas
    @Query("SELECT s " +
            "FROM Store s " +//Usamos el nombre de la entidad (como está en Java)
            "WHERE s.name = :name")
    Optional<Store> findStoreByNameWithJPQL(String name);//Optional porque esperamos que traiga un solo registro o ninguno
}
