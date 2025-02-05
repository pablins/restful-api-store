package com.pablins.restfull_api.controller;

import com.pablins.restfull_api.entity.Store;
import com.pablins.restfull_api.error.StoreNotFoundException;
import com.pablins.restfull_api.service.StoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

//Pasamos como parametro el controlador
@WebMvcTest(StoreController.class)//Nos permite centrarnos en el controlador que queremos trabajar
class StoreControllerTest {

    @Autowired
    private MockMvc mockMvc;//Nos provee metodos para hacer POST, GET, DELETE, etc a nuestro controlador

    //Simulamos el bean de StoreService
    @MockitoBean
    private StoreService mockStoreService;

    private Store storeDb;

    @BeforeEach
    void setUp() {
        //Simula registro en Base de datos, el cual ya tiene un ID
        storeDb = Store.builder()
                .id(1L)
                .name("Cinema")
                .floor("Fourth Floor")
                .code("CIN-123-4")
                .build();
    }

    @Test
    public void saveStore() throws Exception {
        //Simula petición que se realiza al endpoint (no tiene el ID) es decir el JSON que llegaría
        Store peticionStore = Store.builder()
                .name("Cinema")
                .floor("Fourth Floor")
                .code("CIN-123-4")
                .build();

        //Simula la respuesta que dará el StoreService
        Mockito.when(mockStoreService.saveStore(peticionStore)).thenReturn(storeDb);

                //Configuramos la petición, esto lanza excepciones
        mockMvc.perform(MockMvcRequestBuilders
                    //URL del API que tenemos definida en el controller StoreController para el método saveStore
                    .post("/api/store/save")
                    //Tipo de dato del contenido que se envia
                    .contentType(MediaType.APPLICATION_JSON)
                    //Correspondería al JSON que normalmente enviamos desde el Postman
                    .content("{\n" +
                            "    \"name\": \"Cinema\",\n" +
                            "    \"floor\": \"Fourth Floor\",\n" +
                            "    \"code\": \"CIN-123-4\"\n" +
                            "}"))
                //Establecemos lo que esperamos como respuesta
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void findLocalById() throws Exception {
        //Simula la respuesta que hará el StoreService
        Mockito.when(mockStoreService.findStoreById(1L)).thenReturn(storeDb);

        //Hacemos la petición
        mockMvc.perform(MockMvcRequestBuilders
                    .get("/api/store/findById/1")
                    .contentType(MediaType.APPLICATION_JSON))
                //Esperamos como respuesta un status code 200 OK
                .andExpect(MockMvcResultMatchers.status().isOk())
                //Tambien esperamos como respuesta que el json response tenga un campo con el name que queremos
                //$.name -> Significa JSON en el campo nombre
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(storeDb.getName()));
    }
}