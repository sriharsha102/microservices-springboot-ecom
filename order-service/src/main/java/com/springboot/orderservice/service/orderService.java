package com.springboot.orderservice.service;

import com.springboot.orderservice.dto.InventoryResponse;
import com.springboot.orderservice.dto.OrderLineItemsDto;
import com.springboot.orderservice.dto.OrderRequest;
import com.springboot.orderservice.model.Order;
import com.springboot.orderservice.model.OrderLineItems;
import com.springboot.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class orderService {


    private final OrderRepository orderRepository;
    private final WebClient webClient;
    public void placeOrder(OrderRequest orderRequest){
        Order order=new Order();
        order.setOrderName(UUID.randomUUID().toString());
       List<OrderLineItems> orderLineItemsList=orderRequest.getOrderLineItemsList()
                .stream()

                .map(this::mapToDTo)
               .toList();
       order.setOrderLineItemsList(orderLineItemsList);

      List<String> skuCodes= order.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode).toList();

       //call inventory service
       InventoryResponse[] inventoryResponseArray= webClient.get().uri("http://localhost:8082/api/inventory", uriBuilder -> uriBuilder.queryParam("sku-code",skuCodes).build()).retrieve().bodyToMono(InventoryResponse[].class).block();
      Boolean allProductsInStock= Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse::isInStock);
       if(allProductsInStock) {
           orderRepository.save(order);
       }
       else throw new IllegalArgumentException("Product is not in stock, please try agian later");
    }

    private OrderLineItems mapToDTo(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems=new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItems.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
     return orderLineItems;
    }

}
