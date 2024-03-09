package com.springproject.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.springproject.productservice.model.Product;
public interface ProductRepositroy extends MongoRepository<Product,String> {



}
