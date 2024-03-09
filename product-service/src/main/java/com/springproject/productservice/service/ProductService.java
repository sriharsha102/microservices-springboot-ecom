package com.springproject.productservice.service;

import com.springproject.productservice.dbo.ProductRequest;
import com.springproject.productservice.dbo.ProductResponse;
import com.springproject.productservice.model.Product;
import com.springproject.productservice.repository.ProductRepositroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProductService {

    private final ProductRepositroy productRepositroy;
    public void createProduct(ProductRequest productRequest){
        Product product=Product.builder().name(productRequest.getName()).description(productRequest.getDescription()).price(productRequest.getPrice()).build();
        productRepositroy.save(product);

        log.info("Product {} is saved", product.getId());

    }

    public List<ProductResponse> getAllProducts() {
        List<Product> allProducts=productRepositroy.findAll();
        return allProducts.stream().map(product -> toProductResponse(product)).toList();

    }
    private ProductResponse toProductResponse(Product product) {
        return ProductResponse.builder().id(product.getId()).name(product.getName()).price(product.getPrice()).description(product.getDescription()).build();
    }

}
