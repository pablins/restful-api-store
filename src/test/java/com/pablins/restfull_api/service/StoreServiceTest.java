package com.pablins.restfull_api.service;

import com.pablins.restfull_api.entity.Store;
import com.pablins.restfull_api.repository.StoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest//Dado que es una prueba de funcionalidad que hacemos sobre la capa de servicio (aquí no validamos la persistencia)
class StoreServiceTest {

    //Inyectamos nuestra capa de servicio
    @Autowired
    private StoreService storeService;

    //@MockBean//Deprecated -> https://stackoverflow.com/questions/79243535/what-is-the-replacement-for-the-deprecated-mockbeans-in-springboot-3-4-0
    @MockitoBean//Usamos Mockito para simular nuestro repositorio
    private StoreRepository storeRepository;

    @BeforeEach
    void setUp() {
        /*
         Simulamos la persistencia a la DB, nosotros queremos probar la lógica de negocio
         */
        //Definimos el objeto que tendremos
        String storeName = "Koaj";
        Store store = Store.builder()
                .id(1L)
                .name(storeName)
                .floor("Second Floor")
                .code("CLO-021-2")
                .build();
        //Simulamos la respuesta de StoreRepository cuando se consulte "Koaj". Es decir, simula que lo trae de la DB
        Mockito.when(storeRepository.findByNameIgnoreCase(storeName)).thenReturn(Optional.of(store));
    }

    //Probamos con un nombre que asumimos que está en la DB, esperamos que debería encontrarse
    @Test
    //Nos permite describir mejor lo que hará la prueba unitaria
    @DisplayName("Prueba de obtención de información de un Store enviando un nombre válido")
    public void findByNameIgnoreCaseShouldFound() {
        //Nombre tienda que vamos a buscar
        String storeName = "Koaj";
        Store store = storeService.findByNameIgnoreCase(storeName).get();

        assertEquals(storeName, store.getName());

    }
}