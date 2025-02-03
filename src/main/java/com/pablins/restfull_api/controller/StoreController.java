package com.pablins.restfull_api.controller;

import com.pablins.restfull_api.entity.Store;
import com.pablins.restfull_api.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/save")
    public Store saveStore(@RequestBody Store store) {
        return storeService.saveStore(store);
    }

    @PutMapping("/update/{id}")
    public Store updateStore(@PathVariable Long id, @RequestBody Store store) {
        return storeService.updateStore(id, store);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStore(@PathVariable Long id) {
        storeService.deleteStore(id);
        return "Successfully deleted";
    }

}
