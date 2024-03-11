package com.springboot.inventoryservice.inventoryservice.controller;


import com.springboot.inventoryservice.inventoryservice.dto.InventoryResponse;
import com.springboot.inventoryservice.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam("sku-code") List<String> skuCode){
        return inventoryService.isInStock(skuCode);
    }


}
