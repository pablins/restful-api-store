package com.pablins.restful_api.service;

import com.pablins.restful_api.entity.Store;
import com.pablins.restful_api.error.StoreNotFoundException;
import com.pablins.restful_api.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service//le indicamos a spring que aquí estará nuestra capa de servicio y la lógica que desarrollaremos
public class StoreServiceImpl implements StoreService {

    @Autowired//inversión de control de spring
    private StoreRepository storeRepository;

    @Override
    public List<Store> findAllStores() {
        return storeRepository.findAll();
    }

    @Override
    public Store saveStore(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public Store updateStore(Long id, Store store) {

        Store storeDb = storeRepository.findById(id).get();

        if (isNotNullAndNotEmpty(store.getName())) {
            storeDb.setName(store.getName());
        }
        if (isNotNullAndNotEmpty(store.getFloor())) {
            storeDb.setFloor(store.getFloor());
        }
        if (isNotNullAndNotEmpty(store.getCode())) {
            storeDb.setCode(store.getCode());
        }

        return storeRepository.save(storeDb);//Interpreta que sí tiene un ID lo que queremos es actualizar

    }

    @Override
    public void deleteStore(Long id) {
        storeRepository.deleteById(id);
    }

    @Override
    public Optional<Store> findStoreByNameWithJPQL(String name) {
        return storeRepository.findStoreByNameWithJPQL(name);
    }

    @Override
    public Optional<Store> findByName(String name) {
        return storeRepository.findByName(name);
    }

    @Override
    public Optional<Store> findByNameIgnoreCase(String name) {
        return storeRepository.findByNameIgnoreCase(name);
    }

    @Override
    public Store findStoreById(Long id) throws StoreNotFoundException {//exception que posiblemente podría arrojarse
        Optional<Store> store = storeRepository.findById(id);

        if(!store.isPresent()) {//Cuando no encuentre un store por el ID buscado, lanzamos la exception personalizada
            throw new StoreNotFoundException("Store is not available");
        }

        return store.get();
    }

    private boolean isNotNullAndNotEmpty(String value) {
        return Objects.nonNull(value) && !value.isEmpty();
    }
}
