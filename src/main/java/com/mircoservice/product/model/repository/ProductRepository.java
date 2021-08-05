package com.mircoservice.product.model.repository;

import com.mircoservice.product.model.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
