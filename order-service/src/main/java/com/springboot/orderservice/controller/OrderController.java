package com.springboot.orderservice.controller;


import com.springboot.orderservice.ddo.OrderRequest;
import com.springboot.orderservice.service.orderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final com.springboot.orderservice.service.orderService orderService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest){

        orderService.placeOrder(orderRequest);
        return "order placed succesfully";

    }


}
