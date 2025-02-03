package com.pablins.restfull_api.service;

import com.pablins.restfull_api.entity.Store;
import com.pablins.restfull_api.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

    private boolean isNotNullAndNotEmpty(String value) {
        return Objects.nonNull(value) && !value.isEmpty();
    }
}
