package com.pablins.restfull_api.controller;

import com.pablins.restfull_api.entity.Store;
import com.pablins.restfull_api.error.StoreNotFoundException;
import com.pablins.restfull_api.service.StoreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/store")
public class StoreController {

    @Autowired
    StoreService storeService;

    @GetMapping("/findAll")
    public List<Store> findAllStores() {
        return storeService.findAllStores();
    }

    @GetMapping("/findByNameWithJPQL/{name}")
    public Optional<Store> findByNameWithJPQL(@PathVariable String name) {
        return storeService.findStoreByNameWithJPQL(name);
    }

    @GetMapping("findByName/{name}")
    public Optional<Store> findByName(@PathVariable String name) {
        return storeService.findByName(name);
    }

    @GetMapping("/findByNameIgnoreCase/{name}")
    public Optional<Store> findByNameIgnoreCase(@PathVariable String name) {
        return storeService.findByNameIgnoreCase(name);
    }

    @GetMapping("findById/{id}")
    public Store findById(@PathVariable Long id) throws StoreNotFoundException {//Esta será controlada por @ControllerAdvice que es la clase RestResponseEntityExceptionHandler
        return storeService.findStoreById(id);
    }

    @PostMapping("/save")
    public Store saveStore(@Valid @RequestBody Store store) {
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
