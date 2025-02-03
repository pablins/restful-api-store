package com.pablins.restfull_api.service;

import com.pablins.restfull_api.entity.Store;
import com.pablins.restfull_api.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service//le indicamos a spring que aquí estará nuestra capa de servicio y la lógica que desarrollaremos
public class StoreServiceImpl implements StoreService {

    @Autowired//inversión de control de spring
    private StoreRepository storeRepository;

    @Override
    public List<Store> findAllStores() {
        return storeRepository.findAll();
    }
}
