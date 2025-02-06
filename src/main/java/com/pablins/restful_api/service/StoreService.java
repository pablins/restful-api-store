package com.pablins.restful_api.service;

import com.pablins.restful_api.entity.Store;
import com.pablins.restful_api.error.StoreNotFoundException;

import java.util.List;
import java.util.Optional;

public interface StoreService {

    List<Store> findAllStores();
    Store saveStore(Store store);
    Store updateStore(Long id, Store store);
    void deleteStore(Long id);

    Optional<Store> findStoreByNameWithJPQL(String name);
    Optional<Store> findByName(String name);
    Optional<Store> findByNameIgnoreCase(String name);

    Store findStoreById(Long id) throws StoreNotFoundException;
}
