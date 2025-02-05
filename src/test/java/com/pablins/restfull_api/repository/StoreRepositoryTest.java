package com.pablins.restfull_api.repository;

import com.pablins.restfull_api.entity.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest//indicamos a springboot que esta es una clase que funcionara como clase de pruebas para persistencia a la DB
class StoreRepositoryTest {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach//Método que se ejecutará antes de CADA prueba unitaria
    void setUp() {
        //Insertamos un registro de Store
        Store store = Store.builder()
                .name("Supermarket")
                .floor("Third Floor")
                .code("SUP-045-3")
                .build();

        //Hará la persistencia en una base de datos en memoria
        testEntityManager.persist(store);

    }

    /*
    Prueba unitaria para el método: Optional<Store> findByNameIgnoreCase(String name);
     */
    @Test//Indicamos que será una prueba unitaria
    public void findLocalByNameIgnoreCaseFound() {
        Optional<Store> store = storeRepository.findByNameIgnoreCase("SuPerMarkEt");

        assertEquals("Supermarket", store.get().getName());
    }

    @Test
    public void findLocalByNameIgnoreCaseNotFound() {//comportamiento cuando no encuentre en la DB
        Optional<Store> store = storeRepository.findByNameIgnoreCase("NoExiste");

        assertEquals(Optional.empty(), store);

    }
}