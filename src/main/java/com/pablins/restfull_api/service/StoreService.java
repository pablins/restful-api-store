package com.pablins.restfull_api.service;

import com.pablins.restfull_api.entity.Store;

import java.util.List;

public interface StoreService {

    List<Store> findAllStores();
    Store saveStore(Store store);
    Store updateStore(Long id, Store store);
    void deleteStore(Long id);

}
