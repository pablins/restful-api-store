package com.pablins.restfull_api.controller;

import com.pablins.restfull_api.entity.Store;
import com.pablins.restfull_api.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/store")
public class StoreController {

    @Autowired
    StoreService storeService;

    @GetMapping("/findAll")
    public List<Store> findAllStores() {
        return storeService.findAllStores();
    }

}
